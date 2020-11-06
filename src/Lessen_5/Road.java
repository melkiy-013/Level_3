package Lessen_5;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Road extends Stage {

    Lock lock = new ReentrantLock();
    CountDownLatch cdl_2;

    public Road(int length, CountDownLatch cdl_2) {
        this.length = length;
        this.cdl_2 = cdl_2;
        this.description = "Дорога " + length + " метров";
    }

    @Override
    public void go(Car c) {
        try {
            System.out.println(c.getName() + " начал этап: " + description);
            Thread.sleep(length / c.getSpeed() * 1000);
            System.out.println(c.getName() + " закончил этап: " + description);
            if(length == 40){
                try{
                    cdl_2.countDown();
                    lock.lock();
                    System.out.println(c.getName() + " Победитель!!!");
                }finally {

                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
