FROM java:8-jre
ADD target/*.jar .
ENTRYPOINT ["java","-jar","./app.jar", "--port=80"]