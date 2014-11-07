package reference;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 把WeakReference和PhantomReference交换一下，finalize中打印的句子顺序不同。
 * 因为PhantomReference是先运行finalize，再把phantom对象加入到引用队列的，
 * 而WeakReference则先将其放到引用队列（之后会释放Res对象），再等待垃圾回收调用finalize方法
 *
 * http://stackoverflow.com/questions/17850978/why-can-i-not-get-the-phantomreference-from-the-referencequeue-for-a-finalizable
 * @author cheng.qiu
 *
 */
public class ReferenceTest {

    static Reference<ReferenceTest> gRef = null;
    static boolean flag = false;
    static List<Integer> l = new ArrayList<Integer>();

    public void finalize() throws Throwable {
        super.finalize();
        System.out.println(Thread.currentThread().getName() + ":here");
        /*
         * 由于java在设计的时候认为，没有理由去重写finalize方法，如果重写了，那么她就是trivial的，
         * 因为有可能让对象起死回生（resurrect），
         * 所以finalize结束之后，如果没有复杂的逻辑（比如main函数中的忙等）去操作
         * 内存的话，垃圾回收不会忙着回收该对象，因此要设置一个flag，
         * 然后在main函数中再次显示调用System.gc，这样垃圾回收期才会把PhantomReference放到引用队列中。
         *
         * 但是WeakReference不一样，他是在finalize方法之前就把WeakReference对象放到引用队列中了，
         * 虽然他的finalize方法也会在之后被调用，但是如果不调用第二遍System.gc,垃圾也回收不会忙着回收该对象，这点和phantom是一致的
         *
         * 总的来说，第一遍的System.gc是必要的，这样的话Weak和Phantom的referent无论如何都会执行finalize方法，
         * 但是引用队列而言，phantom对象需要第二次调用System.gc才可以
         */

        flag = true;
    }

    public static void main(String[] args) throws InterruptedException {
        test();
        System.out.println("outside test,we can still reference the outdated object" + gRef.get());
        //WeakReference的话，这里一遍就够了
        System.gc();
        System.out.println(gRef.get());
        //下面的逻辑在使用PhantomReference的时候使用
        //		while(!flag){
        //			Thread.sleep(10);
        //		}
        //		System.gc();
    }

    private static void test() {
        ReferenceTest a = new ReferenceTest();
        Res r = ResourceFacotory.getInstance().getResource(a);
        r.use(a);
        System.out.println("inside test " + gRef.get());
    }

}

class ResourceFacotory {

    private ResourceFacotory() {
        t.start();
    };

    public static ResourceFacotory getInstance() {
        return new ResourceFacotory();
    }

    ReferenceQueue<ReferenceTest> queue = new ReferenceQueue<ReferenceTest>();

    Map<Reference<ReferenceTest>, Res> m = new HashMap<Reference<ReferenceTest>, Res>();

    Thread t = null;
    {
        t = new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    try {
                        @SuppressWarnings("unchecked")
                        WeakReference<ReferenceTest> ref = (WeakReference<ReferenceTest>) queue.remove();
                        System.out.println("ref queue pops");
                        Res r = m.get(ref);
                        r.release();
                        //ref.clear();
                        m.remove(ref);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public Res getResource(ReferenceTest key) {
        Res r = new Res(key);
        WeakReference<ReferenceTest> phantomRef = new WeakReference<ReferenceTest>(key, queue);
        m.put(phantomRef, r);
        ReferenceTest.gRef = phantomRef;
        return r;
    }
}
