# Medical Terms Dictionary

## Table of Contents
- [Project Overview](#project-overview)
- [Prerequisites](#prerequisites)
- [Setup Instructions](#setup-instructions)
  - [1. Database Setup](#1-database-setup)
  - [2. Backend Setup (Spring Boot)](#2-backend-setup-spring-boot)
  - [3. Frontend Setup (React Vite)](#3-frontend-setup-react-vite)
- [Running the Project](#running-the-project)
- [Project Structure](#project-structure)
- [Additional Notes](#additional-notes)

## Project Overview
Medical Terms Dictionary is a web application that provides users with a searchable database of medical terms, along with functionality to favorite and view related terms. The project consists of:

- A Spring Boot backend (Java) with a REST API to handle requests and interact with a PostgreSQL database.
- A React frontend (JavaScript) built with Vite, styled using Tailwind CSS and styled-components.
- A Python-based data scraper that fetches and populates the database with medical terms from an external source.

This README provides detailed setup instructions to help you run this project locally on your machine.

## Prerequisites
The following software must be installed on your system:

- **Java** - JDK 11 or higher (for Spring Boot)
- **Maven** - Build and dependency management tool for Java
- **PostgreSQL** - Relational database system
- **Node.js and npm** - Node Package Manager for running the frontend
- **Python** - Required to run the data scraper script

## Setup Instructions

### 1. Database Setup
The project uses PostgreSQL to store medical terms data. Follow these instructions to set up PostgreSQL and populate the database using the Python script.

#### Step 1: Install PostgreSQL
- **Windows**: Download PostgreSQL from the official website and follow the installation instructions.
- **Mac**: You can install PostgreSQL using Homebrew with the command:
    ```bash
    brew install postgresql
    ```
- **Linux**: Install using your package manager (e.g., `sudo apt install postgresql`).

#### Step 2: Start PostgreSQL Service
Ensure PostgreSQL is running:
  - **On Mac and Linux**:
    ```bash
    pg_ctl -D /usr/local/var/postgres start
    ```
  - **On Windows**: Use the pgAdmin tool or start from the Services manager.

#### Step 3: Set Up Database and Run Data Scraper
1. **Database Creation**: The data scraper script will automatically create the database if it does not exist.
2. **Script Location**: The data scraper script is located in the `medical-terms-data-scraper` folder, housed in the main project directory.
3. **Run the Data Scraper**:
   - Open the Python file `MedicalTermsDataParser.py` in the `medical-terms-data-scraper` folder.
   - Ensure you have the necessary Python packages installed:
     ```bash
     pip install requests beautifulsoup4 sqlalchemy psycopg2-binary
     ```
   - Run the script from the root directory:
     ```bash
     python medical-terms-data-scraper/MedicalTermsDataParser.py
     ```
   - This script will scrape data from an external source and populate the PostgreSQL database with medical terms.

#### Step 4: Configure PostgreSQL Credentials
After running the data scraper, update the backend configuration file to reflect your PostgreSQL credentials.
- Open the backend configuration file at `src/main/resources/application.properties`.
- Update the following properties with your PostgreSQL credentials:
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/medical_terms_db
    spring.datasource.username=your_postgres_username
    spring.datasource.password=your_postgres_password
    ```

### 2. Backend Setup (Spring Boot)
The backend uses Spring Boot and Maven.

#### Step 1: Install Java Development Kit (JDK)
Ensure JDK 11+ is installed on your system.

#### Step 2: Install Maven
- **Windows**: Download Maven from the Apache website and follow the installation instructions.
- **Mac**: Install via Homebrew:
    ```bash
    brew install maven
    ```
- **Linux**: Install using your package manager (e.g., `sudo apt install maven`).

#### Step 3: Verify Maven Installation
Run the following command to confirm Maven is installed:
  ```bash
  mvn -v
```

#### Step 4: Build the Poject
Navigate to the backend directory (where pom.xml is located) and run:
  ```bash
  mvn clean install
```
This command will download all dependencies specified in `pom.xml` and build the project.

### 3. Frontend Setup (React Vite)

#### Step 1: Install Node.js and npm
- **Windows** & **Mac**: Download from the Node.js website and install.
- **Linux**: Install using your package manager.
Verify installation by running:
  ```bash
    node -v
  ```
  ```bash
    npm -v
  ```
  
#### Step 2: Install Vite and Dependencies
Inside the frontend directory (`medicaltermsdictionary-frontend`), install dependencies:
  ```bash
    npm install
  ```
  ```bash
    npm install axios tailwindcss
  ```

## Running the Project

#### Step 1: Start the Backend (Spring Boot)
Navigate to the backend directory:
  ```bash
    cd medicaltermsdictionary
  ```
Run the application:
  ```bash
    mvn spring-boot:run
  ```
The backend should start on "http://localhost:8080".

#### Step 2: Start the Frontend (React Vite)

Navigate to the frontend directory:
  ```bash
    cd medicaltermsdictionary-frontend
  ```
Run the application:
  ```bash
    npm run dev
  ```
The frontend will start on "http://localhost:5173".

You should now be able to access the full application with both frontend and backend running locally.

## Project Structure
## Backend (Spring Boot)
- Controller: Handles HTTP requests.
- Service: Business logic for handling data.
- Repository: Interacts with the PostgreSQL database.
- Model: Defines data structure (e.g., `MedicalTerm`).

## Frontend (React)
- Components: Reusable UI components (e.g., search bar, term cards).
- Pages: Main pages (Home, Term Details, Favorites).
- Styles: Styling using Tailwind CSS and styled-components.
- Services: API call functions in `services/api.js`.

## Data Scraper (Python)
Located in `medical-terms-data-scraper`
- `MedicalTermsDataParser.py`: Scrapes and populates the PostgreSQL database with medical terms.

## Additional Notes
- Troubleshooting: If you encounter issues connecting to PostgreSQL, ensure the `application.properties` file in the backend contains the correct credentials and that PostgreSQL is running.
- Data Validation: Ensure the scraper successfully populates data by querying the database to check if medical terms are available.
- Customizing Ports: If the default ports (8080 for backend, 5173 for frontend) are unavailable, they can be changed in the Spring Boot `application.properties` file and the Vite config, respectively.
- Frontend Configuration: You can update the API endpoint URLs in `constants/api.js` if needed.

# Thank You
