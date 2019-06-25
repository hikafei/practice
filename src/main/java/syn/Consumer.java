package syn;

public class Consumer implements Runnable {
    private IStorage storage;

    public Consumer(IStorage storage){
        this.storage = storage;
    }
    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(500);
                storage.consume();
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
