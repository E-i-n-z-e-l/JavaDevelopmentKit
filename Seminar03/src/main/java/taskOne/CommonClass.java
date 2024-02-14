package taskOne;

import java.io.DataInput;
import java.io.InputStream;

//Создать обобщенный класс с тремя параметрами (T, V, K).
//Класс содержит три переменные типа (T, V, K),
//конструктор, принимающий на вход параметры типа (T, V, K),
//методы возвращающие значения трех переменных.
//
//Создать метод, выводящий на консоль имена классов для трех переменных класса.
//Наложить ограничения на параметры типа:
//T должен реализовать интерфейс Comparable,
//V должен реализовать интерфейс DataInput и расширять класс InputStream,
//K должен расширять класс Number.

/**
 * Это обобщенный класс с тремя типовыми параметрами: T, V и K. <p></p>
 * 1) T - параметр, который ограничен сравнимым интерфейсом Comparable, что означает, что тип T должен быть сравнимым.<p></p>
 * 2) V - параметр, который ограничен смешанным интерфейсом InputStream и DataInput, что означает, что тип V должен
 * быть подтипом InputStream и иметь возможности ввода данных.<p></p>
 * 3) K - параметр, который ограничен классом Number, что означает, что тип K должен быть подклассом Number.
 * @param <T>
 * @param <V>
 * @param <K>
 */
public class CommonClass<T extends Comparable<T>, V extends InputStream & DataInput, K extends Number> {
    T someT;
    V someV;
    K someK;

    public CommonClass(T someT, V someV, K someK) {
        this.someT = someT;
        this.someV = someV;
        this.someK = someK;
    }

    public T getSomeT() {
        return someT;
    }

    public V getSomeV() {
        return someV;
    }

    public K getSomeK() {
        return someK;
    }

    public String getClassName() {
        return String.format("T = %s, K = %s, V = %s"
                , someT.getClass().getSimpleName()
                , someK.getClass().getSimpleName()
                , someV.getClass().getSimpleName());
    }
}
