# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Student Management Information System (studentMIS) - A Vue 2.x frontend application for managing student scores, courses, timetables, and user accounts. The application supports three user roles: **student**, **teacher**, and **admin**.

## Tech Stack

- Vue 2.5.2 with Vuex 3 and Vue Router 3
- Element UI 2.12.0 for UI components
- Axios for HTTP requests
- Echarts/v-charts for data visualization

## Common Commands

```bash
# Start development server
npm run dev

# Build for production
npm run build
```

- Dev server runs on `localhost:9122`
- API proxy forwards `/api` requests to backend at `localhost:9121`

## Architecture

### Roles-Based Access
The application uses role-based routing and dashboards:
- **Student**: View scores, timetable, personal dashboard
- **Teacher**: Manage courses, scores, timetable, student analysis
- **Admin**: Full system access including user management (student/teacher/admin), data analysis, account settings

### Component Structure

```
src/
├── components/
│   ├── account/      # Account management
│   ├── admin/        # Admin-specific views
│   ├── analysis/     # Data analysis (student/teacher/admin variants)
│   ├── course/       # Course management
│   ├── dashboard/    # Dashboard with role-specific views
│   ├── login/        # Authentication (login form, verify, captcha)
│   ├── score/        # Score management
│   ├── student/      # Student management
│   ├── teacher/      # Teacher management
│   └── timetable/    # Timetable management
├── axios/            # HTTP client configuration
│   └── axiosHelper.js    # Axios interceptors, token handling
├── router/           # Route definitions
│   ├── index.js          # Router configuration with auth guards
│   └── route.js          # Route definitions
├── vuex/             # State management
│   └── store.js          # Vuex store with role/user state
├── common/           # Shared utilities
│   ├── js/               # Utilities (drag, fill, utils, initialize)
│   ├── css/              # Shared styles
│   └── password/         # Password component
└── base/             # Base components (e.g., base-table.vue)
```

### Authentication Flow

- Tokens stored in `localStorage` (`userToken`, `refreshToken`)
- Axios interceptor adds `Authorization` header to all requests
- 401 responses trigger redirect to login after 3 seconds
- Each dashboard routes based on user role (stored in Vuex state)

### API Configuration

- Base URL: `http://localhost:9121/`
- CORS enabled with credentials
- POST requests use `application/x-www-form-urlencoded` content-type

## Key Files

- `config/index.js:16` - Backend API proxy target
- `config/index.js:27` - Development server port (9122)
- `src/axios/axiosHelper.js:4-8` - API base URL and timeout configuration
- `src/router/index.js:15-22` - Proxy configuration for `/api` routes

## Notes

- No test framework configured
- Production builds output to `dist/` directory