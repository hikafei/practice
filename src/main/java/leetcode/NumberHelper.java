package leetcode;

/**
 * 数字leetcode练习
 */
public class NumberHelper {

    /**
     * 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方
     * 利用二进制运算，&1和>> ,将时间复杂度由o(n)变为o(log(n))
     */
    public double power(double base, int exponent) throws Exception {
        if (base == 0.0 && exponent < 0) throw new Exception("分母不能为0");
        if (exponent == 0) return 1;
        boolean fushuFlag = exponent < 0;
        if (fushuFlag) exponent = -exponent;
        double result = 1.0;
        while (exponent > 0) {
            result = (exponent & 1) == 1 ? result * base : result;
            base = base * base;
            exponent >>= 1;
        }
        return fushuFlag ? 1 / result : result;
    }

    /**
     * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
     */
    public int reverse(int x) {
        boolean flag = x < 0;
        if (flag) x = -x;
        int result = 0;
        while (x > 0) {
            int a = x % 10;
            x = x / 10;
            try {
                result = Math.addExact(a, Math.multiplyExact(result, 10));
            } catch (Exception e) {
                return 0;
            }
        }
        return flag ? -result : result;
    }

    /**
     * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
     * 问总共有多少条不同的路径？
     * 输入: m = 3, n = 2
     * 输出: 3
     * 解释:
     * 从左上角开始，总共有 3 条路径可以到达右下角。
     * 1. 向右 -> 向右 -> 向下
     * 2. 向右 -> 向下 -> 向右
     * 3. 向下 -> 向右 -> 向右
     * 输入: m = 7, n = 3
     * 输出: 28
     */
    public int uniquePaths(int m, int n) {
        if (m <= 0 || n <= 0) return 0;
        if (m == 1 && n == 1) return 1;
        int max = Math.max(m, n);
        int min = Math.min(m, n);
        int[] arr = new int[min];
        for (int i = 0; i < min; i++) {
            arr[i] = 1;
        }
        for (int i = 1; i < max; i++) {
            for (int j = 1; j < min; j++) {
                arr[j] = arr[j] + arr[j - 1];
            }
        }
        return arr[min - 1];
//        return uniquePaths(m-1,n)+uniquePaths(m,n-1);
    }

    /**
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     */
    public int climbStairs(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;
        int a = 1;
        int b = 2;
        int temp = 0;
        int index = 2;
        while (index < n) {
            temp = b;
            b = a + b;
            a = temp;
            index++;
        }
        return b;
    }

    /**
     * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
     */
    public int numSquares(int n) {
        if (n < 0) return Integer.MAX_VALUE;
        if (n == 0 || n == 1) return n;
        int temp = (int) Math.sqrt(n);
        int[] rs = new int[n + 1];
        rs[0] = 0;
        for (int i = 1; i <= n; i++) {
            rs[i] = Integer.MAX_VALUE;
        }
        for (int i = 1; i < temp; i++) {
            for (int j = i * i; j <= n; j++) {
                rs[j] = Math.min(rs[j], rs[j - i * i] + 1);
            }
        }
        return rs[n];
    }
}
