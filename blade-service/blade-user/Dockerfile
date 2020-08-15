FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER feng

RUN mkdir -p /blade/user

WORKDIR /blade/user

EXPOSE 8102

ADD ./target/blade-user.jar ./app.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]

CMD ["--sever.port=8102", "-Xmx128m"]
