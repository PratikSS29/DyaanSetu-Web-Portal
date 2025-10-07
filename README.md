🧠 Dyaansetu – Project Management & Collaboration Portal
📘 Overview

Dyaansetu is a Project Management and Mentorship Portal designed for students, mentors, alumni, and administrators.
It enables seamless collaboration on academic projects by connecting students with mentors, managing groups, and tracking project progress digitally.

The platform provides:

Student and alumni registration & login

Group creation and leader-based uploads

Mentor assignment and monitoring

Admin oversight for all operations

Secure authentication using JWT

🏗️ Architecture
🧩 N-Tier / Layered Architecture

The project follows a multi-layered architecture that separates concerns for better maintainability, scalability, and security.

Frontend (React.js)
      |
      ▼
Backend (Spring Boot REST APIs)
      |
      ▼
Database (MySQL)

Backend Layers:
Layer	Responsibility
Controller Layer	Handles API requests and responses. Uses @RestController.
Service Layer	Contains business logic. Uses @Service.
Repository Layer	Interacts with the database using Spring Data JPA. Uses @Repository.
Entity Layer	Defines database models using JPA annotations (@Entity).
DTO Layer	Transfers data between layers, ensuring encapsulation.
Mapper Layer	Converts entities to DTOs and vice versa.
Security Layer	Manages JWT authentication, authorization, and roles (Student, Alumni, Admin).

🧰 Tech Stack
Backend

Java 17+
Spring Boot 3+
Spring Data JPA (Hibernate)
Spring Security with JWT
MySQL
Lombok
ModelMapper

🧑‍💻 Roles & Features
🎓 Student

Register and login securely.
Join or create a project group.
View dashboard with group & project details.
Upload project materials (if group leader).

🧑‍🏫 Mentor
View assigned groups.
Monitor student progress.
Approve or give feedback on projects.

🧔 Alumni
Register, login, and share professional insights.
Optionally mentor or guide active students.

👨‍💼 Admin
View all users (students, mentors, alumni).
Delete or modify records.
Assign mentors to groups.
Monitor system activity.

⚙️ API Endpoints
Role	Endpoint	Method	Description
Student	/students/register	POST	Register new student
Student	/students/login	POST	Login and get JWT
Student	/students/dashboard	GET	Fetch student dashboard data
Group	/groups/create	POST	Create new project group
Group	/groups/{id}	GET	Get group details
Admin	/admin/all	GET	View all registered users
Admin	/admin/delete/{id}	DELETE	Remove a user

🔒 Authentication & Security
JWT (JSON Web Token) used for stateless authentication.
Each user (Student, Alumni, Admin) gets a unique token after login.
Role-based access is enforced via SecurityConfig.java.
Protected routes require a valid token in headers:
Authorization: Bearer <token>

🗄️ Database Design (Simplified ER Diagram)
Student (id, firstName, lastName, prnNo, email, branch, division, password, group_id)
Alumni (id, firstName, lastName, email, yearOfPassing, industry, workProfile)
Mentor (id, firstName, lastName, email, department)
Group (id, groupName, leader_id, mentor_id)
Project (id, title, description, fileUrl, group_id)


Relationships:
Group ↔ Student → One-to-Many
Group ↔ Mentor → Many-to-One
Group ↔ Project → One-to-One


Author

Pratik Shinde
Backend Developer | Spring Boot 
📧 pratikshinde2903@gmail.com
