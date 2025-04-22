FROM amazonlinux

RUN yum install -y maven-amazon-corretto21
WORKDIR /app
COPY . .

RUN mvn clean package  

CMD ["java", "-jar", "target/dbot-1.0-SNAPSHOT.jar"]
