FROM java:8-jre

WORKDIR /usr/src/app
COPY *.jar ./app.jar

RUN	chown -R micro-shop:orders ./app.jar

USER micro-shop

ENTRYPOINT ["/usr/local/bin/java.sh","-jar","./app.jar", "--port=80"]