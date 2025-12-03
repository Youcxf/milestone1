FROM eclipse-temurin:17-jdk
WORKDIR /app
# Copy your prebuilt JAR from build/libs/ into the image
COPY build/libs/cardatabase-0.0.1-SNAPSHOT.jar app.jar
# Expose the port your Spring Boot app uses (default is 8080)
EXPOSE 8080
# Run the JAR
CMD ["java", "-jar", "app.jar"]