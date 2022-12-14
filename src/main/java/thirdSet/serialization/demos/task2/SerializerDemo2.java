package thirdSet.serialization.demos.task2;

import thirdSet.serialization.models.Client;
import thirdSet.serialization.serializer.Serializer;

import java.io.IOException;

public class SerializerDemo2 {
    public static void main(String[] args) {
//        Client client = new Client("Will", "Willspassword", 48_000);
//        Serializer.serialize(client, "src/main/resources/data/serializationTarget2.ser");
        final String filePath = "src/main/resources/data/serializationTarget2.ser";
        Client clientDeserialized = null;
        try {
            clientDeserialized = Serializer.deserialize(filePath);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(clientDeserialized);
        /*
            4)  Client{name='Will', password='Willspassword', balance=48000, age=null}
            5)  Client{username='null', password='Willspassword', balance=48000, age=null}
            6)  Client{username='null', balance=48000, age=null}
            7)  ClassCastException
            8)  in 6) and 7)
         */
    }
}
