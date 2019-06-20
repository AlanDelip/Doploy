FROM maven:3.6.0-jdk-8-alpine

VOLUME /root/.m2

WORKDIR /home/app

EXPOSE 8080

CMD ["sh","-c","mvn clean && mvn package && java -jar target/${artifactid}-${version}.jar"]