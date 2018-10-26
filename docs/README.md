---
home: true
heroImage: /hero.jpg
actionText: Springメモへ →
actionLink: /contents/

features:
- title: Dependency Injection(DI)
  details: 依存性の注入
- title: Aspect-Oriented Programming(AOP)
  details: 割り込み処理
- title: Bean LifeCycle
  details: Beanインスタンスのライフサイクル

footer: Copyright © 2018-present Naoto Imamachi (@imamachi-n)
---

## Spring Initializr
Spring Initializrを使うことで、Spring Bootに必要なフォルダ構成・設定ファイルを入手できます。  
[Spring Initializr](https://start.spring.io/)

## Spring Bootコトハジメ
これだけでSpringが始められる。
```java
@SpringBootApplication
public class SampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleApplication.class, args);
    }
}
```
::: warning 警告
Java 8.x以上が必須です。
:::