package taskTwo;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * MyIteratorForArray<T> - это обобщенный класс, где <T> - типовой параметр, обозначающий тип элементов коллекции.
 * <p>
 * Класс реализует интерфейс Iterator<T>, который описывает методы для итерации по элементам коллекции.
 * @param <T>
 */
public class MyIteratorForArray<T> implements Iterator<T> {
    private List<T> list;
    private int currentIndex;
    /* У класса есть два конструктора:
   - public MyIteratorForArray(List<T> list) - принимает список (List) элементов типа T в качестве аргумента.
   Список сохраняется в поле list.

   - public MyIteratorForArray(T[] arr) - принимает массив элементов типа T в качестве аргумента.
   Массив преобразуется в список с помощью Arrays.asList(arr) и сохраняется в поле list.*/

    public MyIteratorForArray(List<T> list) {
        this.list = list;
    }

    public MyIteratorForArray(T[] arr) {
        this.list = Arrays.asList(arr);
    }

    /**
     * Метод hasNext() переопределен из интерфейса Iterator<T>. Он проверяет наличие следующего элемента в коллекции.
     * Возвращает true, если текущий индекс (currentIndex) меньше размера списка list, и false в противном случае.
     * @return
     */
    @Override
    public boolean hasNext() {
        return currentIndex < list.size();
    }

    /**
     * Метод next() также переопределен из интерфейса Iterator<T>. Он возвращает следующий элемент из списка list,
     * используя метод list.get(currentIndex++). Затем инкрементируется currentIndex, чтобы указать на следующий
     * элемент.
     * @return
     */
    @Override
    public T next() {
        return list.get(currentIndex++);
    }
}
