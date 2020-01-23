# Product Management(Product API CRUD Operations)

This is java based backened application which performs CRUD operations on Products

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. 

### Technologies

* Spring Boot 2.2.4.RELEASE
* Java 8
* Docker 19.03.1

### Prerequisites

* JDK 1.8 or higher
* [Git Bash](https://git-scm.com/downloads)
* [Maven](https://maven.apache.org/download.cgi)
* [Curl](https://curl.haxx.se/dlwiz/?type=bin)

### Installing

##### 1) Open Git Bash. Go to the folder where you want to checkout the code.
```
cd git/
```

##### 2) Clone the code from git into the folder
```
git clone https://github.com/sandeeppandey2/productmanagement.git
```

##### 3) Go into the folder 'ProductManagement'
```
cd productManagement
```

##### 4a) Start the Database on local
```
docker-compose up
```
##### 4b) Start the application on local
```
mvn spring-boot:run
```

##### 5) Call the API using curl
```
1.GET

curl --location --request GET 'http://localhost:8080/api/products/1' \
--header 'Content-Type: application/json' \
--data-raw ''

2.GET ALL Products
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
curl --location --request GET 'http://localhost:8080/api/products/' \
--header 'Content-Type: application/json' \
--data-raw ''

3.POST

curl --location --request POST 'http://localhost:8080/api/products/' \
--header 'Content-Type: application/json' \
--data-raw '{
	"name" :"MYProduct1" ,
	"price": 10.0
}'


4.PUT
curl --location --request PUT 'http://localhost:8080/api/products/1' \
--header 'Content-Type: application/json' \
--data-raw '{
	"name" :"MYProduct1" ,
	"price": 101.0
}'


5.DELETE

curl --location --request DELETE 'http://localhost:8080/api/products/2' \
--header 'Content-Type: application/json' \
--data-raw ''


