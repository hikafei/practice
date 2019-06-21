package pattern.singleton;

/**
 * 懒汉、饿汉、内部静态类、同步
 */
public class Singleton {
    private Singleton(){}

    public static Singleton getInstance(){
        return Helper.instance;
    }

    private static class Helper{
        private static Singleton instance = new Singleton();
        static {
            System.out.println("init instance");
        }
    }

    public void work(){
        System.out.println("hello jun");
    }
}
