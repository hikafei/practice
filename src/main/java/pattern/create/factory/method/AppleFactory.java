package pattern.create.factory.method;

import pattern.create.factory.Apple;
import pattern.create.factory.Fruit;

public class AppleFactory implements IFactory {
    @Override
    public Fruit factory() {
        return new Apple();
    }
}
