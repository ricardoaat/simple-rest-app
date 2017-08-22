FROM tomcat

MAINTAINER Ric Arcila <ricardoarcila@outlook.com>

WORKDIR /usr/local/tomcat

COPY tomcat-users.xml /usr/local/tomcat/conf/tomcat-users.xml
COPY context.xml /usr/local/tomcat/webapps/manager/META-INF/context.xml
ADD ../build/libs/simple-rest-app.war /usr/local/tomcat/webapps/simple-rest-app.war

EXPOSE 8888
