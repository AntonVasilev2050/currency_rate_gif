FROM adoptopenjdk/openjdk11:alpine-jre
COPY build/libs/currency_rate_gif-0.0.1-SNAPSHOT.jar currency_rate_gif.jar
ENTRYPOINT ["java","-jar","/currency_rate_gif.jar"]