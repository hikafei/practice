package bag;

import org.junit.Test;

import static org.junit.Assert.*;

public class BagHelperTest {

    int v = 10;
    int[] ws = {1, 2, 3, 4, 5};
    int[] vs = {2, 3, 4, 5, 6};

    BagHelper service = new BagHelper();

    @Test
    public void packZeroOneWithFull() {
        int result = service.packZeroOneWithFull(v, ws, vs);
        System.out.println("结果为" + result);
    }

    @Test
    public void packZeroOneWithoutFull() {
        int result = service.packZeroOneWithoutFull(v, ws, vs);
        System.out.println("结果为" + result);
    }

    @Test
    public void packZeroOneWithoutFullAndPrint() {
        int result = service.packZeroOneWithoutFullAndPrint(v, ws, vs);
        System.out.println("结果为" + result);
    }

    @Test
    public void packCompleteWithoutFullAndPrint() {
        int result = service.packCompleteWithoutFullAndPrint(v, ws, vs);
        System.out.println("结果为" + result);
    }

    @Test
    public void packMany() {
        int[] ns = {3, 1, 2, 2, 2};
        int result = service.packMany(v, ws, vs, ns);
        System.out.println("结果为" + result);
    }
}