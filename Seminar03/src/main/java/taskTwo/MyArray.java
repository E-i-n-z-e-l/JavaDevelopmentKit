package taskTwo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Этот класс на Java представляет собой реализацию массива с динамическим размером и итерируемым поведением.
 * <p>
 * Класс имеет один типовой параметр E, который представляет тип элементов в массиве.
 * @param <E>
 */
public class MyArray<E> implements Iterable<E> {
    /* В классе есть два приватных поля: array - массив объектов, и size - текущий размер массива. */
    private Object[] array;
    private int size;

    /**
     * Конструктор класса инициализирует массив с начальной ёмкостью 10.
     */
    public MyArray() {
        array = new Object[10];
    }

    /**
     * Метод add(E element) добавляет элемент в массив. Если размер массива достигает его текущей ёмкости,
     * создается новый массив с удвоенной ёмкостью, старые элементы копируются в новый массив с помощью метода
     * System.arraycopy(), а затем новый элемент добавляется в конец массива. В результате метод возвращает
     * текущий объект MyArray<E> для поддержки цепочечных вызовов.
     * @param element
     * @return
     */
    public MyArray<E> add(E element) {
        if (size >= array.length) {
            Object[] newArray = new Object[size * 2];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
        array[size++] = element;
        return this;
    }

    /**
     * Метод remove(int index) удаляет элемент по указанному индексу из массива. Если индекс выходит
     * за пределы размера массива или является отрицательным, метод просто завершается. В противном случае,
     * используя метод System.arraycopy(), элементы справа от удаляемого элемента сдвигаются на одну позицию
     * влево, а размер массива уменьшается на 1.
     * @param index
     */
    public void remove(int index) {
        if (index >= size || index < 0) {
            return;
        }
        System.arraycopy(array, index + 1, array, index, size - index);

        size--;
    }

    /**
     * Переопределенный метод toString() создает строковое представление массива. Он использует StringBuilder
     * для построения строки, затем проходит по элементам массива, добавляет их встроку с разделителями запятой
     * и удаляет последнюю запятую. В конце строки добавляются квадратные скобки и результат возвращается.
     * @return
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append('[');

        for (int i = 0; i < size; i++) {
            builder.append(array[i]).append(", ");
        }
        builder.delete(builder.length() - 2, builder.length());
        builder.append(']');
        return builder.toString();
    }

    /**
     * Переопределенный метод iterator() возвращает объект итератора для итерации по элементам массива.
     * Он преобразует массив в список ArrayList и передает его в кастомный итератор MyIteratorForArray<E>,
     * который реализует интерфейс Iterator<E>.
     * @return
     */
    @Override
    public Iterator<E> iterator() {
        List<E> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add((E) array[i]);
        }
        return new MyIteratorForArray<>(list);
    }
}
