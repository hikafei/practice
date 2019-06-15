package learn.sqrt;

/**
 * grant法求sqrt（2）
 */
public class Grant {
    public static void main(String[] args) {
        double b = 10;
        double v = 0.01;
        double lam = 0.0001;
        int count = 0;
        while (4 * b * b * b - 8 * b > v) {
            b = b - lam * (4 * b * b * b - 8 * b);
            System.out.println("temp: " + b);
            count++;
        }
        System.out.println(b);
        System.out.println(count);
    }
}
