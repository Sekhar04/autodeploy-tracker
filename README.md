# 🚀 AutoDeploy Tracker

A full-stack deployment tracking system built using Spring Boot, MySQL, JPA, JUnit5, Mockito, and a responsive dashboard UI.

---

## ✨ Features

- Create deployments
- Track deployment statuses
- Status flow validation
- Professional dashboard UI
- Dark mode / Light mode
- REST API support
- MySQL database integration
- Unit testing using JUnit 5 & Mockito
- Exception handling

---

## 🛠 Tech Stack

### Backend
- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL

### Frontend
- HTML
- CSS
- JavaScript

### Testing
- JUnit 5
- Mockito

### Tools
- Postman
- Git
- GitHub
- IntelliJ IDEA

---

## 📌 Deployment Status Flow

```text
PENDING → RUNNING → SUCCESS
                     ↘ FAILED
```

Invalid transitions are blocked automatically.

---

## 📷 Dashboard Preview

Professional deployment dashboard with:
- Live deployment table
- Status buttons
- Auto-refresh
- Theme toggle
- Responsive design

---

## 🔌 API Endpoints

### Create Deployment

```http
POST /api/deployments
```

### Get All Deployments

```http
GET /api/deployments
```

### Get Deployment By ID

```http
GET /api/deployments/{id}
```

### Update Deployment Status

```http
PUT /api/deployments/{id}/status
```

---

## 🧪 Testing

Unit tests created using:
- JUnit 5
- Mockito

Tested:
- Deployment creation
- Status updates
- Invalid transitions
- Resource not found exceptions

---

## ⚙️ Setup Instructions

### 1 Clone Repository

```bash
git clone https://github.com/Sekhar04/autodeploy-tracker.git
```

### 2 Configure MySQL

Update:

```properties
src/main/resources/application.properties
```

with your database credentials.

---

### 3 Run Project

```bash
./mvnw spring-boot:run
```

---

## 👨‍💻 Author

Shobhit Sekhar

---

## ⭐ Future Improvements

- JWT Authentication
- Role-based access
- Real CI/CD integration
- Deployment logs
- Docker support
- Kubernetes integration
- Charts & analytics
