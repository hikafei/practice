package heratage;

public class Parent {
    private static InitialOrder staticField = new InitialOrder();
    private String field = "父对象域";

    static {
        System.out.println(staticField);
        System.out.println("父静态初始化块");
    }

    {
        System.out.println(field);
        System.out.println("父初始化块");
    }

    public Parent(){
        System.out.println("父构造函数");
    }
}
