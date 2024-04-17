java -Xms256m -Xmx512m \
    -Dcom.sun.management.jmxremote=true \
    -Dcom.sun.management.jmxremote.port=9085 \
    -Dcom.sun.management.jmxremote.authenticate=false \
    -Dcom.sun.management.jmxremote.ssl=false \
    -Djava.rmi.server.hostname=localhost \
    -Dcom.sun.management.jmxremote.rmi.port=9085 \
    -Dspring.profiles.active=qa \
    -jar app.jar