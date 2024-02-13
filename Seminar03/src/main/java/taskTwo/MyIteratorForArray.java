package taskTwo;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class MyIteratorForArray<T> implements Iterator<T> {

    private List<T> list;

    private int currentIndex;

    public MyIteratorForArray(List<T> list) {
        this.list = list;
    }

    public MyIteratorForArray(T[] arr) {
        this.list = Arrays.asList(arr);
    }

    @Override
    public boolean hasNext() {
        return currentIndex < list.size();
    }

    @Override
    public T next() {
        return list.get(currentIndex++);
    }
}
