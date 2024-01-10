
# Projeto Spring Boot com Redis - Tutorial de Integração

Este projeto visa demonstrar como integrar o Redis com o Spring Boot da forma mais simples possivel para caching utilizando as anotações @Cacheable, @CacheEvict e a resource file.

## Configuração do Redis
Com o Redis já instalado e em execução. Você pode configurar as propriedades de conexão no arquivo application.properties do Spring Boot.


```
    spring.redis.host=localhost
    spring.redis.port=6379
    spring.cache.type=redis
```
## Dependências Maven
Certifique-se de adicionar a seguinte dependência ao seu arquivo pom.xml para suporte ao Redis.
```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-redis</artifactId>
    </dependency>
</dependencies>
```
## Habilitar o caching

Certifique-se de que a aplicação principal (Application.java) está corretamente configurada para suportar o cache.

```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

### Pronto configuração concluida
[Caso queira um exemplo de como utilizar o caching em algum service](https://github.com/HanselVinicius/RedisQuickStart/blob/main/src/main/java/com/vh/tutorial/redis/redis_tutorial/service/ProductServiceImpl.java)

