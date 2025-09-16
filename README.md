# Eagle Bank Riz Kler

# Eagle Bank Riz

A full-stack banking demo application with:
- **Spring Boot** backend (REST APIs, JWT authentication)
- **React + Vite** frontend
- **Postgres + pgAdmin** via Docker Compose
- **Vitest + React Testing Library** for frontend tests

---

## Quick Start

### Backend
```bash
./mvnw spring-boot:run

### Frontend

cd eagle-bank-ui
npm install
npm run dev

### Database

docker compose up -d

### For the full setup see **docs/SETUP.md**
---------------------------------------------------------------------------------------------------------------------
Spring Boot (Java 21) REST API: Users, Accounts, Transactions + JWT auth.

Eagle Bank

Full-stack Java + React application with authentication, Swagger API docs, and frontend TDD setup.

    Backend – Spring Boot
Run locally
# From project root
./mvnw clean spring-boot:run


App runs at: http://localhost:8080

Swagger UI: http://localhost:8080/swagger-ui/index.html

H2 Console: http://localhost:8080/h2-console

Build JAR
./mvnw clean package
java -jar target/Eagle_Bank_Riz-1.0-SNAPSHOT.jar
-----------------------------------------------------------------------------------------------------------------------

    Frontend – React (Vite)

Frontend lives in eagle-bank-ui
.

Run locally
cd eagle-bank-ui
npm install
npm run dev


App runs at: http://localhost:5173
------------------------------------------------------------------------------------------------------------------------
    Tests

Run all tests (headless)
cd eagle-bank-ui
npm run test

Run with interactive UI
npm run test:ui


Vitest dashboard: http://localhost:51204/vitest
(port may vary)

Coverage
npm run coverage
------------------------------------------------------------------------------------------------------------------------

# create account, capture accountNumber from response
# deposit
curl -X POST "http://localhost:8080/v1/accounts/01012345/transactions/deposit?amount=100.00&description=seed" -H "Authorization: Bearer test-token"

# withdraw
curl -X POST "http://localhost:8080/v1/accounts/01012345/transactions/withdraw?amount=30.00&description=atm" -H "Authorization: Bearer test-token"

# list
curl "http://localhost:8080/v1/accounts/01012345/transactions" -H "Authorization: Bearer test-token"
------------------------------------------------------------------------------------------------------------------------

# create account
curl -s -X POST http://localhost:8080/v1/accounts \
  -H "Content-Type: application/json" \
  -d '{"name":"Riz Kler","accountType":"CURRENT"}' | jq

# list
curl -s http://localhost:8080/v1/accounts | jq

# get by account number (replace 01xxxxxx)
curl -s http://localhost:8080/v1/accounts/01###### | jq

# delete
curl -i -X DELETE http://localhost:8080/v1/accounts/01######

Quick smoke test

