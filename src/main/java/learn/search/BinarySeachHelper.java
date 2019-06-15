package learn.search;

/**
 * 二分查找，arr必须已经排序
 */
public class BinarySeachHelper implements ISearchHelper {
    @Override
    public int work(int[] arr, int target) {
        if (arr == null || arr.length == 0) return -1;
        int length = arr.length;
        int left = 0, right = length - 1;
        int middle = -1;
        int count = 0;
        while (left <= right) {
            count++;
            middle = (left + right) / 2;
            if (arr[middle] == target) break;
            else if (arr[middle] > target) right = middle - 1;
            else left = middle + 1;
        }
        SearchPrint.work(count,middle);
        return middle;
    }
}
