package Lessen_4.Zadanie_1;

public class Main {

    private final Object mon = new Object();
    private int suspended = 1;

    public static void main(String[] args) {

        Main m = new Main();

        Thread t1 = new Thread(() -> {
            m.printA();
        });

        Thread t2 = new Thread(() -> {
            m.printB();
        });

        Thread t3 = new Thread(() -> {
            m.printC();
        });

        t1.start();
        t2.start();
        t3.start();
    }

    private void printA() {
        synchronized (mon){
            try{
                for(int i=0; i<5; i++){
                    while (suspended != 1){
                        mon.wait();
                    }
                    System.out.print("A");
                    suspended = 2;
                    mon.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void printB() {
        synchronized (mon){
            try{
                for(int i=0; i<5; i++){
                    while(suspended != 2){
                        mon.wait();
                    }
                    System.out.print("B");
                    suspended = 3;
                    mon.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void printC() {
        synchronized (mon){
            try{
                for(int i=0; i<5; i++){
                    while(suspended != 3){
                        mon.wait();
                    }
                    System.out.print("C");
                    suspended = 1;
                    mon.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
