package me.david.modules.hotel.feign;

import feign.Param;
import feign.RequestLine;
import me.david.modules.hotel.domain.Hotel;

import java.util.List;

/**
 * Feign interface for Hotels
 */
//@FeignClient("serviceId") //TODO: what's this...
public interface HttpRemoteHotels {

    @RequestLine("GET /api/hotels")
    List<Hotel> list();

    @RequestLine("GET /api/hotels/search/findByName?name={name}")
    Hotel find(@Param("name") String name);
}
