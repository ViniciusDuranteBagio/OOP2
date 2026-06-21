package com.aula.oop.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication -> e uma anotacao "combo" que junta 3 outras:
//   @Configuration      -> esta classe pode definir configuracoes/beans
//   @EnableAutoConfiguration -> deixa o Spring Boot configurar tudo automaticamente
//                               (servidor web, JPA, etc) com base nas dependencias do pom.xml
//   @ComponentScan       -> faz o Spring procurar, dentro do pacote com.aula.oop.app
//                           e seus subpacotes, todas as classes anotadas com
//                           @Controller, @Service, @Repository, @Component etc,
//                           para registra-las automaticamente
@SpringBootApplication
public class AppApplication {

    // Metodo main -> e o ponto de entrada do programa Java.
    // SpringApplication.run(...) sobe todo o contexto do Spring:
    // cria os beans, inicia o servidor Tomcat embutido, conecta no banco H2, etc.
    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }

}
