package syn;

public class Program {
    public static void main(String[] args) {
        IStorage storage = new MutexStorage();
//        for(int i =0;i<5;i++){
        new Thread(new Consumer(storage)).start();
        new Thread(new Producer(storage)).start();
//        }
    }
}
