
## To start app
**Run this in the project**
```
docker-compose up
```
Запуск DB, API, Миграциий

____
## Users credentials in migration
| User | Password | Role| 
|:----------------|:----------------|:----------------|
| User1 | 41241241 | ADMIN
| User2 | 41241241 | USER

ADMIN - может редактировать команды и пользователей \n
USER - может только просматривать команды

____
## API endpoints 
| Endpoint | Access | Type|
|:----------------|:----------------|:----------------|
| /api/v1/auth/sign-in | all requests | POST |
| /api/v1/auth/refresh | all requests | POST |
| /api/v1/auth/sign-up | all requests | POST |
|  |  |  |
| /api/v1/teams/ | authorized users | POST |
| /api/v1/teams/{id} | authorized ADMIN | GET |
| /api/v1/teams/{id} | authorized ADMIN | POST |
| /api/v1/teams/update | authorized ADMIN | PUT |
| /api/v1/teams/save | authorized ADMIN | POST |
|  |  |  |
| /api/v1/users | authorized users | PUT |
| /api/v1/users/{id} | authorized ADMIN | GET |
| /api/v1/users/{id} | authorized ADMIN | PUT |
| /api/v1/users/update | authorized ADMIN  | PUT |
____
