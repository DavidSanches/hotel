package me.david.modules.hotel.config;

import feign.Feign;
import me.david.modules.hotel.domain.Person;
import me.david.modules.hotel.feign.HttpRemoteHotels;
import me.david.modules.hotel.feign.HttpRemotePersons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for Feign for {@link Person} domain
 */
@Configuration
public class PersonHttpFeignConfig {

    @Value("${hotelService.url}")
    protected String serviceUrl;

    @Autowired
    protected Feign.Builder feignBuilder;


    @Bean
    public HttpRemotePersons persons() {
        return feignBuilder
//                .decoder(hotelDecoder())
                .target(HttpRemotePersons.class, serviceUrl);
    }

}
