package com.global.configuration;

import com.global.repository.UserRpository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InOutConfiguration {

    @Bean
    public UserRpository userRpository(){
        return new UserRpository();
    }
}
