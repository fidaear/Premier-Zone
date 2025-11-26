# Hospital Appointment Manager

![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0-green)
![MySQL](https://img.shields.io/badge/MySQL-8.0-orange)
![License](https://img.shields.io/badge/License-MIT-yellow)

A comprehensive hospital appointment management system built with Java Spring Boot that streamlines patient scheduling, doctor management, and medical appointment coordination.

## 📋 Table of Contents

- [Features](#-features)
- [Technology Stack](#-technology-stack)
- [Installation](#-installation)
- [Configuration](#-configuration)
- [API Documentation](#-api-documentation)
- [Usage](#-usage)
- [Project Structure](#-project-structure)
- [Contributing](#-contributing)
- [License](#-license)

## ✨ Features

### 🔐 Authentication & Authorization
- **JWT-based authentication**
- Role-based access control (Patient, Doctor, Admin)
- Secure password encryption
- Session management

### 👥 Patient Management
- Patient registration and profile management
- Medical history tracking
- Appointment history
- Notification preferences

### 🩺 Doctor Management
- Doctor profiles with specialties
- Availability scheduling
- Workload management
- Performance metrics

### 📅 Appointment System
- Real-time appointment booking
- Automated scheduling conflicts detection
- Appointment reminders (email/SMS)
- Cancellation and rescheduling
- Waitlist management

### 🏥 Administrative Features
- Dashboard with analytics
- User management
- System configuration
- Reporting and statistics

## 🛠 Technology Stack

### Backend
- **Java 17**
- **Spring Boot 3.0**
- **Spring Security** with JWT
- **Spring Data JPA**
- **Spring MVC**

### Database
- **MySQL 8.0**
- **Hibernate ORM**
- Database migration tools

### Tools & Utilities
- **Maven** - Dependency management
- **Lombok** - Reduced boilerplate code
- **ModelMapper** - Object mapping
- **JUnit 5** & **Mockito** - Testing
- **Spring Boot Actuator** - Monitoring

## 🚀 Installation

### Prerequisites
- Java 17 or higher
- MySQL 8.0 or higher
- Maven 3.6 or higher

### Step-by-Step Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/fidaear/Hospital-Appointment-Manager.git
   cd Hospital-Appointment-Manager
   ```

2. **Database Configuration**
   ```sql
   CREATE DATABASE hospital_appointment_db;
   CREATE USER 'hospital_user'@'localhost' IDENTIFIED BY 'password';
   GRANT ALL PRIVILEGES ON hospital_appointment_db.* TO 'hospital_user'@'localhost';
   FLUSH PRIVILEGES;
   ```

3. **Application Configuration**
   Create `application.properties` in `src/main/resources/`:
   ```properties
   # Database Configuration
   spring.datasource.url=jdbc:mysql://localhost:3306/hospital_appointment_db
   spring.datasource.username=hospital_user
   spring.datasource.password=password
   
   # JPA Configuration
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   
   # JWT Configuration
   jwt.secret=your-secret-key
   jwt.expiration=86400000
   
   # Email Configuration (for notifications)
   spring.mail.host=smtp.gmail.com
   spring.mail.port=587
   spring.mail.username=your-email@gmail.com
   spring.mail.password=your-app-password
   ```

4. **Build and Run**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

The application will be available at `http://localhost:8080`

## ⚙️ Configuration

### Environment Variables
```bash
export DB_URL=jdbc:mysql://localhost:3306/hospital_appointment_db
export DB_USERNAME=hospital_user
export DB_PASSWORD=password
export JWT_SECRET=your-jwt-secret
export EMAIL_USERNAME=your-email@domain.com
export EMAIL_PASSWORD=your-email-password
```

### Application Properties
Key configuration options in `application.properties`:
```properties
# Server Configuration
server.port=8080
server.servlet.context-path=/api

# JPA Configuration
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

# Logging
logging.level.com.hospital.appointment=DEBUG

# CORS Configuration
cors.allowed.origins=http://localhost:3000
```

## 📚 API Documentation

### Base URL
```
http://localhost:8080/api
```

### Key Endpoints

#### Authentication
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/auth/login` | User login |
| POST | `/auth/register` | User registration |
| POST | `/auth/refresh` | Refresh JWT token |

#### Appointments
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/appointments` | Get all appointments |
| POST | `/appointments` | Create new appointment |
| PUT | `/appointments/{id}` | Update appointment |
| DELETE | `/appointments/{id}` | Cancel appointment |
| GET | `/appointments/patient/{id}` | Get patient appointments |

#### Patients
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/patients` | Get all patients |
| GET | `/patients/{id}` | Get patient by ID |
| PUT | `/patients/{id}` | Update patient profile |

#### Doctors
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/doctors` | Get all doctors |
| GET | `/doctors/specialty/{specialty}` | Get doctors by specialty |
| POST | `/doctors/availability` | Set doctor availability |

### Sample API Request
```bash
# Login
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"john.doe","password":"password123"}'

# Create Appointment
curl -X POST http://localhost:8080/api/appointments \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer your-jwt-token" \
  -d '{
    "patientId": 1,
    "doctorId": 1,
    "appointmentDate": "2024-01-15T10:00:00",
    "reason": "Regular checkup"
  }'
```

## 💻 Usage

### For Patients
1. **Register/Login** to your account
2. **Browse available doctors** and their specialties
3. **Book appointments** based on doctor availability
4. **Manage your appointments** - view, reschedule, or cancel
5. **Receive notifications** for upcoming appointments

### For Doctors
1. **Access your schedule** and upcoming appointments
2. **Set availability** for patient bookings
3. **View patient medical history** before appointments
4. **Update appointment status** and notes

### For Administrators
1. **Manage users** (patients, doctors, staff)
2. **Monitor system performance** through dashboard
3. **Generate reports** on appointment statistics
4. **Configure system settings**

## 📁 Project Structure


Hospital-Appointment-Manager/
├── src/
│   └── main/
│       └── java/
│           └── com/hospital/appointment/
│               ├── controller/         # REST API endpoints
│               ├── service/           # Business logic layer
│               ├── repository/        # Data access layer
│               ├── model/             # Entity classes
│               ├── security/          # Authentication & authorization
│               ├── dto/               # Data Transfer Objects
│               └── config/            # Configuration classes
├── src/main/resources/
│   ├── application.properties         # Application configuration
│   └── static/                        # Static resources
├── pom.xml                           # Maven dependencies
└── README.md


## 🤝 Contributing

We welcome contributions! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

### Development Setup
```bash
# Run tests
mvn test

# Code formatting
mvn spotless:apply

# Build for production
mvn clean package -DskipTests
```

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 📞 Support

For support and questions:
- 📧 Email: fidaear@example.com
- 🐛 Create an [Issue](https://github.com/fidaear/Hospital-Appointment-Manager/issues)
- 💬 Discussions: [GitHub Discussions](https://github.com/fidaear/Hospital-Appointment-Manager/discussions)
