package taskTwo;

public class Main {
    public static void main(String[] args) {
        MyArray<Integer> integerMyArray = new MyArray<>();

        integerMyArray.add(3).add(6).add(7);
        integerMyArray.remove(0);

        System.out.println(integerMyArray);

        for (Integer num : integerMyArray) {
            System.out.println(num);
        }
    }
}
