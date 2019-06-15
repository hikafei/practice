package learn.search;

import org.junit.Test;

import static org.junit.Assert.*;

public class SearchHelperTest {
    private ISearchHelper helper;
    int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

    @Test
    public void sequenceSearch() {
        helper = new SequenceSearchHelper();
        helper.work(arr, 6);
    }

    @Test
    public void binarySearch() {
        helper = new BinarySeachHelper();
        helper.work(arr, 6);
    }

    @Test
    public void insertionSearch() {
        helper = new InsertionSearchHelper();
        helper.work(arr, 6);
    }
}