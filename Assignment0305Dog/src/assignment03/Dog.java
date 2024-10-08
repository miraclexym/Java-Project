package assignment03;

public class Dog {
    private String name;
    private String says;

	public Dog(String name, String says) {
        this.name = name;
        this.says = says;
    }

    public String getName() {
        return name;
    }

    public String getSays() {
        return says;
    }

    public static void main(String[] args) {
        Dog spot = new Dog("Spot", "Ruff!");
        Dog scruffy = new Dog("Scruffy", "Wurf!");

        System.out.println(spot.getName() + " says: " + spot.getSays());
        System.out.println(scruffy.getName() + " says: " + scruffy.getSays());
    }
}

