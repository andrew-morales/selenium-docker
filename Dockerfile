FROM openjdk:8u191-jre-alpine3.8

RUN apk add curl jq

#workspace
WORKDIR /usr/share/udemy

#ADD .jar files under target locatioin from host
#into this image
ADD target/selenium-docker.jar       selenium-docker.jar
ADD target/selenium-docker-tests.jar selenium-docker-tests.jar
ADD target/libs					     libs

#incase of any other dependency like .csv /.json etc..
#ADD also

#ADD suite files from host
ADD book-flight-module.xml 			 book-flight-module.xml
ADD search-module.xml	   			 search-module.xml

#ADD health check script
ADD healthcheck.sh 		   			 healthcheck.sh

#browser
#hot
#module
ENTRYPOINT sh healthcheck.sh 
