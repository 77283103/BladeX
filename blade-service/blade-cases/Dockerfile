FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER feng

RUN mkdir -p /blade/case

WORKDIR /blade/case

EXPOSE 8108

ADD ./target/blade-case.jar ./app.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]

CMD ["--sever.port=8107", "-Xmx128m"]
