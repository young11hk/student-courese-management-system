# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Student Score Management System (SMS) Backend API - a Spring Boot + MyBatis project for managing student scores, courses, teachers, and timetables.

## Commands

```bash
# Build the project
mvn clean package

# Run the application
mvn spring-boot:run

# Or run directly with Java
java -jar target/student-score-backend-0.0.1-SNAPSHOT.jar
```

The backend runs on **port 9121**.

## Database Setup

Create and import the database before running:
```bash
mysql -u root -p -e "CREATE DATABASE student_score_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;"
mysql -u root -p student_score_db < DB_Student_Score.sql
```

Database configuration is in `src/main/resources/application.yml`:
- Database: `student_score_db`
- Port: 3306
- Credentials: configured in application.yml

## Architecture

### Code Structure
```
src/main/java/com/shanzhu/sc/
├── controller/   # REST API endpoints (接收请求)
├── service/      # Business logic (处理逻辑)
├── mapper/       # MyBatis data access (数据库操作)
├── domain/       # Database table entities
├── dto/          # Data Transfer Objects (API返回数据)
├── config/       # Spring configuration classes
└── utils/        # Utilities (JWT token, etc.)
```

### Request Flow
```
Controller → Service → Mapper → MyBatis XML → MySQL Database
                ↓
           Return data to Controller → JSON response to frontend
```

### Key Patterns
- **MyBatis Interface Binding**: Mapper interface methods map to XML `id` attributes by name
- **Service Implementation**: Interfaces in `/service/` with implementations in `/service/.../impl/`
- **DTO vs Domain**: Domain classes map directly to DB tables; DTOs are customized for API responses

## Database Tables (11 total)

| Table | Purpose |
|-------|---------|
| admin, teacher, student | User accounts |
| course | Course info (credits, type, etc.) |
| course_info | Course schedule (room, weeks) |
| teacher_course | Teacher ↔ Course assignments |
| student_course | Student enrollment + scores |
| profession | Majors |
| timetable, week | Timetable scheduling |
| upload | File uploads |

Default login accounts (password: 123456):
- Admin: `admin` (level=0)
- Teacher: `3890290` (level=1)
- Student: `3168901101` (level=2)

## API Examples

```
# Login
GET /api/sms/user/login?username=admin&password=123456&level=0

# Get teacher courses
GET /api/sms/teacher/course/getCourseInfo?teacherId=xxx
```

## Key Dependencies

- Spring Boot 2.5.6
- MyBatis 1.3.1
- MySQL 5.1.47
- JWT (java-jwt 3.4.0)
- PageHelper (pagination)
- Lombok
- Druid (connection pool)