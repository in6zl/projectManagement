FROM openjdk:8-jdk-alpine

VOLUME /home/jenkins/home/workspace/esc-server/

ENV APP_NAME esc-server
ENV PORT 8080
COPY 05_Implementation/$APP_NAME/target/$APP_NAME'-V1.0.0.jar' /$APP_NAME'.jar'

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /$APP_NAME.jar"]
