FROM tomcat

MAINTAINER Ric Arcila <ricardoarcila@outlook.com>

RUN apt-get update && apt-get -y upgrade

WORKDIR /usr/local/tomcat/bin

ENV JPDA_ADDRESS=5005
ENV JPDA_TRANSPORT=dt_socket

COPY build/libs/simple-rest-app.war /usr/local/tomcat/webapps/

EXPOSE 5005 8080
ENTRYPOINT ["catalina.sh", "jpda", "run"]