# JWT Spring Boot
This is a Spring boot **REST API** to demonstrate Authentication & Authorization using **JSON Web Token**.

### Prerequisites

Make sure you have install Java & Maven minimum versions to run this API.
- Java 11.0.11 or above
- Apache Maven 3.6.1 or above

## Building the project

To build the project, in Windows command prompt run following commands.

	$cd your_project_folder/jwt_token_auth_api
	$mvn clean install

it will generate a fat-jar file in the target directory.

## Running the project

To run the project,
	
	$cd target
	$java -jar coursework_2-0.0.1-SNAPSHOT.jar

it will up and running the server on `http://localhost:8080/`.

## Testing the project

For testing purposes we have implemented **Swagger UI** to the REST API. To access the swagger ui enter following URL in the browser.

> http://localhost:8080/swagger-ui.html

Initially we have generated two user credentials in local data structures. you can use those credentials and test the REST API.

| Username | password |
| ------ | ------ |
| admin | password |
| user | user@123 |

##### 01) Authentication endpoint

By providing given username & password in json format, you can generate a **JWT Token**,

    {
      "password": "string",
      "username": "string"
    }

The endpoint response as below,

    {
      "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTYyNjExMzcxMiwiaWF0IjoxNjI2MTEwMTEyfQ.SIiMBFy_eYpTsETBlhteCK0AyaxMYJEenK8fZMAOzT4",
      "subject": "admin",
      "expriation": "2021-07-12T18:15:12.000+00:00"
    }

You need to use the token with `"Bearer<space>"` prefix to authorize the API.

    Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTYyNjExMzcxMiwiaWF0IjoxNjI2MTEwMTEyfQ.SIiMBFy_eYpTsETBlhteCK0AyaxMYJEenK8fZMAOzT4

##### 02) Authorization endpoint

After the authorization with **JW Token**, you can use this end point to fetch the user details from local data structure. The response as below,

    {
      "name": "string",
      "userRole": "string",
      "username": "string"
    }

If you provide a invalid or expired **JW Token** endpoint will return a `401 - Unauthorized` status code.

## License

Apache License Version 2.0 [https://www.apache.org/licenses/LICENSE-2.0]