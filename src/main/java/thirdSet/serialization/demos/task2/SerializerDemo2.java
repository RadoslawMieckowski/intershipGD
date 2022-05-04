package thirdSet.serialization.demos.task2;

import thirdSet.serialization.models.Client;
import thirdSet.serialization.serializer.Serializer;

public class SerializerDemo2 {
    public static void main(String[] args) {
//        Client client = new Client("Will", "Willspassword", 48_000);
//        Serializer.serialize(client, "src/main/resources/data/serializationTarget2.ser");
        Client clientDeserialized = Serializer.deserialize(
                "src/main/resources/data/serializationTarget2.ser");
        System.out.println(clientDeserialized);
        /*
            4)  Client{name='Will', password='Willspassword', balance=48000, age=null}
            5)  Client{username='null', password='Willspassword', balance=48000, age=null}
            6)  Client{username='null', balance=48000, age=null}
            7)  ClassCastException
         */
    }
}
