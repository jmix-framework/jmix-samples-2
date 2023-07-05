# Using Stored Procedures

This project demonstrates how you can call database stored procedures with parameters and display the results in Jmix UI. It contains two examples: the first uses Spring's `JdbcTemplate`, the second uses JPA `EntityManager`. 

The project uses the local PostgreSQL database. Adjust connection settings in **Main Data Store** parameters and execute **Recreate** action from its context menu to create the database. 

## Using JdbcTemplate and DTO entity

The following procedures are used in this example:

```sql
create or replace function get_cars_with_model_by_year(year_ int)
    returns table(id UUID, vin text, year_ int, model text) language SQL
as $$
    select c.id, c.vin, c.year_, m.name
    from CAR c left join MODEL m on c.model_id = m.id where c.year_ = $1
$$

create or replace procedure save_car(car_vin varchar(255), car_year integer) 
language plpgsql
as  $$
begin
    update CAR set YEAR_ = car_year
    where VIN = car_vin;
end  $$                                                      
```

[CarWithModelService](src/main/java/com/company/storedprocedures/app/CarWithModelService.java) is responsible for calling stored procedures using `JdbcTemplate` and mapping data to and from [CarWithModel](src/main/java/com/company/storedprocedures/entity/CarWithModel.java) DTO entity.

[CarWithModelListView](src/main/java/com/company/storedprocedures/view/carwithmodel/CarWithModelListView.java) screen loads data by invoking the service in the loader delegate. 

[CarWithModelDetailView](src/main/java/com/company/storedprocedures/view/carwithmodel/CarWithModelDetailView.java) screen saves data by invoking the service in the `DataContext` commit delegate.

## Using EntityManager

EntityManager can automatically map results of stored procedure invocation to JPA entities.

The following procedure is used in this example:

```sql
create or replace function get_cars_by_year(year_ int)
    returns table(id UUID, vin text, year_ int) language SQL
as $$
    select c.id, c.vin, c.year_
    from CAR c where c.year_ = $1
$$
```

[CarService](src/main/java/com/company/storedprocedures/app/CarService.java) calls the stored procedure and maps the result to the [Car](src/main/java/com/company/storedprocedures/entity/Car.java) JPA entity.

[CarsByYearListView](src/main/java/com/company/storedprocedures/view/car/CarsByYearListView.java) screen loads data by invoking the service in the loader delegate. 
