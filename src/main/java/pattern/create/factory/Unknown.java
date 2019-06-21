package pattern.create.factory;

public class Unknown implements Fruit {
    @Override
    public void grow() {
        System.out.println("unknown");
    }

    @Override
    public void harvest() {
        System.out.println("unknown");
    }

    @Override
    public void plant() {
        System.out.println("unknown");
    }
}
