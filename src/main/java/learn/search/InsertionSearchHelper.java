package learn.search;

public class InsertionSearchHelper implements ISearchHelper {
    @Override
    public int work(int[] arr, int target) {
        if (arr == null || arr.length == 0) return -1;
        int length = arr.length;
        int left = 0, right = length - 1;
        int middle = -1;
        int count =0;
        while (left <= right) {
            count++;
            middle = left+ (target-arr[left])*(right-left)/(arr[right]-arr[left]);
            if (arr[middle] == target) break;
            else if (arr[middle] > target) right = middle - 1;
            else left = middle + 1;
        }
        SearchPrint.work(count,middle);
        return middle;
    }
}
