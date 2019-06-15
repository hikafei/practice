package learn.search;

/**
 * 顺序查找
 */
public class SequenceSearchHelper implements ISearchHelper {
    @Override
    public int work(int[] arr, int target) {
        if(arr==null||arr.length==0) return -1;
        int length = arr.length;
        int count = 0;
        for(int i=0;i<length;i++){
            count++;
            if(arr[i]==target){
                SearchPrint.work(count,i);
                return i;
            }
        }
        return -1;
    }
}
