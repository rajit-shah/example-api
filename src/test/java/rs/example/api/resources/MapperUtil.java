package rs.example.api.resources;

import org.codehaus.jackson.map.ObjectMapper;

public class MapperUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String mapToJson(Object obj) throws Exception {
        return objectMapper.writeValueAsString(obj);
    }

    public static <T> T mapFromJson(String json, Class<T> clazz)
            throws Exception {
        return objectMapper.readValue(json, clazz);
    }
}
