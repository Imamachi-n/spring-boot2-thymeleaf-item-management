# spring-boot2-thymeleaf-item-management
[![Maintainability](https://api.codeclimate.com/v1/badges/9520982bbd77a626d45f/maintainability)](https://codeclimate.com/github/Imamachi-n/spring-boot2-thymeleaf-item-management/maintainability)
[![codebeat badge](https://codebeat.co/badges/25af3bd1-fbdc-4f6a-b455-2fba931682da)](https://codebeat.co/projects/github-com-imamachi-n-spring-boot2-thymeleaf-item-management-master)

Item management system with Spring Boot2 and Thymeleaf

## Heroku
https://spring-boot-item-management.herokuapp.com/login

## PostgreSQL
デフォルトスキーマ`public`を設定する必要あり。
```bash
docker run -it --rm --name postgres -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=password -e POSTGRES_DB=itemdb -p 5432:5432 -d postgres:11.0
```

## MariaDB
文字コードを`utf8_unicode_ci`に設定する必要あり。
```bash
docker run -it --rm --name mariadb -e MYSQL_DATABASE=itemdb_test -e MYSQL_ROOT_PASSWORD=password -p 3306:3306 -d mariadb:10.3.9
```
