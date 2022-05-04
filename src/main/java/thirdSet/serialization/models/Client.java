package thirdSet.serialization.models;

import java.io.Serializable;

public class Client implements Serializable {
    private final String name;
    private final String password;
    private final Integer balance;
    private Integer age;
    private static final long serialVersionUID = -8237181389219902238L;

    public Client(String name, String password, Integer balance) {
        this.name = name;
        this.password = password;
        this.balance = balance;
    }
}


