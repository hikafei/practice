package pattern.create.factory.method;

import pattern.create.factory.Banana;
import pattern.create.factory.Fruit;

public class BananaFactory implements IFactory {
    @Override
    public Fruit factory() {
        return new Banana();
    }
}
