FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER feng

RUN mkdir -p /blade/contract

WORKDIR /blade/contract

EXPOSE 8107

ADD ./target/blade-contract.jar ./app.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]

CMD ["--sever.port=8107"]
