# WR-M
## ВэбРайз Микросервис
## Демонстрационный вариант

2025-05-11
____
Автор: Юрий Колосов

Наименование проекта в GitHub: 
- **wr-microservice:**

  **https://github.com/yuriy-kolosov/wr-microservice.git**
____
## Общее описание

Микросервис, предоставляющий REST API для управления пользователями и их подписками на сервисы.

Данный проект является демонстрацией API
____
### Описание сервиса:
- API для управления пользователями:
  
  -- создание пользователя
  
  -- получение информации о пользователе
  
  -- обновление данных пользователя
  
  -- удаление пользователя

- API для управления подписками:
  
  -- добавление подписки пользователю
  
  -- получение списка подписок пользователя
    
  -- удаление подписки пользователя

  -- получение топ-3 популярных подписок
____
## Инструкция для локального запуска проекта

### 1 Клонировать исходный код проекта из репозитория GitHub на локальный компьютер: выполнить команду:
- git clone https://github.com/yuriy-kolosov/wr-microservice.git <имя_локального_каталога>

### 2 В локальном каталоге <имя_локального_каталога> выполнить команду:
- docker-compose up

### 3 Выполнить проверку работоспособности микросервиса на основании приведенного ниже описания (файл openapi.yaml) и/или с использованием следующих примеров запросов:

- GET http://localhost:8080/user/1

- POST http://localhost:8080/user
  - Body: {
   "id": 1,
   "email": "user1@wr.ru",
   "password": "user1"
	}

- PUT http://localhost:8080/user/1
  - Body: {
   "id": 1,
   "email": "user11@wr.ru",
   "password": "user11"
	}

- POST http://localhost:8080/user/1/subscriptions
  - Body: {
   "id": 1,
   "subscription": "Subsciption Name 1",
   "subscriberId": 1
	}

- GET http://localhost:8080/user/1/subscriptions

- GET http://localhost:8080/subscriptions/top

- DELETE http://localhost:8080/user/1/subscriptions/1

