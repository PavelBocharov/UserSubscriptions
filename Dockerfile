FROM eclipse-temurin:17.0.6_10-jre-alpine

ENV APP_PROFILE=prod

COPY target/UserSubscriptions*.jar /opt/app/japp.jar

CMD ["java", "-jar", "-Dspring.profiles.active=${APP_PROFILE}", "/opt/app/japp.jar"]

EXPOSE 8080