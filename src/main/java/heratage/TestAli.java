package heratage;

public class TestAli {
    public static int k = 0;
    public static TestAli t1 = new TestAli("t1");
    public static TestAli t2 = new TestAli("t2");
    public static int i = print("i");
    public static int n = 99;
    public int j = print("j");
    {
        print("构造块");
    }

    static{
        print("静态块");
    }

    public TestAli(String str){
        System.out.println((++k)+":"+str+"    i="+i+"    n="+n);
        ++i;++n;
    }

    public static int print(String str){
        System.out.println((++k)+":"+str+"    i="+i+"    n="+n);
        ++n;
        return ++i;
    }

    public static void main(String[] args) {
        TestAli t = new TestAli("init");
    }
}
