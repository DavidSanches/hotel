package me.david.modules.hotel.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Feign;
import me.david.modules.hotel.feign.CityDecoder;
import me.david.modules.hotel.feign.HotelDecoder;
import me.david.modules.hotel.feign.HttpRemoteCities;
import me.david.modules.hotel.feign.HttpRemoteHotels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for Feign for City module
 */
@Configuration
public class CityHttpFeignConfig {

    @Value("${hotelService.url}")
    protected String hotelServiceUrl;

    @Autowired
    protected Feign.Builder feignBuilder;

    @Autowired
    protected ObjectMapper objectMapper;

    @Bean
    public HttpRemoteCities cities() {
        return feignBuilder
                .decoder(new CityDecoder(objectMapper))
                .target(HttpRemoteCities.class, hotelServiceUrl);
    }
}
