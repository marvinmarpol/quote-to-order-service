# Quote To Order Service
Quote-to-Order API that allows internal users to:
1. Create and update product Quotes
2. Approve quotes after validation
3. Convert an approved quote into an Order

## Run locally:
    mvn spring-boot:run
or provide the spring profile
    
    SPRING_PROFILES_ACTIVE=dev mvn spring-boot:run


## Run docker locally:
### 1. Build the docker image
    docker build -t quote-to-order-service:latest .
    
### 2. Run the image in a container
    docker run --rm --env-file .env -d -p 8080:8080 --name quote-to-order-service quote-to-order-service:latest
### 3. Run by providing env explicitly
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

## Run using docker compose
    docker compose up --build

## Generate Maven Wrapper:
    mvn -N wrapper:wrapper

## Clean and recompile
    mvn clean install

## Clean and test
    mvn clean test