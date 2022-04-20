package thirdSet;

import thirdSet.annotations.JsonSerializable;
import thirdSet.exceptions.JsonSerializationException;

import java.util.Objects;

public class SerializationClient {
    private Object pojoObject;

    public SerializationClient() {}

    public void setPojoObject(Object pojoObject) {
        checkIfSerializable(pojoObject);
        this.pojoObject = pojoObject;
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
}
