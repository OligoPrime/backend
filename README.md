# Installation for BioPrime backend

## Recommended specifications
 - OS: Ubuntu 20.4 LTS
 - RAM: 4Gb
 - CPU: 4 cores
 - Storage: 100Gb

## Installation

Make sure you have the latest version of Docker and Docker Compose installed.

Then move to [backend](https://github.com/OligoPrime/backend) root directory where you will find a [docker-compose.yml](https://github.com/OligoPrime/backend/blob/master/docker-compose.yml) file. 
You can change the username and password, but do not change the name of the services or external ports.

```shell script
$ cd backend
```

First pull the images from DockerHub.

```shell script
$ sudo docker-compose stop
$ sudo docker-compose rm -f
$ sudo docker-compose pull
```

The first thing we have to do is set up our database. Install `postgres 13` on your machine. You can find the instructions here:

[https://computingforgeeks.com/how-to-install-postgresql-13-on-ubuntu/](https://computingforgeeks.com/how-to-install-postgresql-13-on-ubuntu/)

Then start the `db` container and import data into the database:

```shell script
$ docker-compose up -d db
$
$ dropdb -U postgres -h localhost -p 5433 bioprime
$ createdb -h localhost -p 5433 -E UTF8 -O postgres -U postgres bioprime
$ pg_restore -Fc -h localhost -p 5433 -U postgres -d bioprime ./src/main/resources/bioprime_db.dmpc
```

Then start `frontend` and `backend` containers.

```shell script
$ docker-compose up -d backend
$ docker-compose up -d frontend
```

To check if backend is running go to http://localhost:8081/healthcheck
To check if frontend is running go to http://localhost:80/

Login using credentials `username: admin, password: admin`.


How to compile and run the application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/backend-1.0-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`

