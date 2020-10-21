package Lessen_1;


public class Apple extends Fruit {
    private String name;

    public String getName() {
        return this.name;
    }

    public Apple(int weihht, String name) {
        super(weihht);
        this.name = name;
    }
}
