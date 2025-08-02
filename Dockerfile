FROM eclipse-temurin:22-jdk-alpine

#workspace
WORKDIR /home/selenium-docker

#add the required files to run test
ADD target/docker-resources ./
ADD Runner.sh      Runner.sh

# Fix for windows
RUN dos2unix runner.sh

