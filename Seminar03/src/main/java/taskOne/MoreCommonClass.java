package taskOne;

import java.io.DataInput;
import java.io.InputStream;

public class MoreCommonClass<T extends Comparable<T> ,K extends InputStream & DataInput,V extends Number> extends CommonClass<T, K, V> {
    public MoreCommonClass(T t, V v, K k) {
        super(t, k, v);
    }
}
