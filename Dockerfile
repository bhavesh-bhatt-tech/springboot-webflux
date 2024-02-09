FROM openjdk:17
EXPOSE 8080
ADD target/springboot-webflux.jar springboot-webflux.jar
ENTRYPOINT ["java", "-jar", "springboot-webflux.jar"]