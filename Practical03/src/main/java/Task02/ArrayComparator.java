package Task02;

/* Напишите обобщенный метод compareArrays(), который принимает два массива и возвращает true, если они
одинаковые, и false в противном случае. Массивы могут быть любого типа данных, но должны иметь одинаковую
длину и содержать элементы одного типа. */
public class ArrayComparator {
    public static <T> boolean compareArrays(T[] array1, T[] array2) {
        if (array1.length != array2.length) {
            return false;
        }
        for (int i = 0; i < array1.length; i++) {
            if (!array1[i].equals(array2[i])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Integer[] arr1 = {1, 2, 3};
        Integer[] arr2 = {1, 2, 3};
        boolean result1 = ArrayComparator.compareArrays(arr1, arr2);
        System.out.println(result1);

        String[] arr3 = {"Hello", "World"};
        String[] arr4 = {"Hello", "World"};
        boolean result2 = ArrayComparator.compareArrays(arr3, arr4);
        System.out.println(result2);

        Double[] arr5 = {1.5, 2.5, 3.5};
        Double[] arr6 = {1.5, 2.5, 3.0};
        boolean result3 = ArrayComparator.compareArrays(arr5, arr6);
        System.out.println(result3);
    }
}
