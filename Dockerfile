FROM java:8-jre
ADD target/*.jar .
ENTRYPOINT ["/usr/local/bin/java.sh","-jar","./app.jar", "--port=80"]