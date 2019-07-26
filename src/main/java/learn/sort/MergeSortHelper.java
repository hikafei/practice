package learn.sort;

import java.util.Arrays;

/**
 * 采用分治法
 * 归并排序是一种稳定的排序方法。将已有序的子序列合并，得到完全有序的序列；
 * 即先使每个子序列有序，再使子序列段间有序。
 * 若将两个有序表合并成一个有序表，称为2-路归并。
 */
public class MergeSortHelper implements ISortHelper {
    @Override
    public void work(int[] arr) {
        if (arr == null || arr.length < 2) return;
        arr = sort(arr);
        ArrayPrintHelper.work(arr);
    }

    private int[] sort(int[] arr) {
        int length = arr.length;
        if (length == 1) return arr;
        int middle = length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, middle);
        int[] right = Arrays.copyOfRange(arr, middle, length);
        arr = merge(sort(left), sort(right));
        return arr;
    }

    private int[] merge(int[] left, int[] right) {
        // 上下文决定left 与 right 均不为空
        int length = left.length + right.length;
        int[] result = new int[length];
        int index = 0, lp = 0, rp = 0;
        while (index < length) {
            if (lp >= left.length) {
                result[index++] = right[rp++];
            } else if (rp >= right.length) {
                result[index++] = left[lp++];
            } else if (left[lp] < right[rp]) {
                result[index++] = left[lp++];
            } else {
                result[index++] = right[rp++];
            }
        }
        ArrayPrintHelper.work(result);
        return result;
    }

}
