FROM eclipse-temurin:21-alpine AS build

# working directory for the container.
WORKDIR /app

# copy the maven project files for caching.
COPY mvnw /app/mvnw
COPY .mvn /app/.mvn
COPY pom.xml /app/pom.xml

# install all maven dependencies and run in batch mode to avoid stopping to accept user input.
RUN ./mvnw dependency:go-offline --batch-mode

COPY src src

RUN ./mvnw package -DskipTests

# --------------------------------------------------------------

FROM eclipse-temurin:21-alpine 

WORKDIR /app

COPY --from=build /app/target/app-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 8000

ENTRYPOINT ["java", "-jar", "app.jar"]
