package syn;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockStorage implements IStorage {
    private final int MAX_SIZE = 50;
    private List<Object> list = new ArrayList<>();
    private Lock lock = new ReentrantLock();
    private Condition full = lock.newCondition();
    private Condition empty = lock.newCondition();


    @Override
    public void produce() {
        lock.lock();
        while (list.size() >= MAX_SIZE) {
            System.out.println(String.format("仓库已满,[生产者%s]无法再创建产品", Thread.currentThread().getName()));
            try {
                full.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        list.add(new Object());
        System.out.println(String.format("[生产者%s]生产一个产品,现存量%d", Thread.currentThread().getName(), list.size()));
        empty.signalAll();
        lock.unlock();
    }

    @Override
    public void consume() {
        lock.lock();
        while (list.size() == 0) {
            System.out.println(String.format("仓库中暂无产品，[消费者%s]无法消费", Thread.currentThread().getName()));
            try {
                empty.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        list.remove(0);
        System.out.println(String.format("[消费者%s]消费一个产品,现存量%d", Thread.currentThread().getName(), list.size()));
        full.signalAll();
        lock.unlock();
    }
}