- DELETE http://localhost:8080/user/1
____
### Описание интерфейса: файл openapi.yaml
```yaml
{
  "openapi": "3.0.1",
  "info": {
    "title": "OpenAPI definition",
    "version": "v0"
  },
  "servers": [
    {
      "url": "http://localhost:8080",
      "description": "Generated server url"
    }
  ],
  "paths": {
    "/user/{id}": {
      "get": {
        "tags": [
          "Пользователи"
        ],
        "summary": "Получить информацию о пользователе",
        "operationId": "getUser",
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "application/json": {
                "schema": {
                  "$ref": "#/components/schemas/UzerDTO"
                }
              }
            }
          },
          "404": {
            "description": "Not Found",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/UzerDTO"
                }
              }
            }
          }
        }
      },
      "put": {
        "tags": [
          "Пользователи"
        ],
        "summary": "Обновить пользователя",
        "operationId": "modifyUser",
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          }
        ],
        "requestBody": {
          "content": {
            "application/json": {
              "schema": {
                "$ref": "#/components/schemas/UzerDTO"
              }
            }
          },
          "required": true
        },
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "application/json": {
                "schema": {
                  "$ref": "#/components/schemas/UzerDTO"
                }
              }
            }
          },
          "400": {
            "description": "Bad Request",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/UzerDTO"
                }
              }
            }
          },
          "404": {
            "description": "Not Found",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/UzerDTO"
                }
              }
            }
          }
        }
      },
      "delete": {
        "tags": [
          "Пользователи"
        ],
        "summary": "Удалить пользователя",
        "operationId": "removeUser",
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "application/json": {
                "schema": {
                  "$ref": "#/components/schemas/UzerDTO"
                }
              }
            }
          },
          "404": {
            "description": "Not Found",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/UzerDTO"
                }
              }
            }
          }
        }
      }
    },
    "/user": {
      "post": {
        "tags": [
          "Пользователи"
        ],
        "summary": "Создать нового пользователя",
        "operationId": "addUser",
        "requestBody": {
          "content": {
            "application/json": {
              "schema": {
                "$ref": "#/components/schemas/UzerDTO"
              }
            }
          },
          "required": true
        },
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "application/json": {
                "schema": {
                  "$ref": "#/components/schemas/UzerDTO"
                }
              }
            }
          },
          "400": {
            "description": "Bad Request",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/UzerDTO"
                }
              }
            }
          }
        }
      }
    },
    "/user/{id}/subscriptions": {
      "get": {
        "tags": [
          "Подписки"
        ],
        "summary": "Получить подписки пользователя",
        "operationId": "getUserSubscriptions",
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "application/json": {
                "schema": {
                  "$ref": "#/components/schemas/SubscriptionDTO"
                }
              }
            }
          },
          "404": {
            "description": "Not Found",
            "content": {
              "*/*": {
                "schema": {
                  "type": "array",
                  "items": {
                    "$ref": "#/components/schemas/SubscriptionDTO"
                  }
                }
              }
            }
          }
        }
      },
      "post": {
        "tags": [
          "Подписки"
        ],
        "summary": "Добавить подписку",
        "operationId": "addUserSubscription",
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          }
        ],
        "requestBody": {
          "content": {
            "application/json": {
              "schema": {
                "$ref": "#/components/schemas/SubscriptionDTO"
              }
            }
          },
          "required": true
        },
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "application/json": {
                "schema": {
                  "$ref": "#/components/schemas/SubscriptionDTO"
                }
              }
            }
          },
          "404": {
            "description": "Not Found",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/SubscriptionDTO"
                }
              }
            }
          },
          "500": {
            "description": "Invalid Data",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/SubscriptionDTO"
                }
              }
            }
          }
        }
      }
    },
    "/subscriptions/top": {
      "get": {
        "tags": [
          "Подписки"
        ],
        "summary": "Получить ТОП-3 популярных подписок",
        "operationId": "getTop3Subscriptions",
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "application/json": {
                "schema": {
                  "$ref": "#/components/schemas/SubscriptionTopDTO"
                }
              }
            }
          },
          "404": {
            "description": "Not Found",
            "content": {
              "*/*": {
                "schema": {
                  "type": "array",
                  "items": {
                    "$ref": "#/components/schemas/SubscriptionTopDTO"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/user/{id}/subscriptions/{sub_id}": {
      "delete": {
        "tags": [
          "Подписки"
        ],
        "summary": "Удалить подписку",
        "operationId": "deleteUserSubscription",
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          },
          {
            "name": "sub_id",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int64"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "application/json": {
                "schema": {
                  "$ref": "#/components/schemas/SubscriptionDTO"
                }
              }
            }
          },
          "404": {
            "description": "Not Found",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/SubscriptionDTO"
                }
              }
            }
          }
        }
      }
    }
  },
  "components": {
    "schemas": {
      "UzerDTO": {
        "required": [
          "email",
          "id",
          "password"
        ],
        "type": "object",
        "properties": {
          "id": {
            "type": "integer",
            "format": "int64"
          },
          "email": {
            "pattern": "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$",
            "type": "string"
          },
          "password": {
            "type": "string"
          }
        }
      },
      "SubscriptionDTO": {
        "required": [
          "id",
          "subscriberId",
          "subscription"
        ],
        "type": "object",
        "properties": {
          "id": {
            "type": "integer",
            "format": "int64"
          },
          "subscription": {
            "type": "string"
          },
          "subscriberId": {
            "type": "integer",
            "format": "int64"
          }
        }
      },
      "SubscriptionTopDTO": {
        "type": "object",
        "properties": {
          "subscription": {
            "type": "string"
          },
          "count": {
            "type": "integer",
            "format": "int64"
          }
        }
      }
    }
  }
}
```
____
### Технологический стек:
- Java 17
- Java Spring Boot 3
- Maven
- WEB RESTful
- PostgreSQL
- Hibernate
- Liquibase
- Lombok
- Mapstruct
- SLF4J
- Swagger
- Docker-compose
____
