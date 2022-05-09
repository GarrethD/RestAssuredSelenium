FROM maven:3.5.3-jdk-8-alpine
COPY ./. /
EXPOSE 8080
RUN mvn clean test "-Dsurefire.suiteXmlFiles=TestNG_Runner_API.xml"
