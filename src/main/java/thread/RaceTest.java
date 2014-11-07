package thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RaceTest {
    public static void main(String[] args) throws InterruptedException {

        Account account = new Account();
        Thread t1 = new Thread(new TestRunnable(account));
        Thread t2 = new Thread(new TestRunnable(account));
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(account.finalSum());
    }
}

class TestRunnable implements Runnable {

    private Account account;

    public TestRunnable(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            account.transfer();
        }
    }

}

class Account {
    private int account1 = 10000;
    private int account2 = 15000;

    private Lock lock = new ReentrantLock();

    public int finalSum() {
        return account1 + account2;
    }


    public void transfer() {
        lock.lock();
        try {
            int random = (int) (Math.random() * 500);
            int whoseTurn = (int) (Math.random() * 2);
            if (whoseTurn == 0) {
                account1 -= random;
                account2 += random;
            }
            else {
                account1 += random;
                account2 -= random;
            }
        } finally {
            lock.unlock();
        }
    }

}
