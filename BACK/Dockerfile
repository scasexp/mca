FROM openjdk:11
VOLUME /tmp
EXPOSE 5000
ADD target/test-0.0.1-SNAPSHOT.jar test-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/test-0.0.1-SNAPSHOT.jar"]
