
# Drift Secondary school Grading System

Drift grading system is a java spring boot project backend, implemented to automate grading of student marks


## Acknowledgements

- [Backend Engineer Assignment](https://www.drift.co.ke/)

## Authors

- [@cheruiyotseasoned](https://www.github.com/cheruiyotseasoned)


## Demo

Grading system gif demo


## Tech Stack


**Server:** Spring Boot, java, mysql

To create a README file for your APIs, you can provide information about the purpose of your APIs, how to use them, and any other relevant details. Here's an example structure you can follow:

# API Documentation

## Introduction
This API documentation provides details about the endpoints and functionality of our API. It is intended to help developers understand and utilize the available resources effectively.

## Student Base URL
The base URL for all API endpoints is `http://localhost:8088/api/v1/student/`.
## Subject Base URL
The base URL for all API endpoints is `http://localhost:8088/api/v1/subject/`.
## Scores Base URL
The base URL for all API endpoints is `http://localhost:8088/api/v1/scores/`.

## Authentication
No API Authentication.
## Error Handling
The API uses appropriate HTTP status codes to indicate the success or failure of requests. In case of an error, the response will include a JSON object with an error message.

## Endpoints

**Student API URLs:**
- GET http://localhost:8088/api/v1/student/ranked: Retrieves the ranked ranking sheet of students.
- POST http://localhost:8088/api/v1/student/add: Adds a new student.
```json
{
  "adm": 4,
  "firstName": "kelvin",
  "lastName": "tenai",
  "subjects": [
    {
      "subjectId": 1
    },
    {
      "subjectId": 2
    },{
      "subjectId": 3
    },
    {
      "subjectId": 4
    },
    {
      "subjectId": 5
    },
    {
      "subjectId": 6
    },
    {
      "subjectId": 9
    },
    {
      "subjectId": 12
    }
  ]
}

```
- DELETE http://localhost:8088/api/v1/student/{studentAdm}: Deletes a student.
- PUT http://localhost:8088/api/v1/student/{studentAdm}: Updates a student's information.

**Subject API URLs:**
- GET http://localhost:8088/api/v1/subject/list: Retrieves a list of subjects.
- POST http://localhost:8088/api/v1/subject/add: Adds a new subject.
- ```json
    {
    "name": "Maths",
    "category": "Compulsory"
  },
  {
    "name": "Eng",
    "category": "Compulsory"
  },
  {
    "name": "Kis",
    "category": "Compulsory"
  },
  {
    "name": "Bio",
    "category": "Sciences"
  },
  {
    "name": "Phy",
    "category": "Sciences"
  },
  {
    "name": "Chem",
    "category": "Sciences"
  },
  {
    "name": "Geog",
    "category": "Humanities"
  },
  {
    "name": "CRE",
    "category": "Humanities"
  },
  {
    "name": "Histo",
    "category": "Humanities"
  },
  {
    "name": "Agri",
    "category": "Technical"
  },
  {
    "name": "Comp",
    "category": "Technical"
  },
  {
    "name": "BeD",
    "category": "Technical"
  }


  ```
- DELETE http://localhost:8088/api/v1/subject/{subjectId}: Deletes a subject by its ID.
- PUT http://localhost:8088/api/v1/subject/{subjectId}: Updates a subject's information by its ID.

**Score API URLs:**
- GET http://localhost:8088/api/v1/scores/list: Retrieves a list of scores.
- POST http://localhost:8088/api/v1/scores/{studentAdm}/{subjectId}: Adds scores for a specific student and subject.
- 

```json
{
  "adm": 3,
  "subjectId": 1,
  "score": 80
}
```
  

- DELETE http://localhost:8088/api/v1/scores/{studentAdm}/{subjectId}: Deletes scores for a specific student and subject.
- PUT http://localhost:8088/api/v1/scores/{studentAdm}/{subjectId}: Updates scores for a specific student and subject.
- GET http://localhost:8088/api/v1/scores/subject-mean/{subject_name}: Retrieves the mean score per subject.


### 1. Add Score with (score base url)
- URL: `/add`
- Method: `POST`
- Description: Add a new score for a student in a subject.
- Parameters:
    - `adm` (required): The admission number of the student.
    - `subjectId` (required): The ID of the subject.
    - `score` (required): The score obtained by the student.
- Example Request:
  ```bash
  POST /add
  Content-Type: application/json

  {
    "adm": 12345,
    "subjectId": 56789,
    "score": 80
  }
  ```
- Example Response:
  ```json
  {
    "message": "Score added successfully"
  }
  ```

### 2. Update Subject
- URL: `/{studentAdm}/{subjectId}`
- Method: `PUT`
- Description: Update the score for a student in a subject.
- Parameters:
    - `studentAdm` (path variable): The admission number of the student.
    - `subjectId` (path variable): The ID of the subject.
    - `score` (request body, optional): The updated score.
- Example Request:
  ```bash
  PUT /12345/56789
  Content-Type: application/json

  {
    "score": 90
  }
  ```
- Example Response:
  ```json
  {
    "message": "Score updated successfully"
  }
  ```

### 3. Get Student Scores
- URL: `/students/{studentAdm}/`
- Method: `GET`
- Description: Retrieve the scores of a specific student.
- Parameters:
    - `studentAdm` (path variable): The admission number of the student.
- Example Request:
  ```bash
  GET /api/v1/students/12345/
  ```
- Example Response:
  ```json
  {
    "studentAdm": 12345,
    "scores": [
      {
        "subjectId": 56789,
        "score": 80
      },
      {
        "subjectId": 98765,
        "score": 75
      }
    ]
  }
  
  ```

## Conclusion
End Of Assignment