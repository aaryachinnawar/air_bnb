# AirBnB Clone - Booking System

A full-stack booking system similar to Airbnb, built with React.js for the frontend and Spring Boot for the backend.

## Features

- **Property Listings**: Browse available properties with detailed information
- **User Authentication**: Secure user registration and login
- **Booking System**: Make and manage reservations
- **Reviews & Ratings**: Leave and view property reviews
- **Host Management**: Property owners can list and manage their properties
- **Search & Filter**: Find properties by location, dates, and amenities

## Tech Stack

### Frontend
- React.js
- React Router for navigation
- Axios for API calls
- Modern CSS with responsive design

### Backend
- Spring Boot 3.x
- Spring Data JPA
- Spring Security
- H2 Database (development)
- Maven for dependency management

### DevOps
- Docker & Docker Compose
- GitHub Actions for CI/CD

## Project Structure

```
air_bnb/
├── backend/              # Spring Boot application
│   ├── src/
│   ├── pom.xml
│   └── Dockerfile
├── frontend/             # React application
│   ├── src/
│   ├── package.json
│   └── Dockerfile
├── docker-compose.yml
└── .github/
    └── workflows/        # CI/CD pipelines
```

## Getting Started

### Prerequisites
- Java 17 or higher
- Node.js 16 or higher
- Docker and Docker Compose (optional)
- Maven 3.6+

### Running with Docker (Recommended)

1. Clone the repository:
```bash
git clone https://github.com/aaryachinnawar/air_bnb.git
cd air_bnb
```

2. Start all services with Docker Compose:
```bash
docker-compose up --build
```

3. Access the application:
   - Frontend: http://localhost:3000
   - Backend API: http://localhost:8080
   - H2 Console: http://localhost:8080/h2-console

### Running Locally

#### Backend
```bash
cd backend
mvn clean install
mvn spring-boot:run
```

#### Frontend
```bash
cd frontend
npm install
npm start
```

## API Endpoints

### Properties
- `GET /api/properties` - Get all properties
- `GET /api/properties/{id}` - Get property by ID
- `POST /api/properties` - Create new property (authenticated)
- `PUT /api/properties/{id}` - Update property (authenticated)
- `DELETE /api/properties/{id}` - Delete property (authenticated)

### Bookings
- `GET /api/bookings` - Get all user bookings (authenticated)
- `POST /api/bookings` - Create new booking (authenticated)
- `PUT /api/bookings/{id}` - Update booking (authenticated)
- `DELETE /api/bookings/{id}` - Cancel booking (authenticated)

### Users
- `POST /api/auth/register` - Register new user
- `POST /api/auth/login` - Login user
- `GET /api/users/profile` - Get user profile (authenticated)

### Reviews
- `GET /api/properties/{id}/reviews` - Get property reviews
- `POST /api/reviews` - Create review (authenticated)

## Development

### Backend Development
The backend follows standard Spring Boot conventions:
- Controllers in `controller` package
- Services in `service` package
- Repositories in `repository` package
- Models in `model` package

### Frontend Development
React components are organized by feature:
- Pages in `pages/` directory
- Reusable components in `components/` directory
- API calls in `services/` directory
- Routing in `App.js`

## Testing

### Backend Tests
```bash
cd backend
mvn test
```

### Frontend Tests
```bash
cd frontend
npm test
```

## CI/CD

GitHub Actions workflows automatically:
- Run tests on pull requests
- Build Docker images
- Deploy to staging/production (when configured)

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is open source and available under the MIT License.

## Contact

Project Link: [https://github.com/aaryachinnawar/air_bnb](https://github.com/aaryachinnawar/air_bnb)