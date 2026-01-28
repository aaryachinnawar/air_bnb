# API Documentation

## Overview

This document provides detailed information about the AirBnB Clone REST API endpoints.

## Base URL

```
http://localhost:8080/api
```

## Authentication

Most endpoints require JWT authentication. Include the token in the Authorization header:

```
Authorization: Bearer <your-jwt-token>
```

## Endpoints

### Authentication

#### Register

Create a new user account.

**Endpoint:** `POST /auth/register`

**Request Body:**
```json
{
  "email": "user@example.com",
  "password": "password123",
  "firstName": "John",
  "lastName": "Doe",
  "phoneNumber": "123-456-7890"
}
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "email": "user@example.com",
  "firstName": "John",
  "lastName": "Doe",
  "role": "GUEST"
}
```

#### Login

Authenticate an existing user.

**Endpoint:** `POST /auth/login`

**Request Body:**
```json
{
  "email": "user@example.com",
  "password": "password123"
}
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "email": "user@example.com",
  "firstName": "John",
  "lastName": "Doe",
  "role": "GUEST"
}
```

### Properties

#### Get All Properties

Retrieve a list of all properties.

**Endpoint:** `GET /properties`

**Authentication:** Not required

**Response:**
```json
[
  {
    "id": 1,
    "title": "Cozy Downtown Apartment",
    "description": "Beautiful apartment...",
    "address": "123 Main Street",
    "city": "New York",
    "country": "USA",
    "imageUrl": "https://...",
    "pricePerNight": 150.00,
    "maxGuests": 4,
    "bedrooms": 2,
    "bathrooms": 2,
    "propertyType": "APARTMENT"
  }
]
```

#### Get Property by ID

Retrieve details of a specific property.

**Endpoint:** `GET /properties/{id}`

**Authentication:** Not required

**Response:**
```json
{
  "id": 1,
  "title": "Cozy Downtown Apartment",
  "description": "Beautiful apartment...",
  "address": "123 Main Street",
  "city": "New York",
  "country": "USA",
  "imageUrl": "https://...",
  "pricePerNight": 150.00,
  "maxGuests": 4,
  "bedrooms": 2,
  "bathrooms": 2,
  "propertyType": "APARTMENT"
}
```

#### Get Properties by City

Filter properties by city.

**Endpoint:** `GET /properties/city/{city}`

**Authentication:** Not required

#### Create Property

Add a new property listing.

**Endpoint:** `POST /properties`

**Authentication:** Required

**Request Body:**
```json
{
  "title": "New Property",
  "description": "Description here",
  "address": "123 Street",
  "city": "City Name",
  "country": "Country Name",
  "imageUrl": "https://...",
  "pricePerNight": 100.00,
  "maxGuests": 2,
  "bedrooms": 1,
  "bathrooms": 1,
  "propertyType": "APARTMENT"
}
```

#### Update Property

Update an existing property.

**Endpoint:** `PUT /properties/{id}`

**Authentication:** Required

**Request Body:** Same as Create Property

#### Delete Property

Remove a property listing.

**Endpoint:** `DELETE /properties/{id}`

**Authentication:** Required

### Bookings

#### Get User Bookings

Retrieve all bookings for the authenticated user.

**Endpoint:** `GET /bookings`

**Authentication:** Required

**Response:**
```json
[
  {
    "id": 1,
    "property": {
      "id": 1,
      "title": "Cozy Downtown Apartment",
      "city": "New York",
      "country": "USA"
    },
    "checkInDate": "2024-03-01",
    "checkOutDate": "2024-03-05",
    "numberOfGuests": 2,
    "totalPrice": 600.00,
    "status": "CONFIRMED"
  }
]
```

#### Create Booking

Make a new booking.

**Endpoint:** `POST /bookings`

**Authentication:** Required

**Request Body:**
```json
{
  "propertyId": 1,
  "checkInDate": "2024-03-01",
  "checkOutDate": "2024-03-05",
  "numberOfGuests": 2,
  "totalPrice": 600.00
}
```

#### Cancel Booking

Cancel an existing booking.

**Endpoint:** `DELETE /bookings/{id}`

**Authentication:** Required

### Reviews

#### Get Property Reviews

Retrieve all reviews for a property.

**Endpoint:** `GET /reviews/property/{propertyId}`

**Authentication:** Not required

**Response:**
```json
[
  {
    "id": 1,
    "user": {
      "id": 1,
      "firstName": "Jane",
      "lastName": "Doe"
    },
    "rating": 5,
    "comment": "Great place to stay!",
    "createdAt": "2024-02-15T10:30:00"
  }
]
```

#### Create Review

Add a review for a property.

**Endpoint:** `POST /reviews`

**Authentication:** Required

**Request Body:**
```json
{
  "propertyId": 1,
  "rating": 5,
  "comment": "Excellent stay!"
}
```

## Error Responses

All endpoints may return the following error responses:

**400 Bad Request**
```json
{
  "message": "Invalid request data"
}
```

**401 Unauthorized**
```json
{
  "message": "Authentication required"
}
```

**404 Not Found**
```json
{
  "message": "Resource not found"
}
```

**500 Internal Server Error**
```json
{
  "message": "An unexpected error occurred"
}
```

## Testing with cURL

### Register a user:
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "email": "test@example.com",
    "password": "password123",
    "firstName": "Test",
    "lastName": "User",
    "phoneNumber": "123-456-7890"
  }'
```

### Login:
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "test@example.com",
    "password": "password123"
  }'
```

### Get all properties:
```bash
curl http://localhost:8080/api/properties
```

### Create a booking (authenticated):
```bash
curl -X POST http://localhost:8080/api/bookings \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_TOKEN_HERE" \
  -d '{
    "propertyId": 1,
    "checkInDate": "2024-03-01",
    "checkOutDate": "2024-03-05",
    "numberOfGuests": 2,
    "totalPrice": 600.00
  }'
```
