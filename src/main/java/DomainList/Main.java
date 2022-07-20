package DomainList;
import Technical.Tech;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args){

        List<String> links =
                Tech.readFileToString("C:\\Users\\Asus\\IdeaProjects\\Hillel\\src\\main\\java\\DomainList\\urls.txt");

        long time1 = System.currentTimeMillis();
        firstSolution(links);
        System.out.println("TIME IS SPENT: " + (System.currentTimeMillis() - time1));

        System.out.println("\n----------------------------------------\n");

        long time2 = System.currentTimeMillis();
        secondSolution(links);
        System.out.println("TIME IS SPENT: " + (System.currentTimeMillis() - time2));

        System.out.println("\n----------------------------------------\n");

        long time3 = System.currentTimeMillis();
        thirdSolution(links);
        System.out.println("TIME IS SPENT: " + (System.currentTimeMillis() - time3));

        System.out.println("\n----------------------------------------\n");

        long time4 = System.currentTimeMillis();
        fourthSolution(links);
        System.out.println("TIME IS SPENT: " + (System.currentTimeMillis() - time4));

    }




    private static void fourthSolution(List<String> links) {

        Set<Link> sLink = new HashSet<>();

        links.forEach( l -> {
            Link link = new Link(getDomain(l));
            if (!sLink.add(link)) {

                sLink.stream()
                        .filter( m -> m.equals(link) )
                        .findFirst()
                        .ifPresent(a -> a.setAmount(a.amount+1));
            }
        });

        sLink.stream()
                .sorted(Comparator.comparingInt(Link::getAmount).reversed())
                .limit(10)
                .forEach(System.out::println);
    }

    private static void thirdSolution(List<String> links){

        List<Link> oLinks = new ArrayList<>();
        Set<String> sLink = new HashSet<>();

        links.forEach( l -> {
            String a = getDomain(l);
            if (!sLink.add(a)) {

                Link link = new Link(a);

                if(!oLinks.contains(link)) oLinks.add(link);

                link = oLinks.get(oLinks.indexOf(link));
                link.setAmount(link.getAmount() + 1);
            }
        });

        oLinks.stream()
                .sorted(Comparator.comparingInt(Link::getAmount).reversed())
                .limit(10)
                .forEach(System.out::println);

    }

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

    private static String getDomain(String d){
        return d.contains("/") ? d.substring(0, d.indexOf("/")) : d;
    }

    private static void getLast(TreeMap<Integer, String> sortedClinks, Map.Entry<Integer, String> lastEntry, int counter) {
        if(counter < 10){
            System.out.println(lastEntry.getValue() + " : " + lastEntry.getKey());
            counter += 1;
            getLast(sortedClinks, sortedClinks.lowerEntry(lastEntry.getKey()), counter);
        }
    }


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
