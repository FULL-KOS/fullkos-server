# Start with a base image containing Java runtime
FROM openjdk:17.0.1-jdk

# Add Author info
LABEL maintainer="kbm4250@naver.com"

# Add a volume to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8080

# The application's jar file
ARG JAR_FILE=build/libs/dish-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} dish.jar

# Run the jar file
ENTRYPOINT ["java","-jar","/dish.jar"]