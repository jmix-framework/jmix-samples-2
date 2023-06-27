# External Data Service

This is a simple Spring Boot application that exposes several REST endpoints used by the [External Data Sample](../external-data-sample/README.md).

The endpoints are:

* `GET http://loсalhost:18080/tasks`
* `POST http://loсalhost:18080/tasks`
* `POST http://loсalhost:18080/tasks/{id}`
* `GET http://loсalhost:18080/projects`
* `POST http://loсalhost:18080/projects`
* `POST http://loсalhost:18080/projects/{id}`

To start the service, open terminal in the `jmix-samples-2/external-data-service` directory and run:

```shell
./gradlew bootRun
```