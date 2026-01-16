# Deployment Guide

This guide explains how to deploy your "Todo" Full Stack application.

## Strategy: Single Artifact Deployment

The easiest way to deploy this application is to bundle the **Frontend (HTML/JS)** inside the **Backend (Java JAR)**. This way, you only need to run one server, and you don't need to worry about CORS (Cross-Origin Resource Sharing).

## Option 1: Quick Local Build (PowerShell)

I have created a helper script `prepare_production.ps1` that will:
1.  Copy your frontend files into the backend resources.
2.  Update the API URL in the frontend script to work in production.
3.  Build the final Java JAR file.

### How to Run:
1.  Open a terminal in `d:\babu_projects\SpringBoot-main\SpringBoot-main`.
2.  Run:
    ```powershell
    .\prepare_production.ps1
    ```
3.  Once finished, your runnable file will be at: `TodoBackend\target\Todo-0.0.1-SNAPSHOT.jar`.
4.  You can run it (requires DB connection):
    ```powershell
    java -jar TodoBackend\target\Todo-0.0.1-SNAPSHOT.jar
    ```

## Option 2: Cloud Deployment (Docker)

If you want to deploy to a cloud provider like **Render**, **Railway**, or **AWS**, using Docker is best.

1.  We have created a `Dockerfile` in the `TodoBackend` folder.
2.  **Important**: Before deploying, you still need to copy the frontend files into the backend (using the script above), OR you must configure your deployment pipeline to do so.

### Database
In production, you will need a PostgreSQL database.
-   **Render/Railway**: They verify provide PostgreSQL.
-   **Environment Variables**: You should pass database credentials via environment variables instead of hardcoding them in `application.properties`.

**Recommended Environment Variables:**
-   `SPRING_DATASOURCE_URL`
-   `SPRING_DATASOURCE_USERNAME`
-   `SPRING_DATASOURCE_PASSWORD`
