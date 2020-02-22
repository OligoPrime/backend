from openjdk:11-jre
run mkdir /app
copy ./target/backend-1.0-SNAPSHOP.jar /app/backend-1.0-SNAPSHOP.jar
copy dev-config.yml /app/config.yml
run cd /app
cmd java -jar backend-1.0-SNAPSHOP.jar server config.yml