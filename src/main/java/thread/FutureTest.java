package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTest {
    public static void main(String[] args) {
        Callable<Integer> c = new Callable<Integer>() {

            @Override
            public Integer call() throws Exception {
                int sum = 0;
                for (int i = 0; i < 10000; i++) {
                    sum += (int) (Math.random() * 20000);
                }
                return sum;
            }
        };

        FutureTask<Integer> task = new FutureTask<>(c);
        int ret = -1;
        try {
            task.run();
            ret = task.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(ret);
    }
}
