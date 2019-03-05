FROM java:openjdk-8-jre-alpine
WORKDIR /opt/app

ADD app.jar app.jar

ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

ENV JVM_XMS 128m
ENV JVM_XMX 512m
ENV JAVA_OPTS -server -Xloggc:/opt/app/logs/gc.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Dfile.encoding=UTF8 -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/opt/app/logs/java.hprof -Djava.security.egd=file:/dev/urandom
ENV SPRING_PROFILE deploy

EXPOSE 8080
ENTRYPOINT java $JAVA_OPTS -Xms$JVM_XMS -Xmx$JVM_XMX -jar /opt/app/app.jar --spring.profiles.active=$SPRING_PROFILE