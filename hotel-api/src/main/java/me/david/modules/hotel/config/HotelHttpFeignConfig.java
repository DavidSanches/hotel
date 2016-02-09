package me.david.modules.hotel.config;

import feign.Feign;
import feign.codec.Decoder;
import me.david.modules.hotel.domain.Hotel;
import me.david.modules.hotel.feign.HttpRemoteHotels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for Feign for Hotel module
 */
@Configuration
public class HotelHttpFeignConfig {

    @Value("${hotelService.url}")
    protected String hotelServiceUrl;

    @Autowired
    protected Feign.Builder feignBuilder;


    @Bean
    public HttpRemoteHotels hotels() {
        return feignBuilder
//                .decoder(hotelDecoder())
                .target(HttpRemoteHotels.class, hotelServiceUrl);
    }

//    private Decoder hotelDecoder() {
//    }
}
