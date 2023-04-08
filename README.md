
# **Social Media App**

## Description

This is a Spring RESTful application that allows users to create an account, create a profile, make posts, comment on posts, react to posts, and follow other users.

## **Getting Started**

## Prerequisites

* Java 17 or later
* Maven
* Postgresql

## Installing

1. Clone the repository to your local machine.
2. Navigate to the project directory.
3. Run mvn clean install to build the project.
4. Run the application using your preferred IDE or by running java -jar target/social-media-app.jar from the command line.

## Usage

### To use the application, follow these steps:

1. Register a new user account using the `/api/v1/auth/register` endpoint.
2. Log in to your account using the` /api/v1/auth` endpoint.
3. Create a user profile using the `/api/v1/profiles` endpoint.
4. Make a post using the `/api/v1/post` endpoint.
5. Comment on a post using the `/api/v1/comments/create` comments endpoint.
6. React to a post using the `/api/v1/reactions/create` endpoint.
7. Follow another user using the `/api/v1/follows/{followId}/user/{authId}` endpoint.
8. To get all the posts of all the user i follow `/api/v1/post/{authId}` endpoint.


## API Documentation
The API documentation is available at http://localhost:8080/swagger-ui/index.html when the application is running.

## Built With
* Spring Boot - The web framework used
* Maven - Dependency management
* Postgresql - Database
## Author
Miko Cañares - Initial work
## License
This project is licensed under the MIT License - see the [LICENSE.md](LICENSE) file for details.


