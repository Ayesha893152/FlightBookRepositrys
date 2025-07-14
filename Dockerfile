FROM eclipse-temurin:22-jdk-alpine

#workspace
WORKDIR /home/selenium-docker

#add the required files to run test
ADD target/docker-resources ./

#Environment variable
#Browser
#HubHost
#TEST_SUITE
#THREAD_COUNT

#issue the command to run the test
ENTRYPOINT java -cp 'libs/*' \
 -Dselenium.grid.enabled=${HUB_HOST} \
  -Dselenium.grid.hubHost=${Browser}
  org.testng.TestNG \
  -threadcount ${THREAD_COUNT} \
   test-suites/${TEST_SUITE}