FROM openjdk:17-jdk-slim
WORKDIR /app
COPY . .
RUN chmod +x ./gradlew
RUN ./gradlew build -x test
EXPOSE 5050
CMD ["java", "-jar", "build/libs/LinkLingProject_01-0.0.1-SNAPSHOT.jar"]