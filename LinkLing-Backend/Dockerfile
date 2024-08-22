FROM openjdk:21-jdk-slim
WORKDIR /app
COPY . .
RUN ./gradlew build -x test
EXPOSE 5050
CMD ["java", "-jar", "build/libs/*.jar"]