FROM adoptopenjdk/openjdk11:alpine
MAINTAINER franklimPaulino
RUN apk update
RUN apk add maven
COPY pom.xml /usr/local/service/pom.xml
COPY src /usr/local/service/src
WORKDIR /usr/local/service/src
RUN cd .. && mvn package -DskipTests
RUN echo "172.30.0.2 localhost" | tee -a /etc/host
HEALTHCHECK --interval=5m --timeout=2m --start-period=2m CMD --fail http://localhost:8080 || exit 1
# COPY ./target/fretes-1.0-SNAPSHOT.jar fretes-1.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","../target/fretes-1.0-SNAPSHOT.jar"]