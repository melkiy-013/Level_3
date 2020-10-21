package Lessen_1;


public class Orange extends Fruit {
    private String name;

    public String getName() {
        return this.name;
    }

    public Orange(int weihht, String name) {
        super(weihht);
        this.name = name;
    }
}
