package me.david.modules.hotel.feign;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Function;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import feign.FeignException;
import feign.Response;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import me.david.modules.hotel.domain.Person;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Iterator;

/**
 * Jackson Decoder for Person
 */
public class PersonDecoder implements Decoder {
    private static final Log logger = LogFactory.getLog(PersonDecoder.class);

    //TODO: need refactor to share what is JSON+HAL with other decoders

    private ObjectMapper objectMapper;


    Function<JsonNode, Person> nodeToPerson = new Function<JsonNode, Person>() {
        public Person apply(JsonNode input) {
            try {
                return objectMapper.readValue(input.toString(), Person.class);
            } catch (IOException e) {
                logger.error("Can't deserialize Person from input:" + input.toString(), e);
                return null;
            }
        }
    };

    public PersonDecoder(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {
        JsonNode root = objectMapper.readTree(response.body().asInputStream());

        if (type.toString().startsWith("java.util.List")) { //TODO: find better
            JsonNode entities = root.get("_embedded").elements().next();
            Iterator<Person> it = Iterators.transform(entities.elements(), nodeToPerson);
            return Lists.newArrayList(it);
        }
        return nodeToPerson.apply(root);
    }
}
