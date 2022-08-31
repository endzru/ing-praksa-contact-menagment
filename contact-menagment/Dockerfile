FROM openjdk:18-oracle
EXPOSE 8080
ADD target/contact-menagment-0.0.1-SNAPSHOT.jar /app.jar
CMD java -jar /app.jar
FROM postgres:latest
ADD DockerDB/init.sql /docker-entrypoint-initdb.d/

