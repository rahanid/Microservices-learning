
# Start with a base image containing Java runtime
FROM java:8

# Add Maintainer Info
LABEL maintainer="rahanid@gmail.com"

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port  available to the world outside this container
EXPOSE 9411

# The application's jar file
ARG JAR_FILE=zipkin-server-2.11.6-exec.jar

# Add the application's jar to the container
ADD ${JAR_FILE} zipkin-server.jar

# Run the jar file 
ENTRYPOINT ["java", "-jar", "/zipkin-server.jar"]
