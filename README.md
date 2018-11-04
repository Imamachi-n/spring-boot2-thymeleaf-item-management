# spring-boot2-thymeleaf-item-management
[![Maintainability](https://api.codeclimate.com/v1/badges/9520982bbd77a626d45f/maintainability)](https://codeclimate.com/github/Imamachi-n/spring-boot2-thymeleaf-item-management/maintainability)
[![codebeat badge](https://codebeat.co/badges/25af3bd1-fbdc-4f6a-b455-2fba931682da)](https://codebeat.co/projects/github-com-imamachi-n-spring-boot2-thymeleaf-item-management-master)

This repository is a experimental spring boot2 project for me and NOT a production-ready source code.

## Heroku
https://spring-boot-item-management.herokuapp.com/login

## PostgreSQLの起動
デフォルトスキーマ`public`を設定する必要あり。
以下のコマンドで、日本語の文字コードを設定したpostgreSQLのDockerイメージ生成・起動を行う（テスト用なのでデータの永続化は行っていない）。
```bash
docker build -t postgres-dev -f env/postgresql/Dockerfile env/postgresql/
docker run -it --rm --name postgres-dev -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=password -e POSTGRES_DB=itemdb -p 5433:5432 -d postgres-dev
```

以下のコマンドで、postgreSQLのデータベースが生成されているかチェック。
```bash
docker exec -it postgres-dev psql -U postgres
postgres#=\l
```

## MariaDBの起動
文字コードを`utf8mb4_unicode_ci`に設定する必要あり。
```bash
docker run -it --rm --name mariadb-dev -e MYSQL_DATABASE=itemdb_test -e MYSQL_ROOT_PASSWORD=password -p 3307:3306 -d mariadb:10.3.10 --character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci
```

## Spring Boot2アプリの起動
### 1. Mavenを利用する場合
以下のコマンドを実行し、Webアプリケーションをビルド・起動する。
```
# For MacOS, Linux
./mvnw spring-boot:run

# For Windows
mvnw.cmd spring-boot:run
```

### 2. Dockerを利用する場合
Zulu OpenJDKのDockerコンテナを起動。
```bash
docker build -t zulu-openjdk-dev -f env/jdk/Dockerfile env/jdk/
docker run  --name zulu-openjdk-dev -d zulu-openjdk-dev -e JAR_FILE=C:/Users/imama/Documents/spring-boot2-thymeleaf-item-management/target/itemmanagement-0.0.1-SNAPSHOT.jar
```
