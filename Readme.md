# JWT Spring Boot
This is a Spring boot **REST API** to demonstrate Authentication & Authorization using **JSON Web Token**.

### Prerequisites

Make sure you have install Java & Maven minimum versions to run this API.
- Java 11.0.11 +
- Apache Maven 3.6.1 +

## Building the project

To build the project, in Windows command prompt run following commands.

	cd your_project_folder/jwt_token_auth_api
	mvn clean install

it will generate a fat-jar file in the targen directory.

## Running the project

To run the project,
	
	cd target
	java -jar coursework_2-0.0.1-SNAPSHOT.jar

it will up and running the server on `http://localhost:8080/`.

## Testing the project

For testing purposes we have implemented **Swagger UI** to the REST API. To access the swagger ui enter following URL in the browser.

> http://localhost:8080/swagger-ui/

Initially we have generated two user credentials in local data structures. you can use those credentials and test the REST API.

| Username | password |
| ------ | ------ |
| admin | password |
| user | user@123 |

## License

Apache License Version 2.0 [https://www.apache.org/licenses/LICENSE-2.0]