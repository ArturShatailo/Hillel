package CatsTransfer;

public class Cat {

    private final String name;

    private Address address;

    public Cat(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public void changeAddress(Address address){
        this.address = address;
    }

    @Override
    public String toString() {
        return "name: '" + name + '\'' +
                ", address: " + address;
    }
}
