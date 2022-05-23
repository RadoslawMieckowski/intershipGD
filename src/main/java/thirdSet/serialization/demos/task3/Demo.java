package thirdSet.serialization.demos.task3;

import thirdSet.serialization.models.User;
import thirdSet.serialization.serializer.Serializer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Demo {
    public static void main(String[] args) {
        final String path = "src/main/resources/data/serializationTarget3.ser";
        User user = User.builder()
                        .isActive(true)
                        .isAdmin(false)
                        .isModerator(false)
                        .isVIP(true)
                        .isMuted(true)
                        .isBanned(false)
                    .build();
        Serializer.serialize(user, path);
        try (InputStream inputStream = new FileInputStream(path)){
            byte[] bytes = inputStream.readAllBytes();
            System.out.println(bytes.length);
            //output: 125
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
