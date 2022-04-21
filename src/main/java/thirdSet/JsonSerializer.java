package thirdSet;

import thirdSet.annotations.JsonAttribute;
import thirdSet.annotations.JsonSerializable;
import thirdSet.exceptions.JsonSerializationException;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class JsonSerializer {

    public JsonSerializer() {}

    public String serializePojoObject(Object pojoObject) {
        checkIfSerializable(pojoObject);
        Map<String, String> attributesMap = createAttributesMap(pojoObject);
        return mapToJson(attributesMap);
    }

    private void checkIfSerializable(Object object) {
        if (Objects.isNull(object)) {
            throw new JsonSerializationException("The object to serialize is null");
        }
        Class<?> aClass = object.getClass();
        if (!aClass.isAnnotationPresent(JsonSerializable.class)) {
            throw new JsonSerializationException("The class "
                    + aClass.getSimpleName()
                    + " is not annotated with JsonSerializable");
        }
    }

    private Map<String, String> createAttributesMap(Object object) {
        Class<?> aClass = object.getClass();
        Map<String, String> attributesMap = new LinkedHashMap<>();
        for (Field field : aClass.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(JsonAttribute.class)) {
                try {
                    attributesMap.put(getJsonFieldName(field), (String) field.get(object));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return attributesMap;
    }

    private String getJsonFieldName(Field field) {
        String element = field.getAnnotation(JsonAttribute.class)
                .jsonFieldName();
        return element.isEmpty() ? field.getName() : element;
    }

    private String mapToJson(Map<String, String> attributesMap) {
        String jsonString = attributesMap.entrySet()
                .stream()
                .map(entry -> "\"" + entry.getKey() + "\":\""
                        + entry.getValue() + "\"")
                .collect(Collectors.joining(","));
        return "{" + jsonString + "}";
    }
}
