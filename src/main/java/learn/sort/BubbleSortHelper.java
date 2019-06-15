package learn.sort;

/**
 * 比较相邻的元素。如果第一个比第二个大，就交换它们两个；
 * 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对，这样在最后的元素应该会是最大的数；
 * 针对所有的元素重复以上的步骤，除了最后一个；
 * 重复步骤1~3，直到排序完成。
 */
public class BubbleSortHelper implements ISortHelper{

    public void work(int[] arr){
        if(arr==null||arr.length<=1) return;
        int length = arr.length;
        int right = length-1; //优化:单次冒泡最后需要交换的下标 ,跟temp一起决定单次冒泡的结束下标
        for(int i =0;i<length;i++){
            boolean flag = true; //优化:判断此次循环是否没有发生交换，初始true
            int temp = 0; //优化
            for(int j=0;j<right;j++){
                if(arr[j]>arr[j+1]){
                    SwapHelper.work(arr,j,j+1);
                    flag = false;//优化
                    temp = j;//优化
                }
            }
            ArrayPrintHelper.work(arr);
            right = temp;//优化
            if(flag) break; //优化
        }
        ArrayPrintHelper.work(arr);
    }

    public void work2(int[] arr){
        if(arr==null||arr.length<=1) return;
        int length = arr.length;
        for(int i =0;i<length;i++){
            for(int j=0;j<length-1-i;j++){
                if(arr[j]>arr[j+1]){
                    SwapHelper.work(arr,j,j+1);
                }
            }
        }
        ArrayPrintHelper.work(arr);
    }
}
