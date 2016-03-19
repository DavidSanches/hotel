package me.david.modules.hotel.feign;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import feign.Response;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import me.david.modules.hotel.domain.City;
import me.david.modules.hotel.domain.Hotel;
import me.david.modules.hotel.domain.Person;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Jackson Decoder for Person
 */
public class PersonDecoder implements Decoder {
    //TODO: need refactor to share what is JSON+HAL with other decoders

    private ObjectMapper objectMapper;

    public PersonDecoder(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {
        JsonNode root = objectMapper.readTree(response.body().asInputStream());

        if (type.toString().startsWith("java.util.List")) { //TODO: find better
            JsonNode jsonNode = root.get("_embedded").get("persons"); //TODO: hardcoded
            System.out.println("personsNode = " + jsonNode);

            Iterator<JsonNode> nodes = jsonNode.elements();
            List<Person> result = new LinkedList<Person>();
            while (nodes.hasNext()) {
                JsonNode json = nodes.next();
                System.out.println("json = " + json);
//                objectMapper.readValue()
//
//                result.add(objectMapper.readValue(nodes.next(), Person.class)));
            }
            return result;
        }

//        return hotel(root);
        return null;
    }
}
