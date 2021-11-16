FROM ubuntu:latest

MAINTAINER Mithun "mithun@email.com"

RUN apt-get update && apt-get install -y openjdk-11-jdk

ENV jdbcurl=jdbc:postgresql://pma-database-aws.cpvpyssdnuk1.us-east-2.rds.amazonaws.com:5432/postgres
ENV version=3.2.1-with-aws-ext-postgresDB
ENV dbuser=postgres
ENV dbpass=password321

WORKDIR /usr/local/bin/

ADD pma-app.jar .

ENTRYPOINT ["java", "-jar", "pma-app.jar"]
                                 