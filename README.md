# Spring Data with Couchbase + Tests

This is my template for a spring boot project that uses spring data and couchbase. It includes integration and unit tests.

## Architecture
 
 1. **Controller:** is the presentation layer where the end points are located
 2. **Service:** is the service layer where the business logic resides
 3. **Repository:** is the persistence layer where the CRUD repository is located
 
## Technologies

1. Spring Boot (spring-boot-starter-web, spring-boot-starter-tomcat, spring-boot-starter-test, spring-boot-starter-data-couchbase)
2. Java 8
3. Tomcat 8.5.x
4. Couchbase 5.x
5. Maven

## Tests

 1. **Integration Test (for the Controller):** it uses the Spring Boot Test framework with mockMvc and hamcrest matchers
 2. **Unit Test (for the Service):** it uses the Mockito framework with hamcrest matchers, mock and injectMocks annotations 
 
## Examples

1. User -> atomic counter for key and default bucket
2. Car -> doc attributes for key and default bucket
3. Product -> unique number for key and default bucket
4. Place -> atomic counter for key and multiple buckets

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

**7. Find user by name. HTTP Method: GET**
```
http://localhost:8080/springdatacouchbase/users/find/name?name=carlos
```

**8. User exists? HTTP Method: GET**
```
http://localhost:8080/springdatacouchbase/users/1/exists
```

**9. Find all users. HTTP Method: GET**
```
http://localhost:8080/springdatacouchbase/users/find/all
```

**10. Count all users. HTTP Method: GET**
```
http://localhost:8080/springdatacouchbase/users/count/all
```

## Considerations about Couchbase
 
 * To create a primary index on the bucket.
 ```
 CREATE PRIMARY INDEX `users_primary_index` ON `users` USING GSI;
 ```
 * To delete the primary index of the bucket.
 ```
 DROP INDEX `users`.`users_primary_index` USING GSI;
 ```
  * To create a secondary index for the query 'findUsersWithName'
 ```
 CREATE INDEX `idx_user_find_by_name` ON `users` (name) WHERE type = 'com.wedevol.springdatacouchbase.core.dao.doc.UserDoc' USING GSI;
 ```
  * To delete the secondary index: idx_user_find_by_name.
 ```
 DROP INDEX `users`.`idx_user_find_by_name` USING GSI;
 ```
   * To create a secondary index for the query 'findUsersWithNickname'
 ```
 CREATE INDEX `idx_user_find_by_nickname` ON `users` (DISTINCT ARRAY x FOR x IN nicknames END) WHERE type = 'com.wedevol.springdatacouchbase.core.dao.doc.UserDoc' USING GSI;
 ```
   * To delete the secondary index: idx_user_find_by_nickname.
 ```
 DROP INDEX `users`.`idx_user_find_by_nickname` USING GSI;
 ```
 * In the UserDoc.java (@Document) we can annotate the key (@id) to be part of the json as well using @Field.
 * In the UserRepository, the CrudRepository provides sophisticated CRUD functionality for the entity class that is being managed.
 * Above Couchbase 5 the bucket name and password is the username and password located on the Security tab on the web console.
 * For doc expiration use for example: @Document(expiry = 10) or @Document(expiryExpression = "${valid.document.expiry}").
 * Key generation: UserDoc -> uses a Couchbase atomic counter. CarDoc -> uses doc attributes. ProductDoc -> uses random uuid.

## Documentation and Examples
 
* [Couchbase CRUD Repository documentation](http://docs.spring.io/spring-data/couchbase/docs/current/reference/html/#repositories.core-concepts): There you will find core concepts.
* [Spring Data and Couchbase](https://blog.couchbase.com/spring-data-couchbase-2-is-out-quick-getting-started-with-spring-initializr/): There you will find more considerations when working with spring data and couchbase.
* [Introduction to spring data and couchbase](http://www.baeldung.com/spring-data-couchbase): There you will find an introduction example.
* [Couchbase CRUD Excample](https://blog.couchbase.com/vaadin-couchbase-crud-sample/): There you will find a CRUD example.
* [Couchbase 5](https://developer.couchbase.com/documentation/server/current/introduction/whats-new.html): The new couchbase 5 is role based and there are some changes that need to be considered.
* [Spring Data Couchbase Properties](http://s-xu.blogspot.com.ar/2016/09/spring-boot-common-application.html): Spring Boot common application properties.
* [Spring Data Couchbase Documentation](https://docs.spring.io/spring-data/couchbase/docs/3.1.0.M3/reference/html/): Spring Data Couchbase Documentation.
* [Spring Boot 2 - Release Notes](https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-2.0-Release-Notes): Spring Boot 2.0 Release Notes.

## About me
I am Carlos Becerra - MSc. Softwware & Systems.  But to tell you the truth, I'd prefer to be a passionate developer. You can contact me via:

* [Google+](https://plus.google.com/+CarlosBecerraRodr%C3%ADguez)
* [Twitter](https://twitter.com/CarlosBecerraRo)

_**Any improvement or comment about the project is always welcome! As well as others shared their code publicly I want to share mine! Thanks!**_

## License
```javas
Copyright 2019 Carlos Becerra

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
