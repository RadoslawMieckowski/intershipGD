package thirdSet.serialization.serializer;

import lombok.NonNull;

import java.io.*;
import java.util.Objects;

public final class Serializer {
    private Serializer() {}

    public static <T extends Serializable> void serialize(@NonNull T object, String filePath) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(object);
        } catch (IOException exception) {
            System.err.println("Serialization failed.");
            exception.printStackTrace();
        }
    }

    public static <T> T deserialize(String filePath) {
        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            return (T) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException exception) {
            exception.getMessage();
            return null;
        }
    }
}
