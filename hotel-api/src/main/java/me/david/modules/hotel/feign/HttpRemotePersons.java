package me.david.modules.hotel.feign;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import me.david.modules.hotel.domain.Hotel;
import me.david.modules.hotel.domain.Person;

import java.util.List;

/**
 * Feign interface for {@link Person}
 */
public interface HttpRemotePersons {


    @RequestLine("GET /api/persons")
    List<Person> list();

    @RequestLine("POST /api/persons")
    @Headers("Content-Type: application/json")
    Person save(Person p);

//    http://localhost:8080/api/hotels/search/findByName?name=Swissotel


    @RequestLine("GET /api/persons/search/findByName?name={name}")
    Person find(@Param("name") String name);
}
