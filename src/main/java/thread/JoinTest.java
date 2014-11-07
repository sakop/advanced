package thread;

import java.lang.Thread.UncaughtExceptionHandler;

public class JoinTest {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        final WaitOnMe me = new WaitOnMe();

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                me.doSomeThing();
            }
        });
        t.start();

        Thread daemon = new Thread(new Runnable() {

            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println("I am daemon");
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    }
                }
                throw new RuntimeException("I am interrupted",new IllegalAccessError());
            }
        });
        //daemon.setDaemon(true);
        daemon.start();
        daemon.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("hello " + e.getMessage() + "->" + e.getCause());
            }
        });

        System.out.println("inside");
        me.waitOnMe();
        System.out.println("Wake up");
        daemon.interrupt();
    }
}

class WaitOnMe {
    public synchronized void waitOnMe() throws InterruptedException {
        wait();
    }

    public synchronized void doSomeThing() {
        try {
            //睡眠是不会让出锁的！只是给出时间片，一定要注意
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("here");
        notifyAll();
    }
}
