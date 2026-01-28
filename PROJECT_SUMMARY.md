# Project Summary

## Overview

This repository contains a complete full-stack booking system inspired by Airbnb, built with modern technologies and best practices.

## Technology Stack

### Backend
- **Framework**: Spring Boot 3.2.1
- **Language**: Java 17
- **Database**: H2 (development), PostgreSQL (production)
- **Authentication**: JWT with Spring Security
- **Build Tool**: Maven
- **ORM**: Spring Data JPA with Hibernate
- **Validation**: Jakarta Bean Validation

### Frontend
- **Framework**: React 18.2.0
- **Routing**: React Router v6
- **HTTP Client**: Axios
- **Styling**: Custom CSS with modern responsive design
- **Build Tool**: Create React App

### DevOps
- **Containerization**: Docker & Docker Compose
- **CI/CD**: GitHub Actions
- **Web Server**: Nginx (for frontend in production)

## Features Implemented

### User Management
✅ User registration with email validation  
✅ User login with JWT authentication  
✅ Password hashing with BCrypt  
✅ Role-based access (GUEST, HOST, ADMIN)  

### Property Management
✅ Browse all properties  
✅ View property details  
✅ Filter properties by city  
✅ Create new property listings (authenticated)  
✅ Update property information (authenticated)  
✅ Delete properties (authenticated)  

### Booking System
✅ Create bookings with date validation  
✅ View user's bookings  
✅ Calculate total price based on nights  
✅ Cancel bookings  
✅ Guest capacity validation  
✅ Booking status management (PENDING, CONFIRMED, CANCELLED, COMPLETED)  

### Reviews
✅ View property reviews  
✅ Create reviews with ratings (1-5)  
✅ Delete reviews  

### UI Components
✅ Responsive header with navigation  
✅ Home page with property grid  
✅ Property details page with booking form  
✅ User authentication pages (Login/Register)  
✅ Bookings management page  
✅ Modern, clean design with gradient hero sections  

## Security Features

### Implemented
- JWT-based authentication with externalized secret
- Password hashing using BCrypt
- Spring Security configuration with CORS
- Input validation on all DTOs
- JSON serialization protection (@JsonIgnore)
- Password field never exposed in API responses
- Separate production configuration
- Debug logging disabled in production
- Secure GitHub Actions workflows with minimal permissions

### Security Documentation
- Comprehensive SECURITY.md with best practices
- Production deployment checklist
- Known limitations documented
- Vulnerability disclosure policy

## API Endpoints

### Authentication
- `POST /api/auth/register` - Register new user
- `POST /api/auth/login` - Login user

### Properties
- `GET /api/properties` - Get all properties
- `GET /api/properties/{id}` - Get property by ID
- `GET /api/properties/city/{city}` - Filter by city
- `POST /api/properties` - Create property (auth required)
- `PUT /api/properties/{id}` - Update property (auth required)
- `DELETE /api/properties/{id}` - Delete property (auth required)

### Bookings
- `GET /api/bookings` - Get user bookings (auth required)
- `POST /api/bookings` - Create booking (auth required)
- `DELETE /api/bookings/{id}` - Cancel booking (auth required)

### Reviews
- `GET /api/reviews/property/{propertyId}` - Get property reviews
- `POST /api/reviews` - Create review (auth required)
- `DELETE /api/reviews/{id}` - Delete review (auth required)

## Project Structure

```
air_bnb/
├── backend/                          # Spring Boot application
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/airbnb/
│   │   │   │   ├── config/          # Configuration classes
│   │   │   │   ├── controller/      # REST controllers
│   │   │   │   ├── dto/             # Data Transfer Objects
│   │   │   │   ├── model/           # JPA entities
│   │   │   │   ├── repository/      # Spring Data repositories
│   │   │   │   ├── security/        # Security & JWT utilities
│   │   │   │   └── service/         # Business logic
│   │   │   └── resources/
│   │   │       ├── application.properties
│   │   │       └── application-prod.properties
│   │   └── test/                    # Test classes
│   ├── Dockerfile
│   └── pom.xml
│
├── frontend/                         # React application
│   ├── public/
│   │   └── index.html
│   ├── src/
│   │   ├── components/              # React components
│   │   ├── pages/                   # Page components
│   │   ├── services/                # API services
│   │   ├── styles/                  # CSS stylesheets
│   │   ├── App.js
│   │   └── index.js
│   ├── Dockerfile
│   ├── nginx.conf
│   └── package.json
│
├── .github/
│   └── workflows/                   # CI/CD pipelines
│       ├── backend-ci.yml
│       ├── frontend-ci.yml
│       └── full-stack-ci.yml
│
├── docker-compose.yml               # Full-stack orchestration
├── README.md                        # Project documentation
├── API_DOCUMENTATION.md             # API reference
├── DEPLOYMENT.md                    # Deployment guide
└── SECURITY.md                      # Security documentation
```

## Sample Data

The application initializes with sample data:

**Users:**
- Host: `host@example.com` / `password123`
- Guest: `guest@example.com` / `password123`

**Properties:**
- Cozy Downtown Apartment (New York, $150/night)
- Beach House Paradise (Miami, $300/night)
- Mountain Cabin Retreat (Aspen, $200/night)

## Quick Start

### Using Docker Compose (Recommended)

```bash
# Clone the repository
git clone https://github.com/aaryachinnawar/air_bnb.git
cd air_bnb

# Start all services
docker-compose up --build

# Access the application
# Frontend: http://localhost:3000
# Backend: http://localhost:8080
# H2 Console: http://localhost:8080/h2-console
```

### Local Development

**Backend:**
```bash
cd backend
mvn spring-boot:run
```

**Frontend:**
```bash
cd frontend
npm install
npm start
```

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
- Build Docker images on pushes
- Validate code quality
- Check for security issues

## Documentation

- **README.md**: Project overview and setup
- **API_DOCUMENTATION.md**: Complete API reference with examples
- **DEPLOYMENT.md**: Detailed deployment instructions for various platforms
- **SECURITY.md**: Security best practices and considerations

## Future Enhancements

Potential improvements for future versions:

- [ ] Email verification for new users
- [ ] Password reset functionality
- [ ] Image upload for properties
- [ ] Search with advanced filters (price range, dates, amenities)
- [ ] Real-time availability checking
- [ ] Payment integration (Stripe/PayPal)
- [ ] User profiles with avatars
- [ ] Messaging between guests and hosts
- [ ] Wishlist/favorites feature
- [ ] Map integration for property locations
- [ ] Multi-language support
- [ ] Mobile app (React Native)

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is open source and available under the MIT License.

## Acknowledgments

- Spring Boot team for the excellent framework
- React team for the frontend library
- All contributors who have helped with this project

## Support

For issues and questions:
- GitHub Issues: [Report an issue](https://github.com/aaryachinnawar/air_bnb/issues)
- Documentation: Check the docs folder

---

**Built with ❤️ by the AirBnB Clone Team**
