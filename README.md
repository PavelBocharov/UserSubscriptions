# User Subscriptions
## Build JAR
```bash
mvn clean install -U
```
## Start local JAR (SQLite database)
```bash
java -jar ./target/UserSubscriptions*.jar
```
```bash
java -jar -Dspring.profiles.active=local ./target/UserSubscriptions*.jar
```

## Build Docker image
```bash
docker build --no-cache -t marolok/users_subs:1.0.0 .
```

## Start docker-compose with PostgreSQL
```bash
docker-compose up
```