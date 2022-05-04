package thirdSet.serialization.models;

import java.io.Serializable;

public class Client implements Serializable {
    private final String username;
    private final Integer balance;
    private final Integer age;
    private static final long serialVersionUID = -8237181389219902238L;

    public Client(String username, Integer balance, Integer age) {
        this.username = username;
        this.balance = balance;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Client{" +
                "username='" + username + '\'' +
                ", balance=" + balance +
                ", age=" + age +
                '}';
    }
}


