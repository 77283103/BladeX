FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER feng

RUN mkdir -p /blade/abutment

WORKDIR /blade/abutment

EXPOSE 18080

ADD ./target/blade-abutment.jar ./app.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]

CMD ["--sever.port=18080", "-Xmx128m"]
