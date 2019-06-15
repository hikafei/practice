package learn.sort;

/**
 * 从数列中挑出一个元素，称为 “基准”（pivot）；
 * 重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。
 * 在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；
 * 递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序。
 */
public class QuickSortHelper implements ISortHelper {
    @Override
    public void work(int[] arr) {
        if (arr == null || arr.length < 2) return;
        sort(arr, 0, arr.length - 1);
        ArrayPrintHelper.work(arr);
    }

    private void sort(int[] arr, int left, int right) {
        int point = splitAndLocation(arr, left, right);
        if (point > left && valid(arr, left, point))
            sort(arr, left, point - 1);
        if (point < right && valid(arr, point, right))
            sort(arr, point + 1, right);
    }

    private boolean valid(int[] arr, int a, int b) {
        int index = a;
        while (index < b) {
            if (arr[index] > arr[index + 1]) {
                return true;
            }
            index++;
        }
        return false;
    }

    private int splitAndLocation(int[] arr, int left, int right) {
        int temp = arr[right];
        int i = left, j = right - 1;// 指针
        while (i < j) {
            while (arr[i] < temp && i < j) {
                i++;
            }
            while (arr[j] > temp && i < j) {
                j--;
            }
            SwapHelper.work(arr, i, j);
        }
        if (j < 0 || arr[j] < temp)
            j++;
        for (int k = right - 1; k >= j; k--) {
            arr[k + 1] = arr[k];
        }
        arr[j] = temp;
        System.out.print("value=" + temp + "; index=" + j + "; left=" + left + "; right=" + right + " : ");
        ArrayPrintHelper.work(arr);
        return j;
    }
}
