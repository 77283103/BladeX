FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER feng

RUN mkdir -p /blade/system

WORKDIR /blade/system

EXPOSE 8106

ADD ./target/blade-system.jar ./app.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]

CMD ["--sever.port=8106", "-Xmx128m"]
