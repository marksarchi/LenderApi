# Use OpenJDK as the base image
FROM registry.access.redhat.com/ubi8/openjdk-17:latest

# Set the working directory inside the container
ENV PORT 8080

COPY build/libs/*.jar /opt/app.jar

WORKDIR /opt

# Install PostgreSQL
#RUN apt-get update && apt-get install -y postgis/postgis:12-2.5-alpine

WORKDIR /opt

ENTRYPOINT exec java $JAVA_OPTS -jar app.jar