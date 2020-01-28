package ru.quillaer.daa.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

//Тут конфигурируются бины, которые мне понрадобится внедрять в разных частях программы
@Configuration
public class BeansConfiguration {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
