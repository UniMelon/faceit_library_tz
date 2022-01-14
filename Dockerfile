FROM openjdk:17-oracle
EXPOSE 8080
ADD target/library.jar library.jar
ENTRYPOINT ["java", "-jar", "library.jar"]