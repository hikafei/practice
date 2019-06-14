package leetcode;

import java.util.Arrays;
import java.util.Comparator;

public class BiArrayHelper {
    /**
     * 给定一个 n × n 的二维矩阵表示一个图像。
     * 将图像顺时针旋转 90 度。
     * 说明：
     * 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
     * 示例 1:
     * 给定 matrix =
     * [
     * [1,2,3],
     * [4,5,6],
     * [7,8,9]
     * ],
     * 原地旋转输入矩阵，使其变为:
     * [
     * [7,4,1],
     * [8,5,2],
     * [9,6,3]
     * ]
     */
    public void rotate(int[][] matrix) {
        if (matrix.length == 1) return;
        int length = matrix.length;
        for (int i = 0; i < length / 2; i++) {
            for (int j = i; j <= length - 2 - i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[length - 1 - j][i];
                matrix[length - 1 - j][i] = matrix[length - 1 - i][length - 1 - j];
                matrix[length - 1 - i][length - 1 - j] = matrix[j][length - 1 - i];
                matrix[j][length - 1 - i] = temp;
            }
        }
    }

    /**
     * 给出一个区间的集合，请合并所有重叠的区间。
     * 示例 1:
     * 输入: [[1,3],[2,6],[8,10],[15,18]]
     * 输出: [[1,6],[8,10],[15,18]]
     * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
     * 示例 2:
     * 输入: [[1,4],[4,5]]
     * 输出: [[1,5]]
     * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
     */
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return new int[0][];
        if (intervals.length == 1) return intervals;
        Arrays.sort(intervals, Comparator.comparingInt(a -> ((int[]) a)[0]));
        int length = intervals.length;
        boolean[] flags = new boolean[length];
        flags[0] = true;
        int position = 0;
        for (int i = 1; i < length; i++) {
            flags[i] = true;
            if (intervals[i][0] <= intervals[position][1]) {
                intervals[position][1] = Math.max(intervals[i][1], intervals[position][1]);
                flags[i] = false;
            } else {
                position = i;
            }
        }
        int count = 0;
        for (int i = 0; i < flags.length; i++) {
            if (flags[i])
                count++;
        }
        int[][] result = new int[count][];
        int j = 0;
        for (int i = 0; i < length; i++) {
            if (flags[i]) {
                result[j++] = new int[]{intervals[i][0], intervals[i][1]};
            }
        }
        return result;
    }

    /**
     * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
     * 说明：每次只能向下或者向右移动一步。
     * 示例:
     * 输入:
     * [
     * [1,3,1],
     * [1,5,1],
     * [4,2,1]
     * ]
     * 输出: 7
     * 解释: 因为路径 1→3→1→1→1 的总和最小。
     */
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0) return Integer.MIN_VALUE;
        int m = grid.length;
        int n = grid[0].length;
        int[][] mins = new int[m][n];
        mins[0][0] = grid[0][0];
        for (int i = 1; i < n; i++) {
            mins[0][i] = grid[0][i] + mins[0][i - 1];
        }
        for (int i = 1; i < m; i++) {
            mins[i][0] = grid[i][0] + mins[i - 1][0];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                mins[i][j] = Math.min(mins[i - 1][j], mins[i][j - 1]) + grid[i][j];
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(mins[i][j] + " ");
            }
            System.out.println();
        }
        return mins[m - 1][n - 1];
    }

    /**
     * 给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。你可以假设网格的四个边均被水包围。
     * 思路错误！！！
     */
    public int numIslands(char[][] grid) {
        if (grid == null) return 0;
        int row = grid.length;
        int col = grid[0].length;
        for (int j = col - 1; j >= 0; j--) {
            for (int i = 0; i < row && i <= j; i++) {
                if (grid[i][j - i] == '1') {
                    if ((i > 0 && grid[i - 1][j - i] == '1') || (j - i > 0 && grid[i][j - i - 1] == '1')) {
                        grid[i][j - i] = '0';
                    }
                }
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
        for (int i = col - 1; i < row + col - 1; i++) {
            for (int j = col - 1; j > i - row && j >= 0; j--) {
                if (grid[i - j][j] == '1') {
                    if ((i - j < row - 1 && grid[i - j + 1][j] == '1') || (j < col - 1 && grid[i - j][j + 1] == '1')) {
                        grid[i - j][j] = '0';
                    }
                }
            }
        }
        System.out.println();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
        int result = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    result++;
                }
            }
        }
        return result;
    }

    /**
     * 在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。
     */
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int a = matrix.length;
        int b = matrix[0].length;
        int[][] rs = new int[a][b];
        int max = 0;
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                if (matrix[i][j] == '1') {
                    rs[i][j] = 1;
                    max = 1;
                } else {
                    rs[i][j] = 0;
                }
            }
        }
        for (int i = 1; i < a; i++) {
            for (int j = 1; j < b; j++) {
                rs[i][j] = rs[i][j] == 1 ? (1 + Math.min(rs[i - 1][j - 1], Math.min(rs[i - 1][j], rs[i][j - 1]))) : 0;
                max = Math.max(max, rs[i][j]);
            }
        }
        return max * max;
    }
}
