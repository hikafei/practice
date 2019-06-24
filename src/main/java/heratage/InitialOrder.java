package heratage;

public class InitialOrder {
    private static String staticField = "静态域";
    private String field = "对象域";

    static {
        System.out.println(staticField);
        System.out.println("静态初始化块");
    }

    {
        System.out.println(field);
        System.out.println("初始化块");
    }

    public InitialOrder(){
        System.out.println("构造函数");
    }
}
