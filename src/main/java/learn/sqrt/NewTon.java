package learn.sqrt;

/**
 * 牛顿法求sqrt（2）
 */
public class NewTon {
    public static void main(String[] args) {
        double b = 10;
        double v = 0.01;
        int count =0;
        while (b * b - 2 > v) {
            b = b - (b * b - 2) / (2 * b);
            count++;
        }
        System.out.println(b);
        System.out.println(count);
    }
}
