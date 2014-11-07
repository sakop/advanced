package www.aic.homework;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ExerciseTest {
    public static void main(String[] args) throws ClassNotFoundException, IOException {
        /**
         * 新建Exercise19_1.txt文件
         * 范亮，请看看这里，你写进去的是不是和你读出来的不一样？
         * FileOutputStream写入的是一个字节，一个字节的上限是128
         * 但是你写进去的是10000以内的int类型的随机数，int是4个字节的，所以他上方的三个字节就被自动截掉了
         * 这就是为什么说不让你们用二进制输入输出流的原因，因为初学者很难理解二进制的字节。
         * 如果要真的记录数字，请使用19.5章的DataOutputStream
         */
        FileOutputStream output = new FileOutputStream("Exercise19_1.txt");
        System.out.println("Write");
        int count = 1;
        for (int i = 1; i <= 2; i++) {
            int write = (int) (Math.random() * 10000);
            System.out.print(write + " ");
            if (count++ % 10 == 0) {
                System.out.println();
            }
            output.write(write);
        }
        System.out.println();
        output.flush();
        output.close();

        /**
         * 同理，FileInputStream的read方法虽然返回值是4个字节的int，其实他的返回的是最低的那一个字节，高三字节是补零的
         * 还有read本身是消耗一个字节的，所以while里出现了，就不要在sysout里再出现了
         */
        FileInputStream input = new FileInputStream("Exercise19_1.txt");
        int i;
        int j = 1;
        while ((i = input.read()) != -1) {
            //错误System.out.print(input.read() + "\t");
            System.out.print(i + " ");
            if (j % 10 == 0) {
                System.out.println();
            }
            j++;
        }
        input.close();
        System.out.println();
        /**
         * 请写一个person类，然后保存该类型的对象到流中去，然后读出来，看看结果
         */


        /**
         *
         * ObjectOutputStream类中拥有所有DataInputStream类的方法，以后我们统一用这个类
         * 存一个对象到Exercise19_5.dat
         */
        ObjectOutputStream output2 = new ObjectOutputStream(new FileOutputStream("Exercise19_5.dat"));
        int[] outputArray = { 1, 2, 3, 4, 5 };
        output2.writeObject(outputArray);
        output2.writeObject(new java.util.Date());
        output2.writeDouble(5.5);
        output2.close();

        ObjectInputStream input2 = new ObjectInputStream(new FileInputStream("Exercise19_5.dat"));
        int[] inputArray = (int[]) input2.readObject();
        java.util.Date date = (java.util.Date) (input2.readObject());
        double inputNumber = input2.readDouble();
        input.close();
        for (int k : inputArray) {
            System.out.print(k + " ");
        }
        System.out.println();
        System.out.println(date);
        System.out.println(inputNumber);


    }
}
