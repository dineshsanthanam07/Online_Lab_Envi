# Online Lab Environment Backend

## How to build in local

### Pre-requisites
1. JDK 25 (Preferably [Bellsoft Libertica Native image kit](https://bell-sw.com/pages/downloads/native-image-kit/#nik-25-(jdk-25)))
2. [gradle](https://gradle.org/)

### Setup in local
1. Clone the repo using ```git clone https://github.com/dineshsanthanam07/Online_Lab_Envi.git```
2. Switch to app directory ```cd Online_Lab_Envi```
3. Build the application by executing ```gradle build```

## ER Diagram
```mermaid
---
title: Lab Environment Schema
---
erDiagram
    COURSE }O--|| FACULTY : created_by
    COURSE {
        string course_id PK "Unique value to identify course"
        string course_name "Name of the course"
        timestamp_with_timezone created_at "Date and time course was created at"
        smallint faculty_id FK "Foreign key to refer Faculty who created/owns this course"
    }
    FACULTY ||--|| USER : signed_up_as
    FACULTY {
        smallint faculty_id PK "Unique value to identify faculty"
        string name "Name of the faculty"
        string department "Name of department this faculty belongs to"
        string email "Email address of this faculty"
        string designation "Designation/Title for this faculty"
        integer user_id FK "Foreign key to refer the platform user details for this faculty"
    }
    USER
    USER {
        integer user_id PK "Unique value to identify user"
        string username "Username to login to the platform"
        string password "Password to login to the platform"
        string role "Role the user has signed up to the platform as. Must be one of 'Student', 'Faculty', 'Admin'"
        string status "Onboarding status indicating if the user has successfully been enrolled in the platform"
    }
    STUDENT ||--|| USER : signed_up_as
    STUDENT {
        bigint roll_no PK "Unique roll number assigned to identify student"
        string name "Name of student"
        string department "Name of department this student belongs to"
        string branch "Name of branch this student belongs to"
        smallint batch "Pass out year in format YYYY for the student"
        integer user_id FK "Foreign key to refer the platform user details for this student"
    }
    COURSE_ENROLLMENT }O--|| STUDENT : enrolls_in
    COURSE_ENROLLMENT }O--|| COURSE : enrolled_in
    COURSE_ENROLLMENT {
        integer course_enroll_id PK "Unique key to identify the enrollment"
        string course_id FK "Foreign key to refer the course enrolled to by the student"
        bigint roll_no FK "Foreign key to refer the student enrolled in the course"
        boolean status "Flag to indicate successful enrollment in the course for the student"
    }
    COURSE_TEST }O--|| COURSE : contains
    COURSE_TEST {
        integer course_test_id PK "Unique key to identify the course test"
        string course_id FK "Foreign key to refer the course which contains this test"
        string title "User friendly title for this test"
        timestamp_with_timezone start_time "Date and time the test was started at"
        interval duration "Duration for the test"
    }
    COURSE_TEST_QUESTION }o--|| COURSE_TEST : contains_in
    COURSE_TEST_QUESTION {
        integer question_id PK "Unique key to identify a question in the test"
        string title "User friendly title"
        string questions "Problem statement to be solved"
        integer test_id FK "Foreign key to refer the course test this question is part of"
    }
    SUBMISSION }|--|| COURSE_TEST_QUESTION : submitted_for
    SUBMISSION }|--|| STUDENT : submitted_by
    SUBMISSION {
        integer submission_id PK "Unique key to identify submission"
        smallint language_id "key to identify programming language this question was attempted in"
        integer test_id FK "Foreign key to refer to the course test question this submission was made for"
        integer student_roll_no FK "Foreign key to refer to the student who made this submission"
        timestamp_with_timezone submission_time "Date and time when submission was made"
        text submitted_code "Code solution submitted by student"
    }
```