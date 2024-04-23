# Change Calculation API

A RESTful API for calculating optimal change amounts given a bill value.

## Technologies Used

* Java
* Spring Boot
* Maven

## Prerequisites

* Java Development Kit (JDK) version 8 or later
* A REST API client tool (cURL, Postman, etc.) or programming language with HTTP request capabilities

## Getting Started (Local)

1. Clone this repository.
2. Navigate to the project directory.
3. Build the project: `mvn clean install` 
4. Run the application: `java -jar target/changeService-0.0.1-SNAPSHOT.jar` 

## Available Endpoints

| Endpoint | Method | Description |
|---|---|---|
| `/api/change` | POST | Calculates optimal change for a given bill amount |

**Request Format**

```json
{ "billAmount": 5,
  "lessCoin": true
}
````

**Response Format**
```json
{
  "changeAmount": 5,
  "coinsReturned": {
      "QUARTER": 20
   }
}
````

**Example Usage**

*Using cURL*
```
curl -X POST http://localhost:8080/api/change \
-H "Content-Type: application/json" \
-d '{"billAmount": 5}'
```


| Endpoint | Method | Description                            |
|---|--------|----------------------------------------|
| `/api/coins/quantity` | PUT   | Update the quantity of avaliable coins |

**Request Format**

```json
{
  "coinType": "QUARTER",
  "quantity": 200
}
````

**Response Format**
```
Coin quantity updated successfully
````

**Example Usage**

*Using cURL*
```
curl -X PUT http://localhost:8080/api/coins/quantity \
-H "Content-Type: application/json" \
-d '{"coinType": "QUARTER", "quantity": 200}'
```