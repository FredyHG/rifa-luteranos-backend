Here's the updated README file for a raffle system project using Gradle and PostgreSQL:

---

# Raffle System for Lutheran Church

This project is a raffle system designed for a Lutheran church, implemented using the Spring framework. The system allows the church to manage and conduct raffles efficiently.

## Features

- Ticket purchasing
- Random winner selection
- Management of raffle events

## Technologies Used

- Java
- Spring Boot
- Spring Web
- PostgreSQL
- Docker
- Docker Compose

## Getting Started

Follow these instructions to get a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

Ensure you have the following installed on your machine:

- Java JDK 11 or later
- Gradle
- Docker
- Docker Compose

### Installation

1. Clone the repository:

```bash
git clone https://github.com/your-username/raffle-system.git
cd raffle-system
```

2. Build the project:

```bash
./gradlew clean build
```

### Running the Application

To run the application using Docker Compose, follow these steps:

1. Ensure Docker is running on your machine.

2. In the project root directory:


3. Build and start the application using Docker Compose:

```bash
docker-compose up --build
```

The application should now be running on `http://localhost:8080`.

### Stopping the Application

To stop the application and remove the containers, run:

```bash
docker-compose down
```
