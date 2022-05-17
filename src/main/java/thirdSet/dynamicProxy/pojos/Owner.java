package thirdSet.dynamicProxy.pojos;

import lombok.*;
import thirdSet.dynamicProxy.interfaces.Printable;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Owner implements Printable {
    private String name;
    private List<Pet> pets;

    public Owner(String name) {
        this.name = name;
    }
}
