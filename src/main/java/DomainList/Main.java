package DomainList;
import Technical.Tech;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static class Ball {
    }

    private static class Board {
        private final List<Ball> balls;

        public Board() {
            balls = new ArrayList<>();
            balls.add(new Ball());
            balls.add(new Ball());
            balls.add(new Ball());
        }

        public int count() {
            return balls.size();
        }
    }

    public static void main(String[] args){

        //Insert file data into List collection
        List<String> links =
                Tech.readFileToString("C:\\Users\\Arthur\\IdeaProjects\\Hillel\\src\\main\\java\\DomainList\\urls.txt");

        //First solution method call with time spent displaying
        long time1 = System.currentTimeMillis();
        firstSolution(links);
        System.out.println("FIRST SOLUTION | TIME IS SPENT: " + (System.currentTimeMillis() - time1));

        System.out.println("\n----------------------------------------\n");

        //Second solution method call with time spent displaying
        long time2 = System.currentTimeMillis();
        secondSolution(links);
        System.out.println("SECOND SOLUTION | TIME IS SPENT: " + (System.currentTimeMillis() - time2));

        System.out.println("\n----------------------------------------\n");

        //Third solution method call with time spent displaying
        long time3 = System.currentTimeMillis();
        thirdSolution(links);
        System.out.println("THIRD SOLUTION | TIME IS SPENT: " + (System.currentTimeMillis() - time3));

        System.out.println("\n----------------------------------------\n");

        //Fourth solution method call with time spent displaying
        long time4 = System.currentTimeMillis();
        fourthSolution(links);
        System.out.println("FOURTH SOLUTION | TIME IS SPENT: " + (System.currentTimeMillis() - time4));


            Board board = new Board();
            System.out.println(board.count());



    }


    /**
     * Iterates received in @param 'links' List and creates new Link objects with data received from method getDomain()
     * method for each String element in 'links'
     *
     * Using forEach lambda method checks if Set 'sLink' collection is already containing the created Link by add() method.
     * If adding is impossible, so Set contains Link, the collection will be iterated using stream API and the object
     * equals to created Link will be found. The 'amount' field of this object will be incremented.
     *
     * Then, to show the most frequently met 10 Link objects, the method iterates 'sLink' again using Stream API
     * and with comparingInt comparator chooses Link objects with the biggest 'amount' field.
     *
     * @param links List of links as String values
     */
    private static void fourthSolution(List<String> links) {

        Set<Link> sLink = new HashSet<>();

        links.forEach( l -> {
            Link link = new Link(getDomain(l));
            if (!sLink.add(link)) {

                sLink.stream()
                        .filter( m -> m.equals(link) )
                        .findFirst()
                        .ifPresent(Link::incrementAmount);
            }
        });

        sLink.stream()
                .sorted(Comparator.comparingInt(Link::getAmount).reversed())
                .limit(10)
                .forEach(System.out::println);
    }

    /**
     * Creates 2 collections: List 'oLinks' and Set 'sLink'. Iterates received in @param 'links' List and creates
     * String objects with data received from method getDomain() method for each String element in 'links'.
     *
     * Checks if Set 'sLink' collection is already containing the created String by add() method.
     * If adding is impossible, so Set contains String 'a', the new Link object will be created with String 'a' constructor
     * parameter. Then, the algorithm checks if 'oLinks' contains created Link 'link' object. If it doesn't, adds the Link
     * object into 'oLinks' collection.
     * Using get() method receives Link from 'oLinks' that is equal to created 'link' object. Increments 'amount' field
     * of found Link object.
     *
     * Then, to show the most frequently met 10 Link objects, the method iterates 'oLink' again using Stream API
     * and with comparingInt comparator chooses Link objects with the biggest 'amount' field.
     *
     * @param links List of links as String values
     */
    private static void thirdSolution(List<String> links){

        List<Link> oLinks = new ArrayList<>();
        Set<String> sLink = new HashSet<>();

        links.forEach( l -> {
            String a = getDomain(l);
            if (!sLink.add(a)) {

                Link link = new Link(a);

                if(!oLinks.contains(link)){
                    link.incrementAmount();
                    oLinks.add(link);
                } else {
                    link = oLinks.get(oLinks.indexOf(link));
                    link.incrementAmount();
                }
            }
        });

        oLinks.stream()
                .sorted(Comparator.comparingInt(Link::getAmount).reversed())
                .limit(10)
                .forEach(System.out::println);

    }

    /**
     * Using Stream API, the method collects String values from @param 'links' into new created Map 'cLinks'.
     * The key is set according to getDomain() method that receives each one by one String from 'links'
     * The value is set 1 initially.
     * According to signature of toMap method, I use merge function of Integer summing up (Binary operator). This function
     * allows to merge (sum up) values that is bind to equal keys.
     *
     * Then, to show the most frequently met 10 domains, the method iterates Entry objects 'cLinks' Map using
     * entrySet() method and Stream API; Them stream is sorting according to Entry comparingByValue() method
     * and prints key and value of each.
     *
     * @param links List of links as String values
     */
    private static void secondSolution(List<String> links) {

        Map<String, Integer> cLinks = links.stream().collect(Collectors.toMap(
                e -> getDomain(e),
                e -> 1,
                Integer::sum));

        cLinks.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(10)
                .forEach( entry -> System.out.println(entry.getKey() + " : " + entry.getValue()));
    }

    /**
     * Creates Set 'bLinks', Map 'cLinks' and TreeMap 'sortedClinks' collections
     * Iterates 'links' List collection using forEach method and creates new String objects from each element of 'links'
     * collection using getDomain() method.
     * Checks if Set 'blinks' contains created String 'a' value by add() method. In case of adding is possible, Set doesn't
     * contain this String 'a' object and in this case it will be just added into Set.
     * In case of 'bLinks' contains 'a', it will be put into 'cLinks' as a key. The value is set as:
     *  - Integer value that is equal to 2 in case of the first entry of 'a' into 'cLinks';
     *  - Integer value that is equal to incremented value of 'cLinks' Entry with key equals to 'a'.
     *
     * All elements from 'cLinks' Map are transferred into 'sortedClinks' TreeMap, so will be sorted automatically by
     * SortedMap interface algorithm.
     *
     * Then, the method getLast() is called to get and print last 10 objects from sortedClinks, that will be the most
     * frequently met 10 domains.
     *
     * @param links List of links as String values
     */
    private static void firstSolution(List<String> links) {

        Set<String> bLinks = new HashSet<>();
        Map<String, Integer> cLinks = new HashMap<>();
        TreeMap<Integer, String> sortedClinks = new TreeMap<>();

        links.forEach( l -> {
            String a = getDomain(l);
            if (!bLinks.add(a)) cLinks.put(a, cLinks.get(a) == null ? 2 : cLinks.get(a) + 1);
        });

        cLinks.forEach((a, b) -> sortedClinks.put(b, a));

        getLast(sortedClinks, sortedClinks.lastEntry(), 0);

    }

    /**
     * Converts String of link into String domain by substring from the first character to '/' symbol, but only
     * in case of requested String contains '/' symbol.
     *
     * @param d String value that is a link (http address).
     * @return substring domain name from the requested link String.
     */
    private static String getDomain(String d){
        return d.contains("/") ? d.substring(0, d.indexOf("/")) : d;
    }

    /**
     * Checks counter variable and if it's smaller than 10, prints value and key of received in @param 'lastEntry' Map Entry.
     * Then, increases counter and calls itself (recursion) with new parameters of counter (incremented) and lastEntry
     * that is now equals to result of lowerEntry() method called for 'sortedClinks' TreeMap and is a previous Entry before
     * the current Entry 'lastEntry' compared by keys.
     *
     * @param sortedClinks TreeMap object with Integer key and String value that is amount of links and domain address accordingly.
     * @param lastEntry the current latest by keys Entry of sortedClinks TreeMap
     * @param counter counter of recursive method
     */
    private static void getLast(TreeMap<Integer, String> sortedClinks, Map.Entry<Integer, String> lastEntry, int counter) {
        if(counter < 10){
            System.out.println(lastEntry.getValue() + " : " + lastEntry.getKey());
            counter += 1;
            getLast(sortedClinks, sortedClinks.lowerEntry(lastEntry.getKey()), counter);
        }
    }

    public static boolean isValidURL(String urlString) {
        try {
            URL url = new URL(urlString);
            url.toURI();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Object of Link that has own equal method and hashCode method to provide proper comparing.
     */
    public static class Link{

        private final String link;

        private int amount = 1;

        public Link(String link) {
            this.link = link;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public void incrementAmount(){
            this.amount = this.amount + 1;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Link l = (Link) o;
            return link.equals(l.link);
        }

        @Override
        public int hashCode() {
            return Objects.hash(link);
        }

        @Override
        public String toString() {
            return link + " : " + amount;
        }
    }

}
