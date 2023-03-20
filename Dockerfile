FROM openjdk:11
LABEL app=order
LABEL arch=Backend
EXPOSE 4321
COPY build/libs/order-0.0.1-SNAPSHOT.jar /app/spring-boot-application.jar
ENTRYPOINT ["java","-jar","/app/spring-boot-application.jar"]