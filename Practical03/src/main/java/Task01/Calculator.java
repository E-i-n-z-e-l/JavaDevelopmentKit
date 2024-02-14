package Task01;
/* Написать класс Калькулятор (необобщенный), который содержит обобщенные статические методы: sum(),
multiply(), divide(), subtract(). Параметры этих методов – два числа разного типа, над которыми должна
быть произведена операция. */
public class Calculator {
    public static <T extends Number> T sum(T num1, T num2) {
        if (num1 instanceof Integer && num2 instanceof Integer) {
            return (T) Integer.valueOf(num1.intValue() + num2.intValue());
        } else if (num1 instanceof Double && num2 instanceof Double) {
            return (T) Double.valueOf(num1.doubleValue() + num2.doubleValue());
        } else {
            throw new IllegalArgumentException("Unsupported number types");
        }
    }

    public static <T extends Number> T multiply(T num1, T num2) {
        if (num1 instanceof Integer && num2 instanceof Integer) {
            return (T) Integer.valueOf(num1.intValue() * num2.intValue());
        } else if (num1 instanceof Double && num2 instanceof Double) {
            return (T) Double.valueOf(num1.doubleValue() * num2.doubleValue());
        } else {
            throw new IllegalArgumentException("Unsupported number types");
        }
    }

    public static <T extends Number> T divide(T num1, T num2) {
        if (num1 instanceof Integer && num2 instanceof Integer) {
            return (T) Integer.valueOf(num1.intValue() / num2.intValue());
        } else if (num1 instanceof Double && num2 instanceof Double) {
            return (T) Double.valueOf(num1.doubleValue() / num2.doubleValue());
        } else {
            throw new IllegalArgumentException("Unsupported number types");
        }
    }

    public static <T extends Number> T subtract(T num1, T num2) {
        if (num1 instanceof Integer && num2 instanceof Integer) {
            return (T) Integer.valueOf(num1.intValue() - num2.intValue());
        } else if (num1 instanceof Double && num2 instanceof Double) {
            return (T) Double.valueOf(num1.doubleValue() - num2.doubleValue());
        } else {
            throw new IllegalArgumentException("Unsupported number types");
        }
    }

    public static void main(String[] args) {
        int a = 5;
        int b = 3;
        int sumResult = Calculator.sum(a, b);
        System.out.println(sumResult);
        double divideResult = Calculator.divide(a, b);
        System.out.println(divideResult);

        double x = 2.5;
        double y = 1.5;
        double multiplyResult = Calculator.multiply(x, y);
        double subtractResult = Calculator.subtract(x, y);
        System.out.println(multiplyResult);
        System.out.println(subtractResult);
    }
}
