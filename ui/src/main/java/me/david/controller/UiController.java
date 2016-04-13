package me.david.controller;

import me.david.modules.hotel.domain.Hotel;
import me.david.modules.hotel.feign.HttpRemoteHotels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Main controller for UI
 */
@RestController
public class UiController {


    @Autowired
    protected HttpRemoteHotels hotels;

    @RequestMapping("hotels")
    public List<Hotel> hotels() {
        List<Hotel> result = hotels.list();
        return result;
    }
}
