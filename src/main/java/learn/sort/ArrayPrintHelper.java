package learn.sort;

public class ArrayPrintHelper {
    public static void work(int[] arr){
        if(arr==null||arr.length==0) return;
        for (int value : arr) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}
