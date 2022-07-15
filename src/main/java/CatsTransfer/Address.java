package CatsTransfer;

public class Address {

    private final String city;
    private final String type;
    private final String name;
    private final String number;

    public Address(String city, String type, String name, String number) {
        this.city = city;
        this.type = type;
        this.name = name;
        this.number = number;
    }

    @Override
    public String toString(){
        return "(city: "+city+", type: "+type+", name: "+name+", number: "+number+")";
    }

}
