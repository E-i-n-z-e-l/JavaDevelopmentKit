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
