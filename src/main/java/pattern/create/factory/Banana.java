package pattern.create.factory;

public class Banana implements Fruit {
    @Override
    public void grow() {
        System.out.println("banana is growing");
    }

    @Override
    public void harvest() {
        System.out.println("banana is harvesting");
    }

    @Override
    public void plant() {
        System.out.println("banana is planting");
    }
}
