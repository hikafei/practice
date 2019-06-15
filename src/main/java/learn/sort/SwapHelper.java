package learn.sort;

public class SwapHelper {
    public static void work(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
