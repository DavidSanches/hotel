package me.david.modules.hotel.feign;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import feign.Response;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import me.david.modules.hotel.domain.City;
import me.david.modules.hotel.domain.Hotel;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Jacksone Decoder for City
 */
public class CityDecoder implements Decoder {

    private ObjectMapper objectMapper;

    public CityDecoder(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {
        JsonNode root = objectMapper.readTree(response.body().asInputStream());

        if (type.toString().startsWith("java.util.List")) { //TODO: find better
            JsonNode citiesNode = root.get("_embedded").get("cities");
            System.out.println("citiesNode = " + citiesNode);
            Iterator<JsonNode> cties = citiesNode.elements();
            List<City> result = new LinkedList<City>();
            while (cties.hasNext()) {
                result.add(city(cties.next()));
            }
            return result;
        }

        return city(root);
    }

    private City city(JsonNode node) {

        String name = node.get("name").toString();
        String country = node.get("country").toString();

        return new City(name, country);
    }
}
