#!/bin/bash

# AirBnB Clone - Startup Script for Codespaces
# This script helps you quickly start both frontend and backend

set -e

echo "================================================"
echo "  AirBnB Clone - Startup Script"
echo "================================================"
echo ""

# Function to check if a command exists
command_exists() {
    command -v "$1" >/dev/null 2>&1
}

# Check prerequisites
echo "🔍 Checking prerequisites..."

if ! command_exists java; then
    echo "❌ Java is not installed"
    exit 1
fi

if ! command_exists node; then
    echo "❌ Node.js is not installed"
    exit 1
fi

if ! command_exists mvn; then
    echo "❌ Maven is not installed"
    exit 1
fi

echo "✅ All prerequisites are installed"
echo ""

# Menu
echo "What would you like to do?"
echo ""
echo "1) Start Backend only (Spring Boot)"
echo "2) Start Frontend only (React)"
echo "3) Start Both (Backend + Frontend)"
echo "4) Run Backend Tests"
echo "5) Run Frontend Tests"
echo "6) Build Everything"
echo "7) Clean and Reinstall Dependencies"
echo ""
read -p "Enter your choice (1-7): " choice

case $choice in
    1)
        echo ""
        echo "🚀 Starting Backend (Spring Boot)..."
        echo "================================================"
        cd backend
        mvn spring-boot:run
        ;;
    2)
        echo ""
        echo "🚀 Starting Frontend (React)..."
        echo "================================================"
        cd frontend
        if [ ! -d "node_modules" ]; then
            echo "📦 Installing dependencies first..."
            npm install
        fi
        npm start
        ;;
    3)
        echo ""
        echo "🚀 Starting Both Backend and Frontend..."
        echo "================================================"
        echo ""
        echo "📝 Note: This will open two terminal windows"
        echo "   - Backend in this terminal"
        echo "   - Frontend in a new terminal (you need to run: cd frontend && npm start)"
        echo ""
        echo "Starting Backend in 3 seconds..."
        sleep 3
        cd backend
        mvn spring-boot:run &
        BACKEND_PID=$!
        
        echo ""
        echo "⏳ Waiting for backend to start..."
        sleep 15
        
        echo ""
        echo "Now run this in a NEW TERMINAL:"
        echo "  cd frontend && npm start"
        echo ""
        echo "Press Ctrl+C to stop the backend"
        wait $BACKEND_PID
        ;;
    4)
        echo ""
        echo "🧪 Running Backend Tests..."
        echo "================================================"
        cd backend
        mvn test
        ;;
    5)
        echo ""
        echo "🧪 Running Frontend Tests..."
        echo "================================================"
        cd frontend
        if [ ! -d "node_modules" ]; then
            echo "📦 Installing dependencies first..."
            npm install
        fi
        npm test -- --passWithNoTests
        ;;
    6)
        echo ""
        echo "🔨 Building Everything..."
        echo "================================================"
        
        echo "📦 Building Backend..."
        cd backend
        mvn clean package -DskipTests
        cd ..
        
        echo ""
        echo "📦 Building Frontend..."
        cd frontend
        if [ ! -d "node_modules" ]; then
            npm install
        fi
        npm run build
        cd ..
        
        echo ""
        echo "✅ Build complete!"
        echo "   Backend JAR: backend/target/airbnb-backend-1.0.0.jar"
        echo "   Frontend build: frontend/build/"
        ;;
    7)
        echo ""
        echo "🧹 Cleaning and Reinstalling Dependencies..."
        echo "================================================"
        
        echo "🧹 Cleaning Backend..."
        cd backend
        mvn clean
        rm -rf target
        cd ..
        
        echo ""
        echo "🧹 Cleaning Frontend..."
        cd frontend
        rm -rf node_modules package-lock.json build
        npm install
        cd ..
        
        echo ""
        echo "✅ Clean complete! Dependencies reinstalled."
        ;;
    *)
        echo "❌ Invalid choice. Please run the script again and select 1-7."
        exit 1
        ;;
esac

echo ""
echo "================================================"
echo "  Done!"
echo "================================================"
