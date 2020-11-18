FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER feng

RUN mkdir -p /blade/cases

WORKDIR /blade/cases

EXPOSE 8108

ADD ./target/blade-cases.jar ./app.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]

CMD ["--sever.port=8108", "-Xmx128m"]
