package thirdSet.serialization.models;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@AllArgsConstructor
@ToString
public class Client implements Serializable {
    private final String username;
    private final Double balance;
    private final Integer age;
    private static final long serialVersionUID = -8237181389219902238L;
}


