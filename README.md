Java-Stations-Test
===================

## Introduction
This application is built using Maven, Spring, Jetty, Hibernate and Jersey.

## Prerequisite
In order to build and run the application, you will need following prerequisites

1. JDK 1.8
2. Maven 3+

## Build
Please run "mvn clean install" to build the application

## Testing
Please run "mvn clean install" to build the application and test the application

## Run
1. Please run "mvn jetty:run" to run the application
2. You can make following REST service calls:

  a. **Search Station:** http://localhost:8080/station/search/{name}  
  b. **All Stations:** http://localhost:8080/station/searchAll  
  c. **Health Check:** http://localhost:8080/station/health

## Assumptions
To provide the best possible solution, I have assumed some things as I went along impelemnting the solution, these assumptions are listed below:

1. To include test data, I am inserting/saving it in the database via Static block in the StationRestService, in the actual application, we shouldn't be doing this.
2. Any start/stop scripts are out of scope.
