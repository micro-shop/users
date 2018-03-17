FROM java:8-jre
ADD target/users-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java","-jar","./users-0.0.1-SNAPSHOT.jar", "--port=80"]