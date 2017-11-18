# Spring Data with Couchbase

This is my template for a spring boot project that uses spring data and couchbase.

## Architecture
 
 1. **Controller:** is the presentation layer where the end points are located
 2. **Service:** is the service layer where the business logic resides
 3. **Repository:** is the persistence layer where the CRUD repository is located
 
## Technologies

1. Spring Boot (spring-boot-starter-web, spring-boot-starter-tomcat, spring-boot-starter-test, spring-boot-starter-data-couchbase)
2. Java 8
3. Tomcat 8.5
4. Couchbase 5
5. Maven

## Unit Tests

 1. **For the Controller:** it uses the Spring Boot Test framework with mockMvc and hamcrest matchers
 2. **For the Service:** it uses the Mockito framework with hamcrest matchers and mock and injectMocks annotations 
 
## Exposed methods

**1. Get user by id. HTTP Method: GET**
```
http://localhost:8080/springdatacouchbase/users/1
```

**2. Create a user. HTTP Method: POST**
```
http://localhost:8080/springdatacouchbase/users
```
```
{
  "name": "Carlos",
  "nicknames": ["charz"],
  "age": 25,
  "email": "carlos1@yopmail.com"
}
```

**3. Update a user. HTTP Method: PUT**
```
http://localhost:8080/springdatacouchbase/users/1
```
```
{
  "name": "Carlos2",
  "age": 26
}
```

**4. Delete a user. HTTP Method: DELETE**
```
http://localhost:8080/springdatacouchbase/users/1
```

**5. Find user by email. HTTP Method: GET**
```
http://localhost:8080/springdatacouchbase/users/find/email?email=carlos1@yopmail.com
```

**6. Find user by nickname. HTTP Method: GET**
```
http://localhost:8080/springdatacouchbase/users/find/nickname?nickname=charz
```

**7. User exists? HTTP Method: GET**
```
http://localhost:8080/springdatacouchbase/users/1/exists
```

**8. Find all users. HTTP Method: GET**
```
http://localhost:8080/springdatacouchbase/users/find/all
```

**9. Count all users. HTTP Method: GET**
```
http://localhost:8080/springdatacouchbase/users/count/all
```

## Considerations about couchbase
 
 * To create a primary index on the bucket.
 ```
 CREATE PRIMARY INDEX `users-primary-index` ON `users` USING GSI;
 ```
 * To delete the primary index of the bucket.
 ```
 DROP INDEX `users`.`users-primary-index` USING GSI;
 ```
 * In the UserDoc.java (@Document) we can annotate the key (@id) to be part of the json as well using @Field.
 * In the UserRepository, the CrudRepository provides sophisticated CRUD functionality for the entity class that is being managed.
 * For couchbase 5 the bucket name and password is the username and password. You can check it out in tab Security of the web console.

## Documentation and Examples
 
* [Couchbase CRUD Repository documentation](http://docs.spring.io/spring-data/couchbase/docs/current/reference/html/#repositories.core-concepts): There you will find core concepts.
* [Spring Data and Couchbase](https://blog.couchbase.com/spring-data-couchbase-2-is-out-quick-getting-started-with-spring-initializr/): There you will find more considerations when working with spring data and couchbase.
* [Introduction to spring data and couchbase](http://www.baeldung.com/spring-data-couchbase): There you will find an introduction example.
* [Couchbase CRUD Excample](https://blog.couchbase.com/vaadin-couchbase-crud-sample/): There you will find a CRUD example.
* [Couchbase 5](https://developer.couchbase.com/documentation/server/current/introduction/whats-new.html): The new couchbase 5 is role based and there are some changes that need to be considered.

## About me
I am Carlos Becerra - MSc. Softwware & Systems.  But to tell you the truth, I'd prefer to be a passionate developer. You can contact me via:

* [Google+](https://plus.google.com/+CarlosBecerraRodr%C3%ADguez)
* [Twitter](https://twitter.com/CarlosBecerraRo)

_**Any improvement or comment about the project is always welcome! As well as others shared their code publicly I want to share mine! Thanks!**_

## License
```javas
Copyright 2017 Carlos Becerra

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
