# E2E Scenarios & Contract Tests

## 1) Create User
- Contract: POST /v1/users/ (201 on success; 409 on duplicate email)
- Test: UserApiContractIT.createUser_contract_and_status

## 2) Login
- Contract: POST /v1/auth/login -> 200 + JWT
- Tests: AuthApiContractIT.*


# API Scenarios

1. Create user
    - Endpoint: POST /v1/users/
    - Expect: 201, body conforms to openapi.yaml

2. Login user
    - Endpoint: POST /v1/auth/login
    - Expect: 200 + token
      ...

... (add as test/scenarios are completed)
