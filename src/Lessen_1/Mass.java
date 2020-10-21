package Lessen_1;


public class Mass<T> {
    private T[] mass;

    public T[] getMass() {
        return this.mass;
    }

    public Mass(T[] mass) {
        this.mass = mass;
    }
}
