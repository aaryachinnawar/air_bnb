# Codespaces Architecture

```
┌─────────────────────────────────────────────────────────────────┐
│                    GitHub Codespaces                            │
│                   (Cloud Development Environment)               │
└─────────────────────────────────────────────────────────────────┘
                              │
                              │
        ┌─────────────────────┴─────────────────────┐
        │                                           │
        ▼                                           ▼
┌──────────────────┐                     ┌──────────────────┐
│   VS Code Web    │                     │  Container       │
│   (Browser UI)   │◄────────────────────┤  Environment     │
└──────────────────┘                     └──────────────────┘
                                                   │
                    ┌──────────────────────────────┴───────────────────┐
                    │                                                  │
                    ▼                                                  ▼
          ┌──────────────────┐                            ┌──────────────────┐
          │   Backend        │                            │   Frontend       │
          │   (Spring Boot)  │                            │   (React)        │
          │   Port: 8080     │                            │   Port: 3000     │
          └──────────────────┘                            └──────────────────┘
                    │                                                  │
                    │                                                  │
                    ▼                                                  ▼
          ┌──────────────────┐                            ┌──────────────────┐
          │   H2 Database    │                            │   Node Modules   │
          │   (In-Memory)    │                            │   Dependencies   │
          └──────────────────┘                            └──────────────────┘


Port Forwarding:
═══════════════
┌─────────────────────────────────────────────────────────┐
│ Codespace exposes ports automatically:                  │
│                                                         │
│ Port 3000 → https://[codespace-name]-3000.app.github.dev│
│ Port 8080 → https://[codespace-name]-8080.app.github.dev│
└─────────────────────────────────────────────────────────┘


Development Workflow:
════════════════════

Developer
    │
    │ (1) Opens Codespace
    ▼
VS Code in Browser
    │
    │ (2) Runs: mvn spring-boot:run
    ▼
Backend Starts (Port 8080)
    │
    │ (3) Opens new terminal
    │ (4) Runs: npm start
    ▼
Frontend Starts (Port 3000)
    │
    │ (5) Clicks forwarded port URL
    ▼
Browser opens React App
    │
    │ (6) Makes API calls
    ▼
Backend API (Spring Boot)
    │
    │ (7) Queries database
    ▼
H2 Database
```

## Pre-configured Components

### Java Development
- OpenJDK 17
- Maven 3.9+
- Spring Boot DevTools
- VS Code Java Extension Pack

### React Development
- Node.js 18
- npm 9+
- ESLint
- Prettier
- React DevTools

### Database
- H2 In-Memory Database
- Accessible via /h2-console
- Pre-seeded with sample data

### Extensions
- Java Extension Pack
- Spring Boot Tools
- Spring Boot Dashboard
- ESLint
- Prettier
- ES7+ React Snippets
- Docker Extension

## Resource Usage

| Component | CPU | Memory | Storage |
|-----------|-----|--------|---------|
| Backend (Maven Build) | 1-2 cores | 512MB-1GB | 500MB |
| Backend (Running) | 0.5-1 core | 256MB-512MB | - |
| Frontend (npm install) | 1-2 cores | 512MB | 300MB |
| Frontend (Running) | 0.5 core | 128MB-256MB | - |
| VS Code | 0.2-0.5 core | 256MB-512MB | 100MB |

**Recommended Codespace Size**: 2-core (4GB RAM)
**Optimal Size**: 4-core (8GB RAM)

## Startup Time

| Task | Time |
|------|------|
| Codespace Creation | 30-60 seconds |
| Container Build | 60-90 seconds |
| Post-Create Commands | 60-120 seconds |
| **Total Initial Setup** | **2-4 minutes** |
| Backend Startup | 15-30 seconds |
| Frontend Startup | 10-20 seconds |

## Data Persistence

```
Codespace Storage (Persisted):
├── /workspaces/air_bnb/     (Your code - persisted)
├── ~/.m2/repository/         (Maven cache - persisted)
└── ~/node_modules/           (npm cache - persisted)

Ephemeral (Lost on rebuild):
├── H2 Database (in-memory)
└── Running processes
```

## Network Flow

```
Internet
    │
    ├─► GitHub Codespaces
    │       │
    │       ├─► Your Browser (VS Code UI)
    │       │
    │       └─► Container
    │             │
    │             ├─► Backend (8080)
    │             │     └─► H2 Database
    │             │
    │             └─► Frontend (3000)
    │                   └─► Static Files
    │
    └─► Forwarded URLs
          ├─► frontend-3000.app.github.dev
          └─► backend-8080.app.github.dev
```
