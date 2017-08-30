NAME = simple-rest-app
VERSION=$(shell git describe --tags)
BUILD=$(shell date +%FT%T%z)
CONT_NAME=dazzling_booth
DB_CONT_NAME=objective_darwin
IMAGE_NAME=simplerest/tomcat

.PHONY: docker-build docker-run docker-start build deploy

deploy:
	docker cp build/libs/simple-rest-app.war $(CONT_NAME):/usr/local/tomcat/webapps/simple-rest-app.war

build:
	./gradlew build

build-deploy: build	deploy

tom-stop:
	docker stop $(CONT_NAME)

tom-start:
	docker start $(CONT_NAME)
	docker container logs -f dazzling_booth

tom-run: 
	docker run -d -p 8080:8080 --name $(CONT_NAME) --link $(DB_CONT_NAME):postgres $(IMAGE_NAME)

tom-build:
	docker build -t $(IMAGE_NAME) Docker/.

db-run:
	docker run -d -p 5432:5432 --name $(DB_CONT_NAME) postgres

db-start:
	docker start $(DB_CONT_NAME)

db-stop:
	docker stop $(DB_CONT_NAME)


farm-start: db-start tom-start	

farm-stop: tom-stop db-stop