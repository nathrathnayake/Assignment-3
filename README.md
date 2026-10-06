# PRO101 Assessment 3
## Student Service Management System

This project is a Java Swing application developed for PRO101 Programming Fundamentals.

The system allows administrative staff to create, search, and manage student service requests.

## Features

- Add student details
- Select a service request type
- Submit student service requests
- Automatically generate unique request IDs
- Search by Student ID or Request ID
- Update request status
- Input validation
- Local file storage for saved requests

## Service Request Types

- Academic Enquiry
- IT Support
- Assessment Support
- General Enquiry

## Request Status

- Pending
- In Progress
- Resolved

## Project Structure

- `Student.java` - stores student information
- `ServiceRequest.java` - stores individual service request information
- `StudentServiceSystem.java` - manages requests and search functionality
- `StudentServiceGUI.java` - contains the Swing graphical user interface
- `LocalStorage.java` - manages saving and loading requests locally

## Technologies Used

- Java
- Java Swing
- Git
- GitHub
- Visual Studio Code

## Running the Program

Compile the Java files:

```bash
javac *.java