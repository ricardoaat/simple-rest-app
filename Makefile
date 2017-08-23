NAME = simple-rest-app
VERSION=$(shell git describe --tags)
BUILD=$(shell date +%FT%T%z)
CONT_NAME=dazzling_booth
IMAGE_NAME=simplerest/tomcat

.PHONY: docker-build docker-run docker-start build deploy

deploy:
	docker cp build/libs/simple-rest-app.war $(CONT_NAME):/usr/local/tomcat/webapps/simple-rest-app.war

build:
	./gradlew build

build-deploy: build	deploy

docker-stop:
	docker stop $(CONT_NAME)

docker-start:
	docker start $(CONT_NAME)

docker-run: 
	docker run -d -p 8080:8080 --name $(CONT_NAME) $(IMAGE_NAME)

docker-build:
	docker build -t $(IMAGE_NAME) Docker/.
