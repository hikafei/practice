package pattern.create.factory.method;

import pattern.create.factory.Fruit;

/**
 * 提供一个工厂接口，参与者包括抽象工厂类、具体工厂类、抽象产品、具体产品
 */
public class Program {
    public static void main(String[] args) {
        IFactory factory = new AppleFactory();
        Fruit fruit = factory.factory();
        fruit.plant();
        fruit.grow();
        fruit.harvest();
    }
}
