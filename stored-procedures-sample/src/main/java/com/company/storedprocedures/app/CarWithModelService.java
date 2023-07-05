package com.company.storedprocedures.app;

import com.company.storedprocedures.entity.CarWithModel;
import io.jmix.core.Metadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Demonstrates how to call a database function and map the result to a DTO entity using Spring's JdbcTemplate.
 */
@Component
public class CarWithModelService {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private Metadata metadata;

    /**
     * Executes the {@code car_with_model_by_year} database function and maps the result to a list of
     * {@link CarWithModel} entity.
     *
     * @param year car production year
     * @return a list of CarWithModel DTO entities that describes car produced in the given year
     */
    public List<CarWithModel> loadCarWithModelByYear(int year) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("year", year);

        RowMapper<CarWithModel> rowMapper = (rs, rowNum) -> {
            CarWithModel carWithModel = metadata.create(CarWithModel.class);
            carWithModel.setVin(rs.getString("VIN"));
            carWithModel.setYear(rs.getInt("YEAR_"));
            carWithModel.setModel(rs.getString("MODEL"));
            return carWithModel;
        };
        List<CarWithModel> carWithModels = jdbcTemplate.query("select * from get_cars_with_model_by_year(:year)",
                parameterSource,
                rowMapper);

        return carWithModels;
    }

    public void save(CarWithModel entity) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("car_vin", entity.getVin()).addValue("car_year", entity.getYear());

        jdbcTemplate.update("call save_car(:car_vin, :car_year)", parameterSource);
    }
}