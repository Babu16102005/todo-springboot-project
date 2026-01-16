# Docker Deployment Guide

This project is now fully "Dockerized". This means you can run the entire application (Frontend, Backend, and Database) with a single command, without needing to install Java or PostgreSQL on your machine.

## Prerequisites
-   **Docker Desktop**: You must have Docker installed and running.

## How to Run
1.  Open a terminal in the project folder (`d:\babu_projects\SpringBoot-main\SpringBoot-main`).
2.  Run the following command:
    ```powershell
    docker-compose up --build
    ```
3.  Wait for the logs.
    -   Docker will download the necessary images (Java, Postgres).
    -   It will compile your code automatically.
    -   It will start the database and the app.
4.  Once you see "Started TodoApplication", open your browser:
    -   **URL**: [http://localhost:8080/login.html](http://localhost:8080/login.html)

## How to Stop
Press `Ctrl + C` in the terminal, or run:
```powershell
docker-compose down
```

## Troubleshooting
-   **Port Conflicts**: If port `8080` or `5432` is already in use (e.g., by your local Java/Postgres), the container will fail to start. Stop your local servers first.
-   **Database Data**: The database data is stored in a Docker Volume (`postgres_data`). It persists even if you restart the containers.
