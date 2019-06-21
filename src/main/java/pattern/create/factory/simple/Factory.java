package pattern.create.factory.simple;

import pattern.create.factory.Apple;
import pattern.create.factory.Banana;
import pattern.create.factory.Fruit;
import pattern.create.factory.Unknown;

public class Factory {
    public static Fruit factory(String name){
        switch (name){
            case "apple":{
                return new Apple();
            }
            case "banana":{
                return new Banana();
            }
            default:{
                return new Unknown();
            }
        }
    }
}
