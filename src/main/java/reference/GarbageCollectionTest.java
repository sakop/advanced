package reference;

public class GarbageCollectionTest {
    static long NumberOfCreatedInstances = 0;
    static long NumberOfDeletedInstances = 0;

    public GarbageCollectionTest() {
        NumberOfCreatedInstances++;
    }

    static public void main(String args[])
    {
        System.out.println("starting....");
        for (int i = 0;; i++) {
            GarbageCollectionTest obj = new GarbageCollectionTest();
            obj = null;
            if (i % 10000000 == 0)
            {
                System.out.println(
                        NumberOfCreatedInstances - NumberOfDeletedInstances);
            }
        }
    }

    protected void finalize()
    {
    //    NumberOfDeletedInstances++;
    }
}
