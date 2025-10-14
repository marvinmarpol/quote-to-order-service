# 🧾 Quote-to-Order Service

The **Quote-to-Order Service** is a Spring Boot–based API that enables internal users to manage product quotes and convert them into orders.

### Core Features
1. **Create & Update Quotes** — Draft and modify product quotes.  
2. **Quote Approval Workflow** — Validate and approve quotes before conversion.  
3. **Order Conversion** — Seamlessly convert approved quotes into confirmed orders.

---

## ⚙️ Run Locally (No Docker)

Run the service directly using Maven:

```bash
mvn spring-boot:run
```

or specify a Spring profile:

```bash
SPRING_PROFILES_ACTIVE=dev mvn spring-boot:run
```

---

## 🐳 Run with Docker

### 1. Build the Docker image
```bash
docker build -t quote-to-order-service:latest .
```

### 2. Run the container using `.env` file
```bash
docker run --rm --env-file .env -d -p 8080:8080 --name quote-to-order-service quote-to-order-service:latest
```

### 3. Run by specifying environment variables manually
```bash
docker run --rm -d \
    -p 8080:8080 \
    -e SPRING_PROFILES_ACTIVE=dev \
    -e SPRING_DATASOURCE_URL="jdbc:postgresql://<DB_HOST>:5432/<DB_NAME>" \
    -e SPRING_DATASOURCE_USERNAME="<DB_USER>" \
    -e SPRING_DATASOURCE_PASSWORD="<DB_PASS>" \
    -e SPRING_REDIS_HOST="<REDIS_HOST>" \
    -e SPRING_REDIS_PORT=6379 \
    -e SPRING_REDIS_PASSWORD="<REDIS_PASS>" \
    --name quote-to-order-service \
    quote-to-order-service:latest
```

---

## 🧩 Run with Docker Compose
```bash
docker compose up --build
```

## Stops containers and removes containers
```bash
docker compose down
```

This will automatically start all required services (database, Redis, and the application).

---

## 🧰 Maven Commands

### Generate Maven Wrapper
```bash
mvn -N wrapper:wrapper
```

### Clean and Rebuild
```bash
mvn clean install
```

### Clean and Run Tests
```bash
mvn clean test
```

---

## 🐳 Docker House Keeping
### clean up dangling images and stopped containers safely:
```bash
docker system prune -f
```
### Remove unused volumes and networks
```bash
docker system prune -a --volumes -f
```

## 🌐 Environment Profiles
| Profile | Description |
|----------|--------------|
| `docker` | Local dockerized development environment |
| `dev` | Local development environment (e.g., Docker Compose setup). |
| `prod` | Production mode (uses cloud-based services like AWS RDS or Google Cloud SQL & Redis). |