package pattern.create.factory;

public class Apple implements Fruit {
    private int treeAge;

    public int getTreeAge() {
        return treeAge;
    }

    public void setTreeAge(int treeAge) {
        this.treeAge = treeAge;
    }

    @Override
    public void grow() {
        System.out.println("apple is growing");
    }

    @Override
    public void harvest() {
        System.out.println("apple is harvest");
    }

    @Override
    public void plant() {
        System.out.println("apple is planting");
    }
}
