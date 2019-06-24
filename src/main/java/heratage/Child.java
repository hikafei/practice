package heratage;

public class Child extends Parent{
    private static String staticField = "子静态域";
    private String field = "子对象域";

    static {
        System.out.println(staticField);
        System.out.println("子静态初始化块");
    }

    {
        System.out.println(field);
        System.out.println("子初始化块");
    }

    public Child(){
        System.out.println("子构造函数");
    }

    public static void main(String[] args)
    {
        new Child();
    }
}
