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

$ ./mvnw clean
$ ./mvnw package
$ ./mvnw spring-boot:run

### Actuator health
$ curl -X GET "http://localhost:8080/actuator/health"

### Swagger UI:
Browse to: 
http://localhost:8080/swagger-ui.html#/

test data set is available with json file: 

./request.json,

which can be used to test endpoint: 

/v1/customers/rewardpoints.

It's more convenient to test in Swagger UI:

http://localhost:8080/swagger-ui.html#/Customers%20Rewards%20API/calcualteRewardPointsUsingPOST

