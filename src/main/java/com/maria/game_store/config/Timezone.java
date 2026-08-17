package com.maria.game_store.config;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.util.TimeZone;

@Configuration
public class Timezone {

    @PostConstruct
    public void timezone(){
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }
}
