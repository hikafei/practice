package syn;

public class Producer implements Runnable {
    private IStorage storage;

    public Producer(IStorage storage){
        this.storage = storage;
    }
    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(500);
                storage.produce();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }

    }
}
