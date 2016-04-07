package com.rgp.breathe;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

/**
 * Created by munichan on 07-04-2016.
 */
@Configuration
public class DataSourceConfig {

    @Bean
    @Profile("dev")
    DataSource dev(){
        return null;
    }

    @Bean
    @Profile("qa")
    DataSource qa(){
        return null;
    }

    @Bean
    @Profile("cloud")
    DataSource cloud(){
        return null;
    }
}
