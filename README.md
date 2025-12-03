##  Project Description

This system provides a simple **RESTful API** to help students easily find and contact professors within a university. The system allows users to:

* **Look up a professor's contact information** (email address and phone number) for immediate assistance.
* **Discover and contact other professors** associated with a specific academic department.

---

## 💻 How to Run Locally

Follow these steps to set up and run the Professor Contact System API on your local machine:

1.  **Clone the Repository:**
    ```bash
    git clone https://github.com/Youcxf/milestone1
    ```

2.  **Install Dependencies:** Ensure all project dependencies (e.g., Spring Boot, Maven/Gradle dependencies) are installed.

3.  **Database Configuration:**
    The application uses an **H2 in-memory database** for local development and testing.

    The default connection credentials are:

    * **H2-user:** `jadore`
    * **H2-password:** `springboot`




### Professors Endpoints

| Method | Endpoint | Description | HTTP Status Codes |
| :--- | :--- | :--- | :--- |
| **GET** | `/professors` | Retrieve a list of **all professors**. | `200 OK` (List) |
| **GET** | `/professors/{id}` | Retrieve a professor by their ID. | `200 OK` or `404 Not Found` |
| **POST** | `/professors` | Create a **new professor record**. | `201 Created` (with `Location` header) |
| **PUT** | `/professors/{id}` | **Update** an existing professor record by ID. | `200 OK` or `404 Not Found` |
| **DELETE** | `/professors/{id}` | **Delete** a professor record by ID. | `204 No Content` or `404 Not Found` |
| **GET** | `/professors/{id}/departments` | Retrieve the **department** associated with a professor. | `200 OK` or `404 Not Found` |

### 🏢 Departments Endpoints

| Method | Endpoint | Description | HTTP Status Codes |
| :--- | :--- | :--- | :--- |
| **GET** | `/departments` | Retrieve a list of **all departments**. | `200 OK` (List) |
| **GET** | `/departments/{id}` | Retrieve a department by its ID. | `200 OK` or `404 Not Found` |
| **POST** | `/departments` | Create a **new department record**. | `201 Created` (with `Location` header) |
| **PUT** | `/departments/{id}` | **Update** an existing department record by ID. | `200 OK` or `404 Not Found` |
| **DELETE** | `/departments/{id}` | **Delete** a department record by ID. | `204 No Content` or `404 Not Found` |
| **GET** | `/departments/{id}/professors` | Retrieve a list of all **professors in a department**. | `200 OK` (List) or `404 Not Found` |

---


### JSON Requests to test

```json
{
  "email": "john.smith@edu.qc",
  "professorName": "John Smith",
  "professorPhoneNumber": "5145551234",
  "departmentId": 1 
}
{
  "id": 1,
  "email": "john.smith@edu.qc",
  "professorName": "John Smith",
  "professorPhoneNumber": "5145551234",
  "department": {
    "id": 1,
    "departmentName": "Computer Science",
    "departmentBuilding": 4
  }
}
{
  "departmentName": "Computer Science",
  "departmentBuilding": 4
}
{
  "id": 1,
  "departmentName": "Computer Science",
  "departmentBuilding": 4,
  "professors": [
    {
      "id": 1,
      "professorName": "John Smith",
      "email": "john.smith@edu.qc"
    },
    {
      "id": 2,
      "professorName": "Jane Doe",
      "email": "jane.doe@edu.qc"
    }
  ]
}
