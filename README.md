# Doploy

Doploy is a Docker-based configuration tool that helps 
deploy the backend of a web application in a faster and simpler way.

## Features
- Normal features including receiving requests, redirecting logic, dependency injection, 
 object relation mapping(ORM), data IO, and etc. 
 
- Detailed code examples and comments

- Unit testing, interface testing, and integration testing

- Swagger2 HTTP API management(can be accessed at *localhost:8080/swagger-ui.html* in default)
![swagger](https://c1.staticflickr.com/5/4915/31726275207_42bb23af9c_h.jpg)

- Code quality with SonarQube(can be accessed at *localhost:9000* in default)
![sonar management](http://mooctest.oss-cn-shanghai.aliyuncs.com/resources/springboot-tmpl/sonar-management.png)

## Package Architecture
> This project is using [Anemic Domain Model](https://martinfowler.com/bliki/AnemicDomainModel.html) and based on the IoC framework, 
decoupling services and highly recommendable for team work.\
Moreover, the architecture borrows ideas from micro-service, containing features like highly deployable, and extensible.
*marks for features under planning

- **web** \
    Receiving, handling, filtering, wrapping, redirecting requests. The connector between frontend and backend.
    - **ctrl**
        - receiving requests from the frontend, generating online documents using swagger annotations
        - potential session management
        - \* in a JWT solution, key info is extracted and filtered here
    - **model**
        - traditional value object and minimal object used for interaction with the frontend wrapped by wrappers
     
- **logic**\
    business logic, arranging service for the core business logic

- **service**\
    consist of IO, computational and persistence operations.
    services are independent.
    logic inference is required if multiple services are participating

- **entity**\
    entities that need to be persisted and correspond to repositories
    
- **repository**\
    a traditional DAO layer, exchanging data using JPA
    
- **exception**\
    exceptions can be defined with customized process
    
- **config**\
    configurations for the framework and third-party plugins

## Keep In Touch
- Clone / Fork / Star are warmly welcomed
- Discussions in issues are warmly welcomed
- Visit my [homepage](https://www.alan-zhufengxu.com) for more top-notch projects 