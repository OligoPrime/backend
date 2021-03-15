from openjdk:11-jre
run mkdir /app
copy ./target/backend-1.0-SNAPSHOT.jar /app/backend-1.0-SNAPSHOT.jar
copy ./deploy-config.yml /app/config.yml
cmd java -jar /app/backend-1.0-SNAPSHOT.jar server /app/config.yml
EXPOSE 8080 8081