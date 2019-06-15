package learn.sort;

/**
 * 计数排序的核心在于将输入的数据值转化为键存储在额外开辟的数组空间中。
 * 作为一种线性时间复杂度的排序，计数排序要求输入的数据必须是有确定范围的整数或字符。
 * 计数排序(Counting sort)是一种稳定的排序算法。
 * 计数排序使用一个额外的数组C，其中第i个元素是待排序数组A中值等于i的元素的个数。
 * 然后根据数组C来将A中的元素排到正确的位置。它只能对整数进行排序。
 * 不要使用TreeMap，treemap的get、put、containsKey时间复杂度都是log（n），导致整体时间复杂度nlog（n）
 */
public class CounterSortHelper implements ISortHelper{

    @Override
    public void work(int[] arr) {
        if(arr==null||arr.length<2)return;
        int length = arr.length;
        int max=arr[0];
        int min =arr[0];
        for (int value : arr) {
            max = Math.max(max, value);
            min = Math.min(min, value);
        }
        int nlength = max+1-min;
        int[] counter = new int[nlength];
        for (int value : arr) {
            counter[value - min]++;
        }
        int i=0,j=0;
        while(i<length){
            if(counter[j]>0){
                arr[i++] = j+min;
                counter[j]--;
            } else{
                j++;
            }
        }
        ArrayPrintHelper.work(arr);
    }
}
