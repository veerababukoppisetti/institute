# Use openjdk's 8-jre as the base image
FROM openjdk:8-jre

LABEL maintainer = "institute.management"
# Copy the artifact jar
ADD target/institute-management-system-0.0.1-SNAPSHOT.jar institute-management-system.jar

ENTRYPOINT ["java","-jar","institute-management-system.jar"]