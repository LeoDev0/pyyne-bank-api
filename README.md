<h1 align="center">Pyyne Bank API</h1>

<p align="center">
  <img alt="Github top language" src="https://img.shields.io/github/languages/top/leodev0/pyyne-bank-api?color=56BEB8">

  <img alt="Github language count" src="https://img.shields.io/github/languages/count/leodev0/pyyne-bank-api?color=56BEB8">

  <img alt="Repository size" src="https://img.shields.io/github/repo-size/leodev0/pyyne-bank-api?color=56BEB8">
</p>

Project made using the hexagonal architecture for better separation of concerns, isolation between core code and framework code and best testability.

---

### Starting

```bash
# Clone this project
$ git clone https://github.com/leodev0/pyyne-bank-api

# Access
$ cd pyyne-bank-api

# Run the project
$ mvn spring-boot:run

# The server will initialize at <http://localhost:8080>
```
---

### Documentation

- When running the project, you can check its Swagger UI documentation at: ```localhost:8080/swagger-ui.html``` or its JSON only version at ```http://localhost:8080/v2/api-docs```

![swagger](.github/swagger.png)

---

### Tests

- You can run the tests with: ```mvn test```

![tests](.github/tests.png)
