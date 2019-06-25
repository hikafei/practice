package syn;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class BlockQueueStorage implements IStorage {
    private final int MAX_SIZE =50;
    private BlockingQueue<Object> list=  new LinkedBlockingDeque<>(MAX_SIZE);
    @Override
    public void produce() {
        try {
            list.put(new Object());
            System.out.println(String.format("[生产者%s]生产一个产品,现存量%d",Thread.currentThread().getName(),list.size()));
        } catch (InterruptedException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    @Override
    public void consume() {
        try {
            list.take();
            System.out.println(String.format("[消费者%s]消费一个产品,现存量%d",Thread.currentThread().getName(),list.size()));
        }catch (InterruptedException e){
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
