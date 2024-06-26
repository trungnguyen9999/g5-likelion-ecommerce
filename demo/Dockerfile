#
# Build stage
#
FROM maven:3.8.4-openjdk-17 AS build
COPY . .
RUN mvn clean package -Pprod -DskipTests

#
# Package stage
#
FROM openjdk:17-jdk-slim
COPY --from=build /target/demo-0.0.1-SNAPSHOT.jar demo.jar
# ENV PORT=2004
EXPOSE 2004
ENTRYPOINT ["java","-jar","demo.jar"]