package learn.sort;

/**
 * 从第一个元素开始，该元素可以认为已经被排序；
 * 取出下一个元素，在已经排序的元素序列中从后向前扫描；
 * 如果该元素（已排序）大于新元素，将该元素移到下一位置；
 * 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置；
 * 将新元素插入到该位置后；
 * 重复
 */
public class InsertionSortHelper implements ISortHelper {
    @Override
    public void work(int[] arr) {
        if (arr == null || arr.length < 2) return;
        int length = arr.length;
        for (int i = 1; i < length; i++) {
            int temp = arr[i];
            for (int j = i - 1; j >= 0; j--) {
                if (temp > arr[j]) {
                    arr[j + 1] = temp;
                    break;
                } else {
                    arr[j + 1] = arr[j];
                    if (j == 0) {
                        arr[0] = temp;
                    }
                }
            }
            ArrayPrintHelper.work(arr);
        }
        ArrayPrintHelper.work(arr);
    }
}
