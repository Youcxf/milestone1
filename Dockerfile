# Use lightweight Java 17 image
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy everything into container
COPY . .

# Give gradlew permission
RUN chmod +x gradlew

# Build the app
RUN ./gradlew build

# Run the JAR
CMD ["java", "-jar", "build/libs/milestone1-0.0.1-SNAPSHOT.jar"]
