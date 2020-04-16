# Customers rewards API

This project is a demo project, for below requirement:

A retailer offers a rewards program to its customers, awarding points based on each recorded purchase.

A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every dollar spent over $50 in each transaction

(e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points).

Given a record of every transaction during a three month period, calculate the reward points earned for each customer per month and total.

Make up a data set to best demonstrate your solution
Check solution into GitHub

## Usage

### Start up the project:
```bash
$ ./mvnw clean

$ ./mvnw package

$ ./mvnw spring-boot:run
```

### Actuator health
```bash
$ curl -X GET "http://localhost:8080/actuator/health"
```

### Swagger UI:
Browse to: 
http://localhost:8080/swagger-ui.html#/

test data set will be loaded with json file: 

./src/main/resources/transactions.json,

which can be used to test endpoint: 

/v1/customers/{customerID}/rewardpoints

e.g. 
```bash
$ curl -X GET "http://localhost:8080/v1/customers/8e249dcb-b75a-4e42-af04-0615a29c52aa/rewardpoints"
```
expected response:

```json
{
  "customerID": "8e249dcb-b75a-4e42-af04-0615a29c52aa",
  "totalPoints": 226,
  "summarylist": [
    {
      "rewardsMonth": "2020-04",
      "monthlyPoints": 124
    },
    {
      "rewardsMonth": "2020-03",
      "monthlyPoints": 40
    },
    {
      "rewardsMonth": "2020-02",
      "monthlyPoints": 62
    }
  ]
}
```
