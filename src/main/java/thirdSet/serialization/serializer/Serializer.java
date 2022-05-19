package thirdSet.serialization.serializer;

import java.io.*;

public final class Serializer {
    private Serializer() {}

    public static <T extends Serializable> void serialize(T object, String filePath) {
        if (object == null) throw new RuntimeException("Serialization of null is not allowed!");
        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(object);
        } catch (IOException exception) {
            System.out.println("Serialization failed.");
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
