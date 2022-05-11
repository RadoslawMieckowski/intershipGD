package thirdSet.dynamicProxy.pojos;

import thirdSet.dynamicProxy.interfaces.Printable;

public class Pet implements Printable {
    private String species;
    private String name;
    private boolean CanFly;
    private Owner owner;

    public Pet() {
    }

    public Pet(String species, String name, boolean canFly, Owner owner) {
        this.species = species;
        this.name = name;
        this.CanFly = canFly;
        this.owner = owner;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCanFly() {
        return CanFly;
    }

    public void setCanFly(boolean canFly) {
        CanFly = canFly;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "species='" + species + '\'' +
                ", name='" + name + '\'' +
                ", CanFly=" + CanFly +
                ", owner=" + owner.getName() +
                '}';
    }
}
