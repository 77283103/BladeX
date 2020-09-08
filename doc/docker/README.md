#使用docker构建工程步骤
### 1. 使用harbor作为私有库,需要配置maven,找到setting.xml( `linux可以使用find / -name settings.xml`)加入以下配置

```
<servers>
  <server>
    <id>192.168.0.157</id>
    <username>admin</username>
    <password>Harbor12345</password>
    <configuration>
      <email>smallchill@163.com</email>
    </configuration>
  </server>
</servers>

<pluginGroups>
  <pluginGroup>com.spotify</pluginGroup>  
</pluginGroups>
```

### 2. docker开启远程访问

如果没有远程访问,会报 `Connect to 192.168.0.157:2375 [/192.168.0.157] failed: Connection refused: connect`

在`/usr/lib/systemd/system/docker.service`,配置远程访问。主要是在[Service]这个部分，加上下面两个参数：

```
cd /usr/lib/systemd/system

vi docker.service

ExecStart=
ExecStart=/usr/bin/dockerd -H tcp://0.0.0.0:2375 -H unix://var/run/docker.sock
```

### 3. 配置http访问
因为docker1.3.2版本开始默认docker registry使用的是https，我们设置Harbor默认http方式，所以当执行用docker login、pull、push等命令操作非https的docker regsitry的时就会报错。  
解决办法：配置`/etc/docker/daemon.json`

```
[root@localhost harbor]# vi /etc/docker/daemon.json 
{
  "registry-mirrors": ["https://3dse7md.mirror.aliyuncs.com"]
}
```

将其修改为：

```
{
  "registry-mirrors": ["https://3dse7md.mirror.aliyuncs.com"],
  "insecure-registries":["192.168.0.157"]
}
```

### 4. 在每个需要构建子项目的pom.xml下加入配置,内容可参考如下

```
<build>
  <plugins>
    <plugin>
        <groupId>com.spotify</groupId>
        <artifactId>dockerfile-maven-plugin</artifactId>
        <configuration>
            <username>${docker.username}</username>
            <password>${docker.password}</password>
            <repository>${docker.registry.url}/${docker.namespace}/${project.artifactId}</repository>
            <tag>${project.version}</tag>
            <useMavenSettingsForAuth>true</useMavenSettingsForAuth>
            <buildArgs>
                <JAR_FILE>target/${project.build.finalName}.jar</JAR_FILE>
            </buildArgs>
            <skip>false</skip>
        </configuration>
    </plugin>
  </plugins>
</build>
```

### 5. 在每个需要构建子项目的根目录下加入Dockerfile,内容可参考如下

```
FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER smallchill@163.com

RUN mkdir -p /blade/gateway

WORKDIR /blade/gateway

EXPOSE 80

ADD ./target/blade-gateway.jar ./app.jar

CMD java -Djava.security.egd=file:/dev/./urandom -jar app.jar --spring.profiles.active=test

```

### 6. 在工程根目录的docker-compose.yml下加入配置，内容可参考如下
```
blade-gateway:
  image: "${REGISTER}/blade/blade-gateway:${TAG}"
  ports:
  - 80:80
  networks:
    blade_net:
      ipv4_address: 192.168.2.1
```
