package Lessen_1;


import java.util.Collections;
import java.util.Iterator;

public class Main {
    private Apple a1 = new Apple(3, "apple");
    private Apple a2 = new Apple(4, "apple");
    private Apple a3 = new Apple(6, "apple");
    private Apple a4 = new Apple(7, "apple");
    private Apple a5 = new Apple(2, "apple");
    private Orange o1 = new Orange(9, "orange");
    private Orange o2 = new Orange(4, "orange");
    private Orange o3 = new Orange(7, "orange");
    private Mass<Apple> m_a;
    private Mass<Orange> m_o;
    private Box<Apple> b_apple;
    private Box<Orange> b_orange;
    private Box<Apple> newApple;
    private Box<Orange> newOrange;

    public Main() {
        this.m_a = new Mass(new Apple[]{this.a1, this.a2, this.a3, this.a4});
        this.m_o = new Mass(new Orange[]{this.o1, this.o2, this.o3});
        this.b_apple = new Box();
        this.b_orange = new Box();
        this.newApple = new Box();
        this.newOrange = new Box();
    }

    public static void main(String[] args) {
        Main m = new Main();
        m.to_array(m.b_apple, m.m_a);
        m.to_array(m.b_orange, m.m_o);
        m.sumWeight(m.b_apple);
        m.sumWeight(m.b_orange);
        System.out.println(m.b_apple.compare(m.b_orange));
        m.changeBox(m.b_apple, m.newApple);
        m.changeBox(m.b_orange, m.newOrange);
        m.addFruit(m.newApple, m.a5);
    }

    public void to_array(Box b, Mass m) {
        Collections.addAll(b.getAr(), m.getMass());
    }

    public <T extends Fruit> void sumWeight(Box<T> b) {
        int mass = 0;

        Fruit i;
        for(Iterator var3 = b.getAr().iterator(); var3.hasNext(); mass += i.getWeihht()) {
            i = (Fruit)var3.next();
        }

    }

    public <T extends Fruit> void changeBox(Box<T> b, Box<T> newBox) {
        newBox.getAr().addAll(b.getAr());
        b.getAr().clear();
    }

    public <T extends Fruit> void addFruit(Box<T> b, T f) {
        b.getAr().add(f);
        Iterator var3 = b.getAr().iterator();

        while(var3.hasNext()) {
            Fruit i = (Fruit)var3.next();
            System.out.println(i.getWeihht());
        }

    }
}
