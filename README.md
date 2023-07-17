# RestaurantManagementAPI
[![Java](https://img.shields.io/badge/Java>=8.0-blue.svg)](https://docs.spring.io/spring-boot/docs/0.5.0.M6/api/org/springframework/boot/SpringApplication.html)
[![maven](https://img.shields.io/badge/maven->=3.0.5-green.svg)](https://www.npmjs.com/package/npm/v/5.5.0)
[![springBoot](https://img.shields.io/badge/SpringBoot->=3.0.6-blue.svg)](https://nodejs.org/en/blog/release/v9.3.0)
>This project is a basic web application that allows users to sign in, sign up, and manage their food Orders . Additionally, admin can add new food and view orders created by other users. The application uses authentication tokens to secure user data and ensure that only authenticated users can access certain features of the application.

[Homepage]();

## Framework used
 * Spring Boot
## Languaged Uesd
 * Java
## Data Model
```
* User Model

Id : Integer
userName : string
email : string
password : string
UserType : UserType

* UserType Model

Id : Integer
TypeOfUser : enum
TypeOfUserDescription : String

* Food Model

Id : Integer
foodName : String
foodDescription : String
foodPrice : Double
foodImage : String

* Authentication Token

tokenId : Integer
token : string
tokenCreationDateTime : LocalDateTime
@OneToOne 
users : User

```
## Data Flow
```
1. User send as a request to the application throgh the endpoints
2. the api recived request and sends it to the appropriate controll method
3. the controller method makes a call to the method in service class.
4. he controller method returns a response to the API
5. The API sends the response back to the user
```
## Function Used 
```
* User Controller

POST /user/signup: create a new user account
POST /user/signin: authenticate a user and receive an authentication token

* UserType Controller

POST /role/{email}: create a new UserType

* Food Controller

POST /food: add a new food item
Delete /food / {id} : delete food based on id
GET /food / {foodName} : get all foods based on name
GET /food : get all list of foods

* FoodOrder Controller

POST /order: add a new Order

GET /order : get all FoodOrders of users , if it's admin get all users orders
PUT /food : update order details , status can be updated by only admin.
```
## Data Structure Used
* SQL Database
```
We have used Persistent database to implement CRUD Operations.
```

## Project Summary

The project is a basic web application built using Java and the Spring framework. It allows users to sign up, sign in, and manage their profile information. Users can also create and view order. The application uses authentication tokens to secure user data and ensure that only authenticated users can access certain features. The API endpoints include user signup, signin, and authentication token creation.

## Author

Saurav Kumar

* twiter : [@saurav](https://twitter.com/Sauravjha24)
* Github : [@sjha](https://github.com/sjha24)

## Contributing

Contributions , issues and features requestes are welcome !

Feel free to check issues page

## Show your support

Give a rating if this project help you

## License

Copyright : 2023 [Saurav Kumar]()
This project is [GeekSter](https://www.geekster.in/) license
