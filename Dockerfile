FROM maven:3.6-jdk-11 AS build
COPY . /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package -Dmaven.test.skip

FROM openjdk:11-slim-buster
COPY --from=build /usr/src/app/recipe-manager-api-boot/target/recipe-manager-api-boot-0.0.1-SNAPSHOT.jar /usr/app/recipe-manager-api-boot/target/recipe-manager-api-boot-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD [ "sh", "-c", "java -jar /usr/app/recipe-manager-api-boot/target/recipe-manager-api-boot-0.0.1-SNAPSHOT.jar" ]