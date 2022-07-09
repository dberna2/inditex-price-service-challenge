## Prerequisites

* Java 11+
* Maven 3 installed.

## Execute application test

You can run the application test using the following command: `mvn test`

## Run spring boot application

You can run the application with the following command: `mvn spring-boot:run`

## Service documentación

The documentation was generated using Open Api 3, and it is available
in the following url: http://localhost:8080/swagger-ui/index.html

## Parameter description

| Field           | type   | mandatory | example value       |
|-----------------|--------|-----------|---------------------|
| brandId         | long   | true      | 1                   |
| productId       | long   | true      | 3545                |
| applicationDate | string | true      | 2020-06-14T00:00:00 |

## Curl request

``` curl
curl http://localhost:8080/pricing/1/products/35455\?applicationDate\=2020-06-14T00:00:00
```

## Response

``` javascript
{
    "productId": 35455,
    "brandId": 1,
    "rate": 4,
    "price": 38.95,
    "currency": "EUR",
    "startDate": "2020-06-15T16:00:00",
    "endDate": "2020-12-31T23:59:59"
}
```



