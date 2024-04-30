# Manga Metadata Downloader API

## Descripción
Este proyecto es una API REST desarrollada en Spring Boot que permite descargar metadatos de mangas utilizando la biblioteca HtmlUnit para simular un navegador y acceder a los sitios web de manga.

## Requisitos Previos
- Java JDK 17 o superior instalado

## Instalación y Ejecución
1. el proyecto usando Maven:
   ```bash
   mvn clean install
   ```
2. Ejecuta la aplicación Spring Boot:
   ```bash
   java -jar target/serie-1.0.3.jar
   ```

## Uso
- La API proporciona endpoints RESTful para descargar metadatos de mangas.

## Contribución
¡Las contribuciones son bienvenidas! Si deseas contribuir a este proyecto, sigue estos pasos:
1. Haz un fork del repositorio.
2. Crea una nueva rama (`git checkout -b feature/nueva-caracteristica`).
3. Haz tus cambios y realiza commit (`git commit -am 'Agrega una nueva característica'`).
4. Sube tus cambios (`git push origin feature/nueva-caracteristica`).
5. Abre un Pull Request.

## Configuración de Log4j2
La configuración de Log4j2 se puede personalizar a través de una propiedad log.level en el archivo application.properties. Esta propiedad permite cambiar el nivel de registro para controlar qué eventos se registran y cuáles se omiten. Los niveles de registro disponibles son: TRACE, DEBUG, INFO, WARN, ERROR y FATAL.

## Visual VM
Puedes observar información como uso de CPU, memoria, hilos y clases cargadas en la JVM mediante VisualVM. Para ello ejecuta la aplicación con los siguientes argumentos:
   ```bash
   java \
        -Dcom.sun.management.jmxremote=true \
        -Dcom.sun.management.jmxremote.authenticate=false \
        -Dcom.sun.management.jmxremote.ssl=false \
        -Dcom.sun.management.jmxremote.port=${PORT} \
        -Djava.rmi.server.hostname=${HOST} \
        -Dcom.sun.management.jmxremote.rmi.port=${PORT}
        -jar app.jar
   ```
** Si Dockerizas el jar recuerda exponer el puerto por el cual realizarás la conexión JMX.