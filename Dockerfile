# Use official OpenJDK 17 image
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy everything
COPY . .

# Build the project
RUN ./gradlew build

# Run the built JAR
CMD ["java", "-jar", "build/libs/milestone1-0.0.1-SNAPSHOT.jar"]
