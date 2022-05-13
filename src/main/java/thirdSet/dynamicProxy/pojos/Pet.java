package thirdSet.dynamicProxy.pojos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import thirdSet.dynamicProxy.interfaces.Printable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Pet implements Printable {
    private String species;
    private String name;
    private boolean canFly;
    private Owner owner;

    public Pet(String species, String name, boolean canFly) {
        this.species = species;
        this.name = name;
        this.canFly = canFly;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "species='" + species + '\'' +
                ", name='" + name + '\'' +
                ", canFly=" + canFly +
                ", owner=" + owner.getName() +
                '}';
    }
}
