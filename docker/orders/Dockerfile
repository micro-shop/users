FROM java:8-jre

WORKDIR /usr/src/app
COPY *.jar ./app.jar

RUN	chown -R microshop:orders ./app.jar

USER microshop

ENTRYPOINT ["/usr/local/bin/java.sh","-jar","./app.jar", "--port=80"]