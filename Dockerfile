# -----------------------------
# STAGE 1: Build the Application
# -----------------------------
FROM eclipse-temurin:17-jdk-jammy AS builder
WORKDIR /app

# Copy the Gradle wrapper and settings
COPY . .

# Grant execution permission to the Gradle wrapper (Crucial for Linux)
RUN chmod +x gradlew

# Build the application (skipping tests to save time during deploy)
RUN ./gradlew bootJar -x test --no-daemon

# -----------------------------
# STAGE 2: Run the Application
# -----------------------------
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app

# Copy the JAR file from the builder stage
# We use *.jar so the name doesn't matter (cardatabase vs milestone1)
COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]