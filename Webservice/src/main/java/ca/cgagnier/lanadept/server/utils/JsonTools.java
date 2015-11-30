package ca.cgagnier.lanadept.server.utils;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class JsonTools {


    public static String toJson(Object obj)
            throws JsonMappingException, JsonGenerationException, IOException {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(obj);
    }

}
