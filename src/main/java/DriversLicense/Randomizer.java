package DriversLicense;

import Technical.Tech;

public interface Randomizer<T> extends Database{

    T randomize();

    /*
    * With 80% probability creates String value that includes random numbers from 0 to 9.
    * The amount of iterations (numbers in String) is specified in @param 'numberLength'.
    * Returns created String value or null if 20% probability triggered;
    */
    default String getRandomId(int numberLength) {
        if(Tech.getRandom(0, 9) > 2){
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < numberLength; i++) {
                s.append(Tech.getRandom(0, 9));
            }
            return String.valueOf(s);
        }
        return null;
    }

    /*
    * Gets random String value from 'fineTypes' array implemented from Database interface
    */
    default String getRandomFineType() {
        return fineTypes[Tech.getRandom(0, fineTypes.length-1)];
    }

    /*
     * Gets random String value from 'names' array implemented from Database interface
     */
    default String getRandomName() {
        return names[Tech.getRandom(0, names.length-1)];
    }

    /*
     * Gets random String value from 'surnames' array implemented from Database interface
     */
    default String getRandomSurnames() {
        return surnames[Tech.getRandom(0, surnames.length-1)];
    }

    /*
    * Creates data as a random kit of numbers divided by '-' symbol;
    */
    default String getRandomDate() {
        return Tech.getRandom(1, 30) + "-" + Tech.getRandom(1, 12) + "-" + Tech.getRandom(2000, 2022);
    }

}
