package com.linkling.linklingproject_01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@SpringBootApplication
//@EntityScan({"com.linkling.linklingproject_01.model"})
//@EnableJpaRepositories({"com.linkling.linklingproject_01.repository"})
//public class LinkLingProject01Application {
//
//    public static void main(String[] args) {
//        SpringApplication.run(LinkLingProject01Application.class, args);
//    }
//}

import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EntityScan({"com.linkling.linklingproject_01.model"})
@EnableJpaRepositories({"com.linkling.linklingproject_01.repository"})
public class LinkLingProject01Application {

    public static void main(String[] args) {
        SpringApplication.run(LinkLingProject01Application.class, args);
    }

    @Bean
    public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> webServerFactoryCustomizer() {
        return factory -> factory.setPort(Integer.parseInt(System.getProperty("server.port", "8080")));
    }
}
