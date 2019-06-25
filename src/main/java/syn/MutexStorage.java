package syn;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class MutexStorage implements IStorage {
    private List<Object> list = new ArrayList<>();
    Semaphore notFull = new Semaphore(10);
    Semaphore notEmpty = new Semaphore(0);
    Semaphore mutex = new Semaphore(1);
    @Override
    public void produce() {
        try {
            notFull.acquire();
            mutex.acquire();
            list.add(new Object());
            System.out.println("【生产者" + Thread.currentThread().getName()
                    + "】生产一个产品，现库存" + list.size());
        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            mutex.release();
            notEmpty.release();
        }
    }

    @Override
    public void consume() {
        try {
            notEmpty.acquire();
            mutex.acquire();
            list.remove(0);
            System.out.println("【消费者" + Thread.currentThread().getName()
                    + "】消费一个产品，现库存" + list.size());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mutex.release();
            notFull.release();
        }
    }
}
