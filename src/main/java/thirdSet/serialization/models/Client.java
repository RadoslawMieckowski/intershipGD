package thirdSet.serialization.models;

import java.io.Serializable;

public class Client implements Serializable {
    private final String username;
    private final String password;
    private final Integer balance;
    private final Integer age;
    private static final long serialVersionUID = -8237181389219902238L;

    public Client(String username, String password, Integer balance, Integer age) {
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Client{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                ", age=" + age +
                '}';
    }
}


