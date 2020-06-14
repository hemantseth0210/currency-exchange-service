# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Add Maintainer info
LABEL maintainer hemant.seth02@gmail.com

# Create the app directory
RUN mkdir -p /usr/app

# Set working directory to app
WORKDIR /usr/app

# Copy the jar file into container image under the app directory
#COPY ./target/spring-recipe-app-0.0.3.jar /usr/app/spring-recipe-app.jar
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar


# Expose port 8000 to the world outside this container
#EXPOSE 8000

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]