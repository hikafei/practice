package learn.sort;

/**
 * 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，
 * 然后，再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
 * 以此类推，直到所有元素均排序完毕。
 */
public class SelectionSortHelper implements ISortHelper{

    /**
     * 优化考虑 每次遍历得到最大最小值，只需遍历n/2次
     */
    public void work(int[] arr){
        if(arr==null||arr.length<2) return;
        int length = arr.length;
        for(int i=0;i<length;i++){
            int index = i;
            for(int j=i+1;j<length;j++){
                if(arr[index]>arr[j]){
                    index = j;
                }
            }
            SwapHelper.work(arr,i,index);
            ArrayPrintHelper.work(arr);
        }
        ArrayPrintHelper.work(arr);
    }
}
