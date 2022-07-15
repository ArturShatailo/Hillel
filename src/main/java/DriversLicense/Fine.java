package DriversLicense;

import Technical.Tech;

public class Fine implements Randomizer<Fine>{

    private String date;

    private final String licenseNumber;

    private double amount;

    private String title;

    public Fine(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    /*
    * Fills fields with values from random methods getRandomDate() and getRandomFineType() implemented from Randomizer interface
    * 'amount' field gets random double number from 10 to 10000.
    * Returns this object
    */
    @Override
    public Fine randomize() {
        date = getRandomDate();
        amount = Tech.getRandomDouble(10.0, 10000.0);
        title = getRandomFineType();
        return this;
    }

    @Override
    public String toString() {
        return "date: '" + date + '\'' +
                ", amount: " + amount +
                ", title: '" + title + '\'';
    }
}
