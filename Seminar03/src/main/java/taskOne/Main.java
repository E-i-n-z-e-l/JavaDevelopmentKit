package taskOne;

import java.io.DataInputStream;

public class Main {
    public static void main(String[] args) {
        CommonClass<String, DataInputStream, Integer> myClass = new CommonClass<>("abc", new DataInputStream(System.in), 10);
        System.out.println(myClass.getClassName());
    }
}
