package pattern.create.factory.simple;

import pattern.create.factory.Fruit;

/**
 * 简单工厂也叫静态工厂，使用工厂的公有静态方法生成继承自某接口的具体对象
 */
public class Program {

    public static void main(String[] args){
        Fruit a1 = Factory.factory("apple");
        a1.plant();
        a1.grow();
        a1.harvest();
        System.out.println();
        a1 = Factory.factory("orange");
        a1.plant();
        a1.grow();
        a1.harvest();
    }
}
