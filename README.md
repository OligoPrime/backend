# Installation for BioPrime backend

## Recommended specifications
 - OS: Ubuntu 20.4 LTS
 - RAM: 4Gb
 - CPU: 4 cores
 - Storage: 100Gb

## Database
BioPrime uses PostgreSQL for its database. We don't require any customisations during the installation, so you can
install it using any online guide. We recommend this one: 
https://www.digitalocean.com/community/tutorials/how-to-install-and-use-postgresql-on-ubuntu-20-04

The only requirement for the database is a dedicated role and a dedicated database for the application.

After installing and configuring the database make sure is it accessible locally and from outside if the database is 
on another server.

## Java application

The java application runs inside a docker container, so make sure you have Docker installed.
https://www.digitalocean.com/community/tutorials/how-to-install-and-use-docker-on-ubuntu-20-04

The application reads the database configuration from environment variables of the docker image.
We recommend you to create a file (`env.list`) and configure the environment variables there. 
Place the file into `/home/bioprime/`.

```
DB_USER=USERNAME
DB_PW=PASSWORD
JDBC_URL=jdbc:postgresql://IP:PORT/DATABASE
// EXAMPLE JDBC_URL=jdbc:postgresql://198.51.100.1/bioprime
```

If you are running frontend and backend on the same server we recommend installing Docker Compose and running the 
`docker-compose.yml` file in this repository. Otherwise, run this command:
```
sudo docker run bioprime/backend:latest -d -p 8080:8080 --env-file ./home/bioprime/env.list 
```

It runs the docker container `bioprime/backend:latest` in detached mode `-d` on port 8080 `-p 8080:8080` and configures
the container environment `--env-file` from the file at `./home/bioprime/env.list`. If you want to use another port,
change the first number of the port flag (`-p 8081:8080`). 

To check if the application is running go to http://localhost:8081/healthcheck

How to compile and run the application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/backend-1.0-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`

