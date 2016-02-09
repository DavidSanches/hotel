package me.david.test;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import feign.Feign;
import me.david.modules.hotel.config.HotelHttpFeignConfig;
import me.david.modules.hotel.domain.Hotel;
import me.david.modules.hotel.feign.HotelDecoder;
import me.david.modules.hotel.feign.HttpRemoteHotels;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Integration test for Hotel Module, targetting HotelRepoService
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        HotelModuleIntegrationTest.BaseConfiguration.class,
        HotelModuleIntegrationTest.TestConfiguration.class,
        HotelHttpFeignConfig.class
})
public class HotelModuleIntegrationTest {

    @Configuration
    static class BaseConfiguration {

        protected List<Module> modules() {

            return new ImmutableList.Builder<Module>()
//                    .add()
                    .build();
        }

        @Bean
        public ObjectMapper objectMapper() {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModules(modules());
            return mapper;
        }
    }

    @Configuration
    @PropertySource("classpath:/test.properties")
    static class TestConfiguration {

        @Autowired
        protected ObjectMapper objectMapper;

        @Bean
        public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
            return new PropertySourcesPlaceholderConfigurer();
        }

        // this bean will be injected into the OrderServiceTest class
        @Bean
        public Feign.Builder feignBuilder() {
            return Feign.builder()
//                    .decoder(new JacksonDecoder(objectMapper))
                    .decoder(new HotelDecoder(objectMapper))
                    ;
        }
    }

    @Autowired
    protected HttpRemoteHotels hotels;

    @Value("${hotelService.url}")
    protected String hotelServiceUrl;

    @Test
    public void checkServerURL() {
        System.out.println("hotelServiceUrl = " + hotelServiceUrl);
        assertThat(hotelServiceUrl).isEqualTo("http://localhost:8080");
    }

    @Test
    public void listHotels_shouldNotBeEmpty() {
        List<Hotel> listHotels = this.hotels.list();
        System.out.println("listHotels = " + listHotels);
        for (Hotel hotel : listHotels) {
            System.out.println("hotel = " + hotel);
        }
        assertThat(listHotels).isNotEmpty();
    }

    @Test
    public void findHotel_nameGiven_shouldNotBeEmpty() {
        Hotel swissotel = this.hotels.find("Swissotel");
        System.out.println("swissotel = " + swissotel);
        assertThat(swissotel).isNotNull();

        Hotel theLangham = this.hotels.find("The Langham");
        System.out.println("theLangham = " + theLangham);
        assertThat(theLangham).isNotNull();
    }
}
