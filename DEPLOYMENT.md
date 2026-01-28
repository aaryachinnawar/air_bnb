# Deployment Guide

This guide provides step-by-step instructions for deploying the AirBnB Clone application.

## Table of Contents

1. [Local Development](#local-development)
2. [Docker Deployment](#docker-deployment)
3. [Cloud Deployment](#cloud-deployment)
4. [Environment Variables](#environment-variables)
5. [Troubleshooting](#troubleshooting)

## Local Development

### Prerequisites

- Java 17 or higher
- Node.js 18 or higher
- Maven 3.6+
- Git

### Backend Setup

1. Navigate to the backend directory:
```bash
cd backend
```

2. Install dependencies and build:
```bash
mvn clean install
```

3. Run the application:
```bash
mvn spring-boot:run
```

The backend will start on `http://localhost:8080`

### Frontend Setup

1. Navigate to the frontend directory:
```bash
cd frontend
```

2. Install dependencies:
```bash
npm install
```

3. Start the development server:
```bash
npm start
```

The frontend will start on `http://localhost:3000`

## Docker Deployment

### Using Docker Compose (Recommended)

1. Ensure Docker and Docker Compose are installed

2. Build and start all services:
```bash
docker-compose up --build
```

3. Access the application:
   - Frontend: http://localhost:3000
   - Backend: http://localhost:8080
   - H2 Console: http://localhost:8080/h2-console

4. Stop the services:
```bash
docker-compose down
```

### Individual Docker Containers

#### Backend

```bash
cd backend
docker build -t airbnb-backend .
docker run -p 8080:8080 airbnb-backend
```

#### Frontend

```bash
cd frontend
docker build -t airbnb-frontend .
docker run -p 3000:80 airbnb-frontend
```

## Cloud Deployment

### AWS Deployment

#### Using AWS Elastic Beanstalk

1. Install AWS CLI and EB CLI

2. Initialize EB application:
```bash
eb init -p docker airbnb-app
```

3. Create environment:
```bash
eb create airbnb-env
```

4. Deploy:
```bash
eb deploy
```

#### Using AWS ECS (Elastic Container Service)

1. Push images to Amazon ECR:
```bash
# Login to ECR
aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin <account-id>.dkr.ecr.us-east-1.amazonaws.com

# Tag images
docker tag airbnb-backend:latest <account-id>.dkr.ecr.us-east-1.amazonaws.com/airbnb-backend:latest
docker tag airbnb-frontend:latest <account-id>.dkr.ecr.us-east-1.amazonaws.com/airbnb-frontend:latest

# Push images
docker push <account-id>.dkr.ecr.us-east-1.amazonaws.com/airbnb-backend:latest
docker push <account-id>.dkr.ecr.us-east-1.amazonaws.com/airbnb-frontend:latest
```

2. Create ECS task definitions and services through AWS Console or CLI

### Heroku Deployment

#### Backend

1. Create a Heroku app:
```bash
heroku create airbnb-backend
```

2. Add PostgreSQL addon:
```bash
heroku addons:create heroku-postgresql:hobby-dev
```

3. Deploy:
```bash
git subtree push --prefix backend heroku main
```

#### Frontend

1. Create a Heroku app:
```bash
heroku create airbnb-frontend
```

2. Set buildpack:
```bash
heroku buildpacks:set heroku/nodejs
```

3. Deploy:
```bash
git subtree push --prefix frontend heroku main
```

### Google Cloud Platform (GCP)

#### Using Cloud Run

1. Build and push images:
```bash
# Backend
gcloud builds submit --tag gcr.io/PROJECT_ID/airbnb-backend backend/

# Frontend
gcloud builds submit --tag gcr.io/PROJECT_ID/airbnb-frontend frontend/
```

2. Deploy to Cloud Run:
```bash
# Backend
gcloud run deploy airbnb-backend \
  --image gcr.io/PROJECT_ID/airbnb-backend \
  --platform managed \
  --region us-central1 \
  --allow-unauthenticated

# Frontend
gcloud run deploy airbnb-frontend \
  --image gcr.io/PROJECT_ID/airbnb-frontend \
  --platform managed \
  --region us-central1 \
  --allow-unauthenticated
```

## Environment Variables

### Backend

Create `application-prod.properties` for production:

```properties
# Server
server.port=${PORT:8080}

# Database (PostgreSQL for production)
spring.datasource.url=${DATABASE_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.jpa.hibernate.ddl-auto=update

# Security
jwt.secret=${JWT_SECRET}
jwt.expiration=${JWT_EXPIRATION:86400000}
```

### Frontend

Create `.env.production`:

```
REACT_APP_API_URL=https://your-backend-url.com/api
```

## CI/CD with GitHub Actions

The repository includes three GitHub Actions workflows:

1. **Backend CI** (`.github/workflows/backend-ci.yml`)
   - Runs on backend changes
   - Builds, tests, and creates Docker image

2. **Frontend CI** (`.github/workflows/frontend-ci.yml`)
   - Runs on frontend changes
   - Builds, tests, and creates Docker image

3. **Full Stack CI** (`.github/workflows/full-stack-ci.yml`)
   - Runs on main branch pushes
   - Tests full integration

### Setting up Secrets

Add these secrets to your GitHub repository:

- `DOCKER_USERNAME`: Docker Hub username
- `DOCKER_PASSWORD`: Docker Hub password
- `AWS_ACCESS_KEY_ID`: AWS access key
- `AWS_SECRET_ACCESS_KEY`: AWS secret key

## Database Migration

### From H2 to PostgreSQL

1. Update `pom.xml` to include PostgreSQL driver:
```xml
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <scope>runtime</scope>
</dependency>
```

2. Update `application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/airbnbdb
spring.datasource.username=postgres
spring.datasource.password=yourpassword
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```

## Monitoring and Logging

### Adding Application Monitoring

1. Add Spring Boot Actuator:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

2. Configure endpoints in `application.properties`:
```properties
management.endpoints.web.exposure.include=health,info,metrics
management.endpoint.health.show-details=always
```

## Troubleshooting

### Backend Issues

**Issue:** Port 8080 already in use
```bash
# Find and kill the process
lsof -ti:8080 | xargs kill -9
```

**Issue:** Database connection errors
- Check H2 console at http://localhost:8080/h2-console
- Verify JDBC URL: `jdbc:h2:mem:airbnbdb`
- Username: `sa`, Password: (empty)

### Frontend Issues

**Issue:** API calls failing
- Check CORS configuration in backend
- Verify backend is running on correct port
- Check browser console for errors

**Issue:** Build fails
```bash
# Clear cache and reinstall
rm -rf node_modules package-lock.json
npm install
```

### Docker Issues

**Issue:** Container won't start
```bash
# Check logs
docker-compose logs backend
docker-compose logs frontend

# Rebuild without cache
docker-compose build --no-cache
```

## Performance Optimization

### Backend

1. Enable caching:
```java
@EnableCaching
public class AirbnbApplication {
    // ...
}
```

2. Add connection pooling for production database

3. Configure appropriate JVM parameters:
```bash
java -Xms512m -Xmx1024m -jar app.jar
```

### Frontend

1. Build optimized production bundle:
```bash
npm run build
```

2. Enable compression in nginx:
```nginx
gzip on;
gzip_types text/plain text/css application/json application/javascript;
```

3. Implement lazy loading for routes

## Security Checklist

- [ ] Change default JWT secret
- [ ] Use HTTPS in production
- [ ] Enable CSRF protection for non-REST endpoints
- [ ] Implement rate limiting
- [ ] Use environment variables for sensitive data
- [ ] Regular security updates for dependencies
- [ ] Enable SQL injection protection
- [ ] Implement proper input validation
- [ ] Use secure password hashing (BCrypt)
- [ ] Configure appropriate CORS policies

## Support

For issues and questions:
- GitHub Issues: [Project Issues](https://github.com/aaryachinnawar/air_bnb/issues)
- Email: support@airbnbclone.com
