FROM java:8-jre
ADD target/orders-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java","-jar","./orders-0.0.1-SNAPSHOT.jar", "--port=80"]