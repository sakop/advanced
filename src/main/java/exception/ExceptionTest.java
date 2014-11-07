package exception;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class ExceptionTest {
    public static void main(String[] args) throws IOException {
        Parent p = new Child();
        p.test();
        try(Scanner s = new Scanner(System.in)){

        }
    }
}

class Parent {
    public void test() throws IOException {

    }
}

class Child extends Parent {
    public void test() throws FileNotFoundException {

    }
}