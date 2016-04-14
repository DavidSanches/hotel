package me.david.config;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.common.collect.ImmutableList;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import me.david.modules.hotel.config.CityHttpFeignConfig;
import me.david.modules.hotel.config.HotelHttpFeignConfig;
import me.david.modules.hotel.config.PersonHttpFeignConfig;
import me.david.modules.hotel.domain.Hotel;
import me.david.modules.hotel.feign.HotelDecoder;
import me.david.modules.hotel.feign.PersonDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

/**
 * Created by david on 13/04/2016.
 */
@Configuration
@ContextConfiguration(classes = {
        HotelHttpFeignConfig.class,
        CityHttpFeignConfig.class,
        PersonHttpFeignConfig.class
})
public class JavaConfig {

    class HotelModule extends SimpleModule {

        public HotelModule() {
            super("HotelModule");
//            addAbstractTypeMapping(Hotel.class, )
        }
    }

    protected List<Module> modules() {

        return new ImmutableList.Builder<Module>()
                .add(new HotelModule())
                .build();
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModules(modules());
        return mapper;
    }

    @Bean
    public Feign.Builder feignBuilder() {
        ObjectMapper objectMapper = objectMapper();
        return Feign.builder()
                .encoder(new JacksonEncoder(objectMapper))
                .decoder(new JacksonDecoder(objectMapper))
//                .decoder(new HotelDecoder(objectMapper))
//                .decoder(new PersonDecoder(objectMapper))
                ;
    }
}
