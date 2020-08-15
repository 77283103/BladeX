FROM bladex/alpine-java:8_server-jre_cn_unlimited

MAINTAINER smallchill@163.com

RUN mkdir -p /blade/design

WORKDIR /blade/design

EXPOSE 9008

ADD ./target/blade-bpmnjs-design.jar ./app.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]

CMD ["--sever.port=9008", "-Xmx128m"]
