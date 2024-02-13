package taskTwo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyArray<E> implements Iterable<E> {
    private Object[] array;
    private int size;

    public MyArray() {
        array = new Object[10];
    }

    public MyArray<E> add(E element) {
        if (size >= array.length) {
            Object[] newArray = new Object[size * 2];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
        array[size++] = element;
        return this;
    }

    public void remove(int index) {
        if (index >= size || index < 0) {
            return;
        }
        System.arraycopy(array, index + 1, array, index, size - index);

        size--;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append('[');

        for (int i = 0; i < size; i++) {
            builder.append(array[i]).append(", ");
        }
//        Arrays.stream(array).forEach(x->builder.append(x).append(", "));
        builder.delete(builder.length() - 2, builder.length());
        builder.append(']');
        return builder.toString();
    }

    @Override
    public Iterator<E> iterator() {
        List<E> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add((E) array[i]);
        }
        return new MyIteratorForArray<>(list);
    }
}
