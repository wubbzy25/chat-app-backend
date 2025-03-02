FROM gradle:7.4-jdk17 AS build
WORKDIR /app
COPY build.gradle settings.gradle /app/
RUN gradle clean # Clean the project
COPY src /app/src
RUN gradle build --no-daemon --info

FROM amazoncorretto:17-alpine-jdk
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
