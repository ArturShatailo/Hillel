package DriversLicense;

import Technical.Tech;

import java.util.ArrayList;
import java.util.List;

public class Driver implements Randomizer<Driver>{

    private String name;
    private String surname;
    private String idCardNumber;
    private String licenseNumber;

    private final List<Fine> fines = new ArrayList<>();

    public Driver(){}

    public Driver(String name, String surname, String idCardNumber, String licenseNumber) {
        this.surname = surname;
        this.name = name;
        this.idCardNumber = idCardNumber;
        this.licenseNumber = licenseNumber;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public List<Fine> getFines() {
        return fines;
    }

    /*
    * Fills 'name' with random value from 'names' array, implemented from Database interface through Randomizer interface that
    * extends Database;
    * Fills 'surname' field with random value from 'surnames' array, implemented in the same way as 'names' array;
    * Fills 'idCardNumber' with value received from method getRandomId(8) implemented from Randomize interface;
    * Fills 'licenseNumber' with value received from method getRandomId(6) implemented from Randomize interface;
     */
    @Override
    public Driver randomize() {
        name = names[Tech.getRandom(0, names.length-1)];
        surname = surnames[Tech.getRandom(0, surnames.length-1)];
        idCardNumber = getRandomId(8);
        licenseNumber = getRandomId(6);
        return this;
    }
    @Override
    public String toString() {
        return "name: '" + name + '\'' +
                ", surname: '" + surname + '\'' +
                ", idCardNumber: '" + idCardNumber + '\'' +
                ", licenseNumber: '" + licenseNumber + '\'' +
                ", fines: " + fines;
    }
}
