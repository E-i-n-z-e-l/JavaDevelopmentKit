package taskOne;

import java.io.DataInput;
import java.io.InputStream;

/**
 * Это подкласс класса CommonClass с тремя типовыми параметрами: T, K и V. <p></p>
 * 1) T - параметр, который ограничен сравнимым интерфейсом Comparable, что означает, что тип T должен быть сравнимым.<p></p>
 * 2) K - параметр, который ограничен смешанным интерфейсом InputStream и DataInput, что означает, что тип
 * K должен быть подтипом InputStream и иметь возможности ввода данных.<p></p>
 * 3) V - параметр, который ограничен классом Number, что означает, что тип V должен быть подклассом Number.
 *<p></p>
 * Класс MoreCommonClass расширяет класс CommonClass и вызывает конструктор суперкласса с аргументами t, k и v.
 *<p></p>
 * Таким образом, класс MoreCommonClass наследует поля и методы класса CommonClass и использует их с соответствующими
 * типами параметров T, K и V.
 * @param <T>
 * @param <K>
 * @param <V>
 */
public class MoreCommonClass<T extends Comparable<T> ,K extends InputStream & DataInput,V extends Number>
        extends CommonClass<T, K, V> {
    public MoreCommonClass(T t, V v, K k) {
        super(t, k, v);
    }
}
