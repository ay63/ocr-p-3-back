# Châtop App

## Install Project

### Download back-end

```
git clone https://github.com/ay63/ocr-p-3-back.git
```

## Postman collection

For Postman import the collection

> resources/postman/rental.postman_collection.json

by following the documentation:

[Documentation: import export data ](https://learning.postman.com/docs/getting-started/importing-and-exporting-data/#importing-data-into-postman)

## MySQL

> SQL script for creating the schema is available resources/sql/script.sql

### Command for create and import SQL script

```
CREATE DATABASE chatop;
USE chatop;
source path/to/sql/script.sql;
```

## RSA Keys

Generate public and private key

```
openssl genpkey -algorithm RSA -out private_key.pem -pkeyopt rsa_keygen_bits:2048
openssl rsa -pubout -in private_key.pem -out public_key.pem
```

## AWS S3
S3 is used for uploading images of rentals.

> Get started with S3 https://docs.aws.amazon.com/AmazonS3/latest/userguide/GetStartedWithS3.html

## Run project

```
 ./mvnw spring-boot:run
```

## Build documentation

```
./mvnw javadoc:javadoc
```

## Swagger/OpenApi

> http://localhost:3001/api/swagger-ui/index.html
>

### Download frontend project

**Then follow the readme to install project**

```
https://github.com/OpenClassrooms-Student-Center/Developpez-le-back-end-en-utilisant-Java-et-Spring.git
```

## Technologies Used

- `Spring Boot`: Framework for creating the application.
- `Maven`: Build tool for dependency management.
- `JPA`: For object-relational mapping with the entities.
- `AWS S3`: Integrated for storage purposes.
- `Spring Security`: For authentication and authorization.
- `Swagger/OpenAPI`: For API documentation.

# General Notes

## Key Features:

- `Separation of Concerns`: Each layer is dedicated to a specific responsibility, making the code easier to understand
  and maintain.
- `DTOs and Mappers`: Ensures a clear separation between the internal data model (entities) and the data exposed via
  APIs (DTOs).
- `Configuration Files`: Provides a centralized approach to manage application settings.

# Project Package Structure

## com.openclassrooms.chatop.configurations

This package contains classes responsible for the application’s configuration.

## com.openclassrooms.chatop.controllers

This package holds the REST controllers that manage HTTP requests and responses.

## com.openclassrooms.chatop.dto

This package contains data transfer objects (DTOs) used for communication between the application and the client.

## com.openclassrooms.chatop.mappers.implementations

This package contains mapper implementations for converting between entities and DTOs.

## com.openclassrooms.chatop.entities

This package contains the core JPA entity classes that represent the database tables.

## com.openclassrooms.chatop.repositories

This package contains the repository interfaces for database operations.

## com.openclassrooms.chatop.services

This package contains the service classes that encapsulate the business logic of the application.

## com.openclassrooms.chatop.constraints

This package contains the constraints classes for entities and Dto.
