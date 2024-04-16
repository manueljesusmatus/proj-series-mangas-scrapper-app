FROM eclipse-temurin:17-jdk-alpine
LABEL maintainer="mmatush <mmatushdeveloper@gmail.com>"
WORKDIR /app

ENV TZ=America/Santiago

RUN apk update  \
    && apk --no-cache add bash  \
    && apk --no-cache add curl

COPY crontab /etc/cron.d/crontab
ADD . /app

RUN crontab /etc/cron.d/crontab
CMD ["/usr/sbin/crond", "-f"]

COPY target/*.jar app.jar
ENTRYPOINT ./start.sh