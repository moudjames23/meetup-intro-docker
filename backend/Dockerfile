FROM openjdk:17-alpine3.14
WORKDIR app/
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src
RUN  ./mvnw clean package -DskipTests
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "target/spring-collab.jar"]