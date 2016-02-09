package me.david.modules.hotel.feign;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import feign.Response;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import me.david.modules.hotel.domain.City;
import me.david.modules.hotel.domain.Hotel;

import java.util.List;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Jacksone Decoder for Hotel
 */
public class HotelDecoder implements Decoder {

    private ObjectMapper objectMapper;

    public HotelDecoder(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {
        JsonNode root = objectMapper.readTree(response.body().asInputStream());

        if (type.toString().startsWith("java.util.List")) { //TODO: find better
            JsonNode hotelsNode = root.get("_embedded").get("hotels");
            System.out.println("hotelsNode = " + hotelsNode);
            Iterator<JsonNode> htls = hotelsNode.elements();
            List<Hotel> result = new LinkedList<Hotel>();
            while (htls.hasNext()) {
                result.add(hotel(htls.next()));
            }
            return result;
        }

        return hotel(root);
    }

    private Hotel hotel(JsonNode node) {

        String htlName = node.get("name").toString();
        String htlAddress = node.get("address").toString();
        String htlZip = node.get("zip").toString();

        return new Hotel(new City(htlZip, "no country"), htlName);
    }
}
