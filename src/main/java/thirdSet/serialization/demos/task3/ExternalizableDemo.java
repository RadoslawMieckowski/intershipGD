package thirdSet.serialization.demos.task3;

import thirdSet.serialization.models.UserExternalizable;
import thirdSet.serialization.serializer.Serializer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ExternalizableDemo {
    public static void main(String[] args) {
        String path = "src/main/resources/data/serializationTarget3.ser";
        UserExternalizable user = new UserExternalizable(
                true, false, false,
                true, true, false
        );
        Serializer.serialize(user, path);
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(path);
            byte[] bytes = inputStream.readAllBytes();
            System.out.println(bytes.length);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
