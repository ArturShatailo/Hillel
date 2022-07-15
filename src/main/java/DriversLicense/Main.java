package DriversLicense;
import Technical.Tech;

public class Main implements Database {

    static Inspector i = d -> d.getFines().isEmpty() && d.getIdCardNumber() != null && d.getLicenseNumber() != null;

    public static void main(String[] args) {

        System.out.println("Input amount of drivers: ");
        fillDataBase(Tech.GetInputFunction());
        checkDrivers();

        System.out.println("Show all drivers: ");
        drivers.forEach(System.out::println);
        System.out.println("----------------------------------");

        System.out.println("Show allowed: ");
        driversAllowed.forEach(System.out::println);
        System.out.println("----------------------------------");

        System.out.println("Show not allowed: ");
        driversNotAllowed.forEach(System.out::println);

    }

    /*
    * Checks all Driver objects in 'drivers' collection implemented from Database interface with method checkDriver(),
    * implemented from Inspector interface into 'i' static variable.
    * If checkDriver() returns true, the current Driver object is adding into 'driversAllowed' collection implemented
    * from Database interface;
    * If checkDriver() returns false, the current Driver object is adding into 'driversNotAllowed' collection implemented
    * from Database interface;
    */
    private static void checkDrivers() {
        drivers.forEach(d -> {
            if(i.checkDriver(d)) driversAllowed.add(d);
            else driversNotAllowed.add(d);
        });
    }

    /*
     * Creates new Driver objects in amount equal to received @param 'amount' and adds them into 'drivers' collection,
     * implemented from Database interface. Driver object calls method randomise() implemented from Randomizer interface into
     * Driver class.
     * With 20% probability calls fillFines() static method sending created Driver object.
     * Adds created Driver into 'drivers'
     */
    public static void fillDataBase(int amount) {
        for (int i = 0; i < amount; i++) {
            Driver d = new Driver().randomize();
            if (Tech.getRandom(0, 9) < 2) fillFines(d);
            drivers.add(d);
        }
    }

    /*
    * Creates new Fine objects in random amount from 0 to 9;
    * Adds created Fine objects into 'fines' field of received in @param 'd' object Driver.
    * Fine object calls method randomise() implemented from Randomizer interface into Fine class.
    */
    private static void fillFines(Driver d) {
        for (int i = 0; i < Tech.getRandom(0, 9); i++) {
            d.getFines().add(new Fine(d.getLicenseNumber()).randomize());
        }
    }
}
