package Lessen_1;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Box<T extends Fruit> {
    private List<T> ar = new ArrayList();

    public List<T> getAr() {
        return this.ar;
    }

    public Box() {
    }

    public <T extends Fruit> boolean compare(Box<T> b) {
        int mass_1 = 0;
        int mass_2 = 0;

        Iterator var4;
        Fruit i;
        for(var4 = b.getAr().iterator(); var4.hasNext(); mass_1 += i.getWeihht()) {
            i = (Fruit)var4.next();
        }

        for(var4 = this.ar.iterator(); var4.hasNext(); mass_2 += i.getWeihht()) {
            i = (Fruit)var4.next();
        }

        return mass_1 == mass_2;
    }
}
