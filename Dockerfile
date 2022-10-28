FROM openjdk:17

EXPOSE 8080

ENV url=# username=# password=#

ADD target/librebnb.jar librebnb.jar

ENTRYPOINT ["java", "-jar", "/librebnb.jar"]