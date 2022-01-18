package edu.kpi.mmsa.cars.sell.app.api.confige;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfige {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
