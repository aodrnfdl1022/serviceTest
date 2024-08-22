FROM openjdk:17-jdk-slim
WORKDIR /workspace
COPY . .
RUN chmod +x ./gradlew
RUN ./gradlew build -x test
EXPOSE 5050
CMD ["java", "-jar", "build/libs/*.jar"]