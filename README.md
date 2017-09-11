# Simple-REST-App
---
A simple backend application made on java (Spring Boot Framework) using spring boot security with JWT. Connects to a Postgres DB running in a docker. 

## DOCKER
---
### BUILD TOMCAT DOCKER
Creates a tomcat docker container ready to run the application exposing port 8080

    make tom-build

### RUN TOMCAT DOCKER
To run the tomcat contaner use

    make tom-run

### RUN POSTGRES DOCKER
To create/run the postgres docker container use

    make db-run

### START BOTH CONTAINERS

    make farm-start

### STOP BOTH CONTAINERS

    make farm-stop

## BUILD COMMANDS
---
### BUILD
Creates a fat .war containing the app.

	make build

### BUILD AND DEPLOY

    make build-deploy

## Author
---
Ricardo Arcila%  