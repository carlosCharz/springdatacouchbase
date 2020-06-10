# Changelog
All notable changes to the spring data Couchbase project will be documented in this file, in a per release basis.

## [UNRELEASED]

-------------------------------------------------------------------------------


## [r1_v13] - 2020-06-10

## Modified
- Upgrade spring boot lib from 2.2.6.RELEASE to 2.2.7.RELEASE https://github.com/spring-projects/spring-boot/releases/tag/v2.2.7.RELEASE
- Migrate tests from JUnit 4 to JUnit 5
- Code format and clean up
- Apply some UCD detector changes
- Upgrade Tomcat version to 8.5.56 t https://tomcat.apache.org/tomcat-8.5-doc/changelog.html


-------------------------------------------------------------------------------------


## [project_r1_v12] - 2019-04-11

## Modified
- Upgrade spring boot lib from 2.2.2.RELEASE to 2.2.6.RELEASE https://github.com/spring-projects/spring-boot/releases/tag/v2.2.6.RELEASE
- Code format and clean up


-------------------------------------------------------------------------------------

## [project_r1_v11] - 2019-11-09

## Modified
- Upgrade spring boot lib from 2.1.7.RELEASE to 2.2.1.RELEASE https://github.com/spring-projects/spring-boot/releases/tag/v2.2.1.RELEASE


-------------------------------------------------------------------------------------


## [project_r1_v10] - 2019-08-28

## Added
- Add new example for query: DELETE FROM ...
- Add another example for Java UUID generator for the key: PhoneDoc
- Add more links in the documentation with examples

## Modified
- Upgrade spring boot lib from 2.1.6.RELEASE to 2.1.7.RELEASE https://github.com/spring-projects/spring-boot/releases/tag/v2.1.7.RELEASE
- The service logs the newly created key
- Set a different name on the @Field annotation for the PhoneDoc
- Add Couchbase Document @NotNull validations for the ProductDoc and PhoneDoc
- Add more examples of the usage of the template N1ql queries
- Minor code cleanup

## Fixed
- Fix the hashCode and equals methods
- Fix HTTP response statuses
- Fix silent exceptions Spring Data Couchbase hide


-------------------------------------------------------------------------------------


## [project_r1_v9] - 2019-07-03

## Modified
- Upgrade spring boot lib from 2.1.5.RELEASE to 2.1.6.RELEASE https://github.com/spring-projects/spring-boot/releases/tag/v2.1.6.RELEASE


-------------------------------------------------------------------------------------


## [project_r1_v8] - 2018-05-25

## Modified
- upgrade spring boot from 2.1.3.RELEASE to 2.1.5.RELEASE https://github.com/spring-projects/spring-boot/releases/tag/v2.1.5.RELEASE

-------------------------------------------------------------------------------------


## [project_r1_v7] - 2018-03-27

## Modified
- upgrade spring boot from 2.1.2.RELEASE to 2.1.3.RELEASE

-------------------------------------------------------------------------------------


## [project_r1_v6] - 2018-02-02

## Modified
- upgrade spring boot from 2.1.0.RELEASE to 2.1.2.RELEASE
- remove @ResponseBody annotation because the @RestController already includes that

-------------------------------------------------------------------------------------


## [project_r1_v5] - 2018-11-19

## Modified
- branch multiple buckets integrated in main repo
- upgrade spring boot from 2.0.6.RELEASE to 2.1.0.RELEASE https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-2.1.0-M1-Release-Notes

-------------------------------------------------------------------------------------


## [project_r1_v4] - 2018-07-07

## Added
- configuration to support multiple buckets with examples: controller, service, repo, counter, configuration

-------------------------------------------------------------------------------------


## [project_r1_v3] - 2018-06-29

## Added
- secondary indexes: idx_user_find_by_nickname, idx_user_find_by_name
- examples of key generation: UserDoc -> uses a Couchbase atomic counter. CarDoc -> uses doc attributes. ProductDoc -> uses random uuid.

## Modified
- upgrade spring boot to 2.0.3

-------------------------------------------------------------------------------------


## [project_r1_v2] - 2018-05-01
**Tag**: project_r1_v2

### Added
- add google formatter

## Modified
- upgrade spring boot to 2.0.1

-------------------------------------------------------------------------------------

## [project_r1_v1] - 2018-03-30
**Tag**: project_r1_v1

### Added
- upgrade to spring boot 2
- add changelog file

### Fixed
- spring boot servlet initializer package
- MockitoJUnitRunner package
- junit tests
- findOne crud repository (the api changed)
- exists crud repository (the api changed)
- delete crud repository (the api changed)
