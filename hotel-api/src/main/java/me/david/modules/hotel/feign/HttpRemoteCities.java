package me.david.modules.hotel.feign;

import feign.Param;
import feign.RequestLine;
import me.david.modules.hotel.domain.City;
import me.david.modules.hotel.domain.Hotel;

import java.util.List;

/**
 * Feign interface for Cities
 */
public interface HttpRemoteCities {

    @RequestLine("GET /api/cities")
    List<City> list();
}
