package thread;

import spring.transaction.model.Person;

public class ThreadLocalTest {

    /**
     * This is the result of one run
     *
    Thread-1:306;5485
    Thread-0:600;5362
    Thread-2:974;5485
    Thread-2 ends:974;5362
    Thread-0 ends:600;5362
    Thread-1 ends:306;5362

     * @param args
     */
    public static void main(String[] args) {
        Thread[] threads = new Thread[3];
        A a = new A();
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(a);
            threads[i].start();
        }
    }
}

class A implements Runnable {

    ThreadLocal<Integer> t = new ThreadLocal<Integer>();
    Integer b = null;

    ThreadLocal<Person> pt = new ThreadLocal<Person>();

    @Override
    public void run() {
        int random = (int) (Math.random() * 1000);
        b = (int) (Math.random() * 10000);
        System.out.println(Thread.currentThread().getName() + ":" + random + ";" + b);

        t.set(random);

        System.out.println(Thread.currentThread().getName() + " ends:" + t.get() + ";" + b);
    }

}

