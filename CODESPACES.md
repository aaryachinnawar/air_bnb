# Running AirBnB Clone in GitHub Codespaces

This guide will help you run the AirBnB Clone booking system in GitHub Codespaces, a cloud-based development environment.

> 📊 **Architecture Overview**: See [.github/ARCHITECTURE.md](.github/ARCHITECTURE.md) for a visual diagram of the Codespaces setup.

## 🚀 Quick Start

### Step 1: Open in Codespaces

1. **Navigate to the repository**: Go to [https://github.com/aaryachinnawar/air_bnb](https://github.com/aaryachinnawar/air_bnb)

2. **Create a Codespace**:
   - Click the green **Code** button
   - Select the **Codespaces** tab
   - Click **Create codespace on main** (or your preferred branch)

3. **Wait for setup**: Codespaces will automatically:
   - Create a cloud-based development environment
   - Install Java 17 and Node.js 18
   - Install frontend dependencies (this may take 2-3 minutes)

### Step 2: Start the Backend (Spring Boot)

Open a new terminal in Codespaces and run:

```bash
cd backend
mvn clean install
mvn spring-boot:run
```

**Expected output:**
```
Started AirbnbApplication in X.XXX seconds
Sample data initialized successfully!
```

The backend will be available at: `http://localhost:8080`

### Step 3: Start the Frontend (React)

Open a **new terminal** (keep the backend running) and run:

```bash
cd frontend
npm start
```

**Expected output:**
```
Compiled successfully!
webpack compiled with 0 warnings
```

The frontend will be available at: `http://localhost:3000`

### Step 4: Access the Application

Codespaces will automatically forward ports 3000 and 8080. You have two ways to access:

**Option 1: Click the notification**
- When ports are forwarded, you'll see a notification
- Click "Open in Browser" for port 3000

**Option 2: Use the Ports panel**
- Go to the **PORTS** tab in the bottom panel
- Find port 3000 (Frontend) and click the 🌐 globe icon
- Or right-click and select "Open in Browser"

## 🎯 Default Credentials

The application comes with sample data. You can log in with:

**Host Account:**
- Email: `host@example.com`
- Password: `password123`

**Guest Account:**
- Email: `guest@example.com`
- Password: `password123`

## 📍 Accessing Different Services

Once your Codespace is running, you can access:

| Service | Port | URL in Codespace |
|---------|------|------------------|
| Frontend (React) | 3000 | Auto-forwarded URL |
| Backend API | 8080 | Auto-forwarded URL |
| H2 Database Console | 8080 | `{backend-url}/h2-console` |

### H2 Database Console

To access the H2 database console:

1. Open the backend URL: `{your-codespace-url}-8080.app.github.dev/h2-console`
2. Use these connection details:
   - **JDBC URL**: `jdbc:h2:mem:airbnbdb`
   - **Username**: `sa`
   - **Password**: (leave empty)

## 🔧 Development Workflow

### Running Backend Tests

```bash
cd backend
mvn test
```

### Running Frontend Tests

```bash
cd frontend
npm test
```

### Building for Production

**Backend:**
```bash
cd backend
mvn clean package
```

**Frontend:**
```bash
cd frontend
npm run build
```

### Using Docker Compose (Alternative)

If you prefer using Docker:

```bash
docker-compose up --build
```

This will start both frontend and backend in containers.

## 🛠️ IDE Extensions

Your Codespace comes pre-configured with helpful extensions:

### For Java/Spring Boot:
- **Java Extension Pack** - Java language support
- **Spring Boot Tools** - Spring Boot development tools
- **Spring Boot Dashboard** - Manage Spring Boot apps

### For React:
- **ESLint** - JavaScript linting
- **Prettier** - Code formatting
- **ES7+ React Snippets** - React code snippets

### General:
- **Docker** - Docker container management

## 📝 Making API Requests

### Using the Terminal (curl)

Test the backend API directly:

```bash
# Get all properties
curl http://localhost:8080/api/properties

# Register a new user
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "email": "newuser@example.com",
    "password": "password123",
    "firstName": "John",
    "lastName": "Doe",
    "phoneNumber": "123-456-7890"
  }'

# Login
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "guest@example.com",
    "password": "password123"
  }'
```

### Using VS Code REST Client

Install the "REST Client" extension and create a `.http` file:

```http
### Get all properties
GET http://localhost:8080/api/properties

### Register user
POST http://localhost:8080/api/auth/register
Content-Type: application/json

{
  "email": "test@example.com",
  "password": "password123",
  "firstName": "Test",
  "lastName": "User"
}
```

## 🐛 Troubleshooting

### Frontend won't start

**Issue**: `npm start` fails with module errors

**Solution**:
```bash
cd frontend
rm -rf node_modules package-lock.json
npm install
npm start
```

### Backend won't start

**Issue**: Port 8080 already in use

**Solution**:
```bash
# Find and kill the process
lsof -ti:8080 | xargs kill -9

# Or change the port in application.properties
cd backend/src/main/resources
# Edit application.properties and change server.port=8081
```

### Cannot access forwarded ports

**Issue**: URLs not opening

**Solution**:
1. Check the PORTS panel at the bottom
2. Ensure ports 3000 and 8080 are forwarded
3. Try making the port visibility "Public" (right-click on port → Port Visibility → Public)
4. Refresh your browser

### Maven build fails

**Issue**: Maven download errors

**Solution**:
```bash
# Clean Maven cache and retry
rm -rf ~/.m2/repository
cd backend
mvn clean install -U
```

### Out of memory errors

**Issue**: Build fails with heap space errors

**Solution**:
```bash
# Increase Java heap size
export MAVEN_OPTS="-Xmx1024m"
cd backend
mvn clean install
```

## 💡 Tips and Tricks

### 1. Multiple Terminals

Use split terminals to run multiple services:
- Terminal 1: Backend (`mvn spring-boot:run`)
- Terminal 2: Frontend (`npm start`)
- Terminal 3: Available for commands

### 2. Hot Reload

Both frontend and backend support hot reload:
- **Frontend**: Edit React files, browser auto-refreshes
- **Backend**: Use Spring Boot DevTools (already included)

### 3. Debugging

#### Debug Backend:
1. Set breakpoints in Java code
2. Go to Run & Debug (Ctrl+Shift+D)
3. Select "Debug (Attach)" configuration
4. Start backend with: `mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005"`

#### Debug Frontend:
1. Add debugger statement in code
2. Open browser DevTools (F12)
3. Use the Sources tab for debugging

### 4. Environment Variables

Create a `.env` file in the frontend directory:

```bash
REACT_APP_API_URL=http://localhost:8080/api
```

### 5. Database Persistence

By default, H2 runs in memory. To persist data:

Edit `backend/src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:h2:file:./data/airbnbdb
```

## 🔄 Syncing with Main Repository

Keep your Codespace up to date:

```bash
git fetch origin
git pull origin main
```

## 📚 Additional Resources

- **API Documentation**: See `API_DOCUMENTATION.md`
- **Deployment Guide**: See `DEPLOYMENT.md`
- **Security Guide**: See `SECURITY.md`
- **Project Summary**: See `PROJECT_SUMMARY.md`

## 🆘 Getting Help

If you encounter issues:

1. Check the [GitHub Issues](https://github.com/aaryachinnawar/air_bnb/issues)
2. Review the troubleshooting section above
3. Check application logs in the terminal
4. Open a new issue with:
   - Error message
   - Steps to reproduce
   - Codespace environment details

## 📦 Codespace Specifications

Recommended Codespace machine type:
- **2-core**: Sufficient for development and testing
- **4-core**: Better performance for building and running both services
- **8-core**: Optimal for heavy development

Your Codespace includes:
- ✅ Java 17 (OpenJDK)
- ✅ Node.js 18
- ✅ Maven 3.9+
- ✅ npm/npx
- ✅ Docker
- ✅ Git
- ✅ VS Code extensions for Java and React

## 🎉 You're All Set!

Your development environment is ready. Happy coding!

**Next Steps:**
1. Explore the codebase
2. Make changes and see them live
3. Run tests to ensure everything works
4. Create your first booking!

---

**Note**: Codespaces may timeout after 30 minutes of inactivity. Your work is automatically saved, but you'll need to restart services when you return.
