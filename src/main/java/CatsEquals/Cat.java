package CatsEquals;

public class Cat {

    private String name;
    private String breed;
    private int weight;
    private int age;

    public Cat() {}

    public Cat(String name, String breed, int weight, int age) {
        this.name = name;
        this.breed = breed;
        this.weight = weight;
        this.age = age;
    }

    public Cat(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString(){
        return "Name: " + name+"\nBreed: " + breed+"\nWeight: " + weight+"\nAge: " + age;
    }


    //Default creating by Idea
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Cat cat = (Cat) o;
//        return weight == cat.weight && age == cat.age;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(weight, age);
//    }

    //OwnCreating
    public boolean areCatsEquals(Object u){
        if(u == this) return true;
        if(u == null || this.getClass() != u.getClass()) return false;
        Cat cat = (Cat) u;

        if(String.valueOf(weight).toCharArray().length != String.valueOf(cat.weight).length()){
            return false;
        }
        if(String.valueOf(age).toCharArray().length != String.valueOf(cat.age).toCharArray().length){
            return false;
        }

        for(int i=0; i<String.valueOf(weight).toCharArray().length; i++){
            if(String.valueOf(weight).toCharArray()[i] != String.valueOf(cat.weight).toCharArray()[i]){
                return false;
            }
        }

        for(int i=0; i<String.valueOf(age).toCharArray().length; i++){
            if(String.valueOf(age).toCharArray()[i] != String.valueOf(cat.age).toCharArray()[i]){
                return false;
            }
        }

        return weight == cat.weight && age == cat.age;
    }
}
