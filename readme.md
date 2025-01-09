# Chatop App

## Technologies Used
- `Spring Boot`: Framework for creating the application.
- `Maven`: Build tool for dependency management.
- `JPA`: For object-relational mapping with the entities.
- `AWS S3`: Integrated for storage purposes.
- `Spring Security`: For authentication and authorization.
- `Swagger/OpenAPI`: For API documentation.

---

# General Notes
## Key Features:
- `Separation of Concerns`: Each layer is dedicated to a specific responsibility, making the code easier to understand and maintain.
- `DTOs and Mappers`: Ensures a clear separation between the internal data model (entities) and the data exposed via APIs (DTOs).
- `Configuration Files`: Provides a centralized approach to manage application settings.

## Install Project
### Download back-end
```
git clone https://github.com/ay63/ocr-p-3-back.git
```
## Run projet
```
 ./mvnw spring-boot:run
```
## Build documentation
```
mvn javadoc:javadoc
```
### Download frontend project and resources(postman,sql and mockoon)
**Then follow the readme in to install project** 
```
https://github.com/OpenClassrooms-Student-Center/Developpez-le-back-end-en-utilisant-Java-et-Spring.git
```

---

# Project Package Structure

## com.openclassrooms.chatop.configuration

This package contains classes responsible for the application’s configuration.

- `OpenApiConfig`: Configures the OpenAPI/Swagger documentation for the project.
- `S3ClientConfig`: Handles the configuration for AWS S3 client integration.
- `SpringSecurityConfig`: Manages the security configuration, including authentication and authorization mechanisms.

## com.openclassrooms.chatop.controllers

This package holds the REST controllers that manage HTTP requests and responses.

- `AuthController`: Manages authentication and authorization endpoints.

- `MessageController`MessageController: Handles endpoints related to message operations.

- `RentalController`: Manages rental-related operations.

- `UserController`: Handles user-related operations, such as registration and profile management.

## com.openclassrooms.chatop.dto

This package contains data transfer objects (DTOs) used for communication between the application and the client.

### Sub-packages:
#### ental:
- `RentalDto`: Represents a rental data structure used in requests and responses.
- `RentalDtoAbstract`: Abstract base class for rental-related DTOs.
- `RentalResponseDto`: Used for rental-related responses.
- `RentalUpdateDto`: Represents rental updates.
#### user:
- `MessageDto`: Represents a message object.
- `UserDto`: Represents a user object used for various user-related operations.
- `UserLoginDto`: Used for login-related operations.
- `UserRegisterDto`: Used for user registration operations.

## com.openclassrooms.chatop.mapper.implementation

This package contains mapper implementations for converting between entities and DTOs.

### Sub-packages:
#### message:
 - `MessageDtoMapperImpl`: Maps Message entities to MessageDto objects and vice versa.
#### rental:
- `RentalDtoMapperImpl`: Maps Rental entities to RentalDto objects and vice versa.
- `RentalResponseDtoMapperImpl`: Maps rental entities to RentalResponseDto.
#### user:
- `UserDtoMapperImpl`: Maps User entities to UserDto.
- `UserLoginDtoMapperImpl`: Maps login requests to user entities.
- `UserRegisterDtoMapperImpl`: Maps user registration requests to user entities.
- `DTOMapper`: Acts as a central interface for all mapper implementations.

## com.openclassrooms.chatop.entities

This package contains the core JPA entity classes that represent the database tables.

- `Message`: Represents the message entity in the database.
- `Rental`: Represents the rental entity in the database.
- `User`: Represents the user entity in the database.

## com.openclassrooms.chatop.repositories

This package contains the repository interfaces for database operations.

- `MessageRepository`: Repository for managing Message entities.
- `RentalRepository`: Repository for managing Rental entities.
- `UserRepository`: Repository for managing User entities.

## com.openclassrooms.chatop.services

This package contains the service classes that encapsulate the business logic of the application.

- `DateService`: Provides date and time-related utilities.
- `FileService`: Handles file-related operations, such as uploading and retrieving files.
- `JwtService`: Manages JSON Web Tokens (JWT) for authentication and authorization.
- `MessageService`: Contains the business logic for managing messages.
- `PasswordService`: Handles password encryption and validation.
- `RentalService`: Implements business logic for rental-related operations.
- `UserService`: Manages user-related operations such as registration, login, and profile updates.