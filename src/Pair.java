import java.util.Comparator;

public class Pair<T, T1> {

    private T a;
    private T1 b;

    public Pair(T a, T1 b) {
        this.a = a;
        this.b = b;
    }

    public T getA() {
        return this.a;
    }

    public T1 getB() {
        return this.b;
    }

}
