FROM eclipse-temurin:17.0.6_10-jre-alpine

ENV APP_PROFILE=prod
ENV DB_URL=jdbc:postgresql://pgsql:5432/postgres
ENV DB_USR=postgres
ENV DB_PWD=postgres

COPY target/UserSubscriptions*.jar /opt/app/japp.jar

CMD ["java", "-jar", "-Dspring.profiles.active=${APP_PROFILE}", "-Dspring.datasource.url=${DB_URL}", "-Dspring.datasource.username=${DB_USR}", "-Dspring.datasource.password=${DB_PWD}", "/opt/app/japp.jar"]

EXPOSE 8080