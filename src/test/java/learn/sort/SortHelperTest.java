package learn.sort;

import org.junit.Test;

import static org.junit.Assert.*;

public class SortHelperTest {
    private int[] arr = {4, 2, 1, 5, 3, 8, 6, 9, 7, 3};
    private ISortHelper helper;

    @Test
    public void bubbleSortTest() {
        helper = new BubbleSortHelper();
        helper.work(arr);
    }

    @Test
    public void selectionSortTest() {
        helper = new SelectionSortHelper();
        helper.work(arr);
    }

    @Test
    public void insertionSortTest() {
        helper = new InsertionSortHelper();
        helper.work(arr);
    }

    @Test
    public void mergeSortTest() {
        helper = new MergeSortHelper();
        helper.work(arr);
    }

    @Test
    public void quickSortTest() {
        helper = new QuickSortHelper();
        helper.work(arr);
    }

    @Test
    public void counterSortTest() {
        helper = new CounterSortHelper();
        helper.work(arr);
    }
}