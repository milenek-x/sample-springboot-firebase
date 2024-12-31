# Sample Task Management Application with Spring Boot Backend and Firebase Integration

## Overview

This tutorial provides a step-by-step guide for creating a Spring Boot backend application integrated with Firebase. The application allows users to manage tasks, including creating, updating, and deleting tasks.

## Prerequisites

- Java Development Kit (JDK) 11 or higher
- Maven
- An IDE (e.g., IntelliJ IDEA, Eclipse)
- A Firebase project set up in the Firebase Console

## Getting Started

### Step 1: Create a New Spring Boot Project

1. Go to [Spring Initializr](https://start.spring.io/).
2. Select the following options as in the image:
   ![{66D65AAD-399E-477F-8C0D-1D0E362C44E8}](https://github.com/user-attachments/assets/92bdabeb-f826-4468-afd8-c3864031f672)

3. Click on the **Generate** button to download your project as a ZIP file.
5. Extract the ZIP file and open it in your preferred IDE.

### Step 2: Add Firebase Dependencies

To integrate Firebase into your Spring Boot application, you need to add the Firebase Admin SDK dependency.

1. Open `pom.xml` in your project.
2. Add the following dependency inside the `<dependencies>` tag:
 ```
<dependency>
  <groupId>com.google.firebase</groupId>
  <artifactId>firebase-admin</artifactId>
  <version>9.1.0</version> <!-- Check for the latest version -->
</dependency>
```
3. Save the pom.xml file and allow Maven to download the necessary dependencies.

### Step 3: Configure Firebase and Create a Firestore Database

1. Go to the Firebase Console: Visit Firebase Console.
2. Create a New Project:
   - Click on Add project.
   - Enter your project name and click Continue.
   - (Optional) Enable Google Analytics for your project, then click Create project.
   - Once the project is created, click Continue to go to the project dashboard.
3. Navigate to Project Settings:
   - In the left sidebar, click on the gear icon next to Project Overview to open Project Settings.
4. Set Up Firestore Database:
   - In the left sidebar, click on Firestore Database.
   - Click on Create database.
   - Choose between Start in test mode (allows read/write access) or Start in production mode (requires authentication rules).
     - If you choose test mode, remember to set proper security rules later for production use.
   - Select a location for your Firestore database and click Done.
5. Service Accounts:
   - Navigate to Project Settings > Service accounts.
   - Click on Generate new private key, which will download a JSON file containing your service account credentials.
   - Place this JSON file in your project directory (e.g., src/main/resources/).

### Step 4: Add Firebase Configuration File

1. To initialize Firebase in your Spring Boot application, you will use a configuration file instead of directly initializing it in your main application class.
  - Create a Firebase Configuration Class:
     - Create a new Java class named FirebaseConfig in your project (e.g., in the com.example.taskmanagement package).

### Step 5: Create RESTful Endpoints
1. Create controllers and services to handle CRUD operations for tasks using Firestore as your database.
    - Define a Task model class that represents your task entity.
    - Implement a controller class with methods to handle requests for creating, reading, updating, and deleting tasks.

### Step 6: Testing Out Endpoints with Postman
1. Open Postman.
2. Set up requests for each of your API endpoints:
   - For adding a task, use POST with URL http://localhost:8070/api/v1/tasks.
   - For retrieving tasks, use GET with URL http://localhost:8070/api/v1/tasks.
   - For updating a task, use PUT with URL http://localhost:8070/api/v1/tasks/{id}.
   - For deleting a task, use DELETE with URL http://localhost:8070/api/v1/tasks/{id}.
3. Ensure that you include any necessary headers (e.g., Content-Type: application/json) and body data where required.
   - Test each endpoint and verify that they work as expected by checking responses and data changes in Firestore.

### Step 7: Creating the Frontend

1. Choose a frontend framework or library (e.g., React, Angular, Vue.js) or create a simple HTML/CSS/JavaScript application.
2. Set up HTTP requests from your frontend application to interact with your Spring Boot backend API.
3. Implement forms for adding and updating tasks, as well as displaying tasks retrieved from Firestore through API calls.
4. Ensure proper error handling and user feedback mechanisms are implemented.

## Conclusion
You have successfully created a Spring Boot backend application integrated with Firebase! You can now manage tasks through RESTful APIs and build a frontend interface to interact with these APIs. For further customization and improvements, consider adding features like user authentication, input validation, and error handling.
