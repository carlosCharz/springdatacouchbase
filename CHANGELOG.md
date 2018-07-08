# Changelog
All notable changes to the spring data couchbase project will be documented in this file, in a per release basis.

## [project_r1_v5] - unreleased

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
