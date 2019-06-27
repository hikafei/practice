package leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by liuyijun on 2019/6/27.
 */
public class ArrayHelperTest {
    private int[] arr = {4, 2, 1, 5, 3, 8, 6, 9, 7, 3};
    ArrayHelper helper = new ArrayHelper();
    @Test
    public void maxAndMin() throws Exception {
        int[] result = helper.maxAndMin(arr);
        System.out.println(result[0]+" : "+result[1]);
    }

}