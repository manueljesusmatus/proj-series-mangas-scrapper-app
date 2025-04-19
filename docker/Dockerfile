FROM eclipse-temurin:17-jdk-alpine
LABEL maintainer="mmatush <mmatushdeveloper@gmail.com>"
WORKDIR /app

ENV TZ=America/Santiago

RUN apk update  \
    && apk --no-cache add bash  \
    && apk --no-cache add curl

ADD . /app

COPY target/*.jar app.jar
ENTRYPOINT ./start.sh