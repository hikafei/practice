package syn;

import java.util.ArrayList;
import java.util.List;

public class CommonStorage implements IStorage {
    private final int MAX_SIZE = 50;
    private List<Object> list = new ArrayList<>();
    @Override
    public void produce() {
        synchronized (list){
            if(list.size()>=MAX_SIZE){
                System.out.println(String.format("仓库已满,[生产者%s]无法再创建产品",Thread.currentThread().getName()));
            }
            else {
                list.add(new Object());
                System.out.println(String.format("[生产者%s]生产一个产品,现存量%d",Thread.currentThread().getName(),list.size()));
            }
        }
    }

    @Override
    public void consume() {
        synchronized (list){
            if(list.size()==0){
                System.out.println(String.format("仓库中暂无产品，[消费者%s]无法消费",Thread.currentThread().getName()));
            } else {
                list.remove(0);
                System.out.println(String.format("[消费者%s]消费一个产品,现存量%d",Thread.currentThread().getName(),list.size()));
            }
        }
    }
}
