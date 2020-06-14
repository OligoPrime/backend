# Installation for BioPrime backend

## Recommended specifications
 - OS: Ubuntu 20.4 LTS
 - RAM: 4Gb
 - CPU: 4 cores
 - Storage: 100Gb

## Installation

Make sure you have the latest version of Docker and Docker Compose installed.

Then create a `docker-compose.yml` file and copy the below configuration. Change the username, password and optionally ports. 
Do not change the name of the services.

```yaml
version: '3'
services:
  frontend:
    image: "bioprime/frontend:latest"
    ports:
      - "80:80"
  backend:
    image: "bioprime/backend:latest"
    environment:
      - DB_USER=postgres
      - DB_PW=postgres
      - JDBC_URL=jdbc:postgresql://db/bioprime
    ports:
      - "8080:8080"
  db:
    image: postgres
    environment:
      - POSTGRES_USER=postgres
      - POSTGRES_PASSWORD=postgres
      - POSTGRES_DB=bioprime
    ports:
      - "5433:5432"
    volumes:
      - pgdata:/var/lib/postgresql/data
volumes:
  pgdata:
```

To check if backend is running go to http://localhost:8081/healthcheck
To check if frontend is running go to http://localhost:80/

## Updating the application
To update the containers run the following commands.
```shell script
sudo docker-compose stop
sudo docker-compose rm -f
sudo docker-compose pull
sudo docker-compose up -d
```


How to compile and run the application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/backend-1.0-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`

