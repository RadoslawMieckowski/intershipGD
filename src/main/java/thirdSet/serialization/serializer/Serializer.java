package thirdSet.serialization.serializer;

import java.io.*;
import java.util.Objects;

public final class Serializer {
    private Serializer() {}

    public static <T extends Serializable> void serialize(T object, String filePath) {
        Objects.requireNonNull(object, "Serialization of null is not allowed!");
        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(object);
        } catch (IOException exception) {
            System.out.println("Serialization failed.");
            exception.printStackTrace();
        }
    }

    public static <T> T deserialize(String filePath) {
        T retrievedObject = null;
        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            retrievedObject = (T) objectInputStream.readObject();
        } catch (IOException exception) {
            exception.getMessage();
        } catch (ClassNotFoundException exception) {
            exception.getMessage();
        }
        return retrievedObject;
    }
}
