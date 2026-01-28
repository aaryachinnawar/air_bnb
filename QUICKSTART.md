# Quick Reference - Codespaces Commands

## 🚀 Starting the Application

### Option 1: Using the Helper Script (Easiest)
```bash
./start.sh
```
Then select option 3 to start both services.

### Option 2: Manual Start

**Terminal 1 - Backend:**
```bash
cd backend
mvn spring-boot:run
```

**Terminal 2 - Frontend:**
```bash
cd frontend
npm install  # first time only
npm start
```

## 🌐 Accessing the Application

- **Frontend**: Click the port 3000 notification or use the PORTS tab
- **Backend API**: Access via port 8080
- **H2 Console**: `{backend-url}/h2-console`

## 👤 Test Credentials

| Account | Email | Password |
|---------|-------|----------|
| Host | `host@example.com` | `password123` |
| Guest | `guest@example.com` | `password123` |

## 🧪 Running Tests

```bash
# Backend tests
cd backend && mvn test

# Frontend tests
cd frontend && npm test
```

## 🔧 Common Issues

### Port already in use
```bash
lsof -ti:8080 | xargs kill -9
```

### Frontend won't start
```bash
cd frontend
rm -rf node_modules
npm install
```

### Backend build fails
```bash
cd backend
mvn clean install -U
```

## 📝 API Testing

### Get all properties
```bash
curl http://localhost:8080/api/properties
```

### Register a user
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "email": "test@example.com",
    "password": "password123",
    "firstName": "Test",
    "lastName": "User"
  }'
```

### Login
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "guest@example.com",
    "password": "password123"
  }'
```

## 🐳 Using Docker (Alternative)

```bash
docker-compose up --build
```

## 📚 Documentation

- [Full Codespaces Guide](CODESPACES.md)
- [API Documentation](API_DOCUMENTATION.md)
- [Deployment Guide](DEPLOYMENT.md)
- [Security Guide](SECURITY.md)

## 🆘 Need Help?

Run the helper script for more options:
```bash
./start.sh
```

Or check the [Troubleshooting section](CODESPACES.md#-troubleshooting) in CODESPACES.md
