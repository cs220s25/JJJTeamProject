#!/bin/bash
FROM amazonlinux

WORKDIR /JJJTeamProject

COPY . .
RUN yum install -y maven-amazon-corretto21
RUN mvn clean package

CMD ["java", "-jar","target/dbot-1.0-SNAPSHOT.jar"]
