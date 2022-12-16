FROM openjdk:17
EXPOSE 5000
RUN mkdir -p /app/
ADD build/libs/demo-0.0.1-SNAPSHOT.jar /app/demo-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/app/demo-0.0.1-SNAPSHOT.jar"]

# Build container with params 
# -p 5000:5000 --add-host:host.docker.internal:host-gateway 
# for internal testing