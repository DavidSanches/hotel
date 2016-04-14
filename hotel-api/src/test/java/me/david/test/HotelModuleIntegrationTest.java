package me.david.test;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import me.david.modules.hotel.config.CityHttpFeignConfig;
import me.david.modules.hotel.config.HotelHttpFeignConfig;
import me.david.modules.hotel.config.PersonHttpFeignConfig;
import me.david.modules.hotel.domain.City;
import me.david.modules.hotel.domain.Hotel;
import me.david.modules.hotel.domain.Person;
import me.david.modules.hotel.feign.*;
import org.junit.Ignore;
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
        HotelHttpFeignConfig.class,
        CityHttpFeignConfig.class,
        PersonHttpFeignConfig.class
})
@Ignore("need a server running @8080")
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
//                    .client(new OkHttpClient()) //TODO
                    .encoder(new JacksonEncoder(objectMapper))
                    .decoder(new JacksonDecoder(objectMapper))
//                    .decoder(new HotelDecoder(objectMapper))
//                    .decoder(new PersonDecoder(objectMapper))
                    ;
        }
    }

    @Autowired
    protected HttpRemoteHotels hotels;

    @Autowired
    protected HttpRemoteCities cities;

    @Autowired
    protected HttpRemotePersons persons;

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
        System.out.println("listHotels.get(0) = " + listHotels.get(0));
        assertThat(listHotels.get(0)).isNotNull();
    }

    @Test
    public void listCitiess_shouldNotBeEmpty() {
        List<City> listCities = this.cities.list();
        System.out.println("listCities = " + listCities);
        for (City city : listCities) {
            System.out.println("city = " + city);
        }
        assertThat(listCities).isNotEmpty();
        System.out.println("listCities.get(0) = " + listCities.get(0));
        assertThat(listCities.get(0)).isNotNull();
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


    @Test
    public void saveAndFindPerson() {
        String testPersonName = "john";

        List<Person> everybody;
        everybody = persons.list();
        int nbPeople = everybody.size();
//        assertThat(everybody).isEmpty();

        boolean davidAlive = true;
        Person david = new Person(testPersonName, 18, "myphone", "my address",
                davidAlive);
        Person saved = persons.save(david);
        System.out.println("saved = " + saved);
        assertThat(saved).isNotNull();
        assertThat(saved.getName()).isEqualTo(testPersonName);
        assertThat(saved.getAge()).isEqualTo(18);

        everybody = persons.list();
        assertThat(everybody).hasSize(nbPeople + 1);

        Person found = persons.find(testPersonName);
        assertThat(found).isNotNull();
        assertThat(found.getAge()).isEqualTo(18);
        assertThat(found.getId()).isNotNull();
        System.out.println("found.getId() = " + found.getId());

    }
}
