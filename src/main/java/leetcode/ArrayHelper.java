package leetcode;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * leetcode 数组练习
 */
public class ArrayHelper {

    /**
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
     * <p>
     * 思路think:
     * 1 使用Map记录数组中每一项的下标
     * 2 遍历nums，利用Map的keys是否包含target-nums[i],包含的话 遍历的当前下标i与map对应项值j即为结果，和为target
     * <p>
     * 复杂度cal:
     * 时O(n),空O(n)
     */
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return new int[]{};
        }
        // 初始化map
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        // 遍历nums，检查和为target
        for (int i = 0; i < nums.length; i++) {
            int x = target - nums[i];
            if (map.containsKey(x)) {
                return new int[]{i, map.get(x)};
            }
        }
        return new int[]{};
    }

    /**
     * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
     * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
     * 你可以假设 nums1 和 nums2 不会同时为空。
     * <p>
     * think:
     * 构造有序新数组array，包含nums1和nums2。然后根据length求中位数
     * <p>
     * cal
     * O(m+n) 不满足要求
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1 == null) nums1 = new int[]{};
        if (nums2 == null) nums2 = new int[]{};
        int length1 = nums1.length;
        int length2 = nums2.length;
        int length = length1 + length2;
        int[] array = new int[length];
        int i = 0, j = 0;
        int index = 0;
        while (i < length1 || j < length2) {
            int a = Integer.MAX_VALUE, b = Integer.MAX_VALUE;
            if (i < length1) {
                a = nums1[i];
            }
            if (j < length2) {
                b = nums2[j];
            }
            if (a <= b) {
                array[index++] = a;
                i++;
            } else {
                array[index++] = b;
                j++;
            }
        }
        if (length % 2 == 1) {
            return array[(length - 1) / 2];
        } else {
            return ((double) (array[length / 2] + array[length / 2 - 1])) / 2;
        }
    }

    /**
     * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
     * <p>
     * think:
     * 前偶后奇数就交换 O(n2)
     * other:
     * 定义两个list，遍历array，第一个list保存奇数，第二个list保存偶数，遍历完之后，根据两个list回写array。 o(n)
     */
    public void reOrderArray(int[] array) {
        int temp = 0;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                // 前偶后奇数就交换
                if ((array[j] & 1) == 0 && (array[j + 1] & 1) == 1) {  //与1位与得1就是奇数，1只有最后一位是1
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
     * 注意：答案中不可以包含重复的三元组。
     * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
     * 满足要求的三元组集合为：
     * [
     * [-1, 0, 1],
     * [-1, -1, 2]
     * ]
     */
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3) {
            return new ArrayList<>();
        }
        Map<Integer, Integer> mapOne = new HashMap<>();
        Map<Integer, List<Integer>> mapOther = new HashMap<>();
        List<Integer> numberList = IntStream.of(nums).boxed().collect(Collectors.toList());
        for (int i = 0; i < nums.length; i++) {
            if (!mapOne.containsKey(0 - nums[i])) {
                mapOne.put(-nums[i], nums[i]);
                List<Integer> temp = numberList.stream().collect(Collectors.toList());
                temp.remove(numberList.indexOf(nums[i]));
                mapOther.put(-nums[i], temp);
            }
        }
        List<List<Integer>> result = new ArrayList<>();
        for (Map.Entry<Integer, List<Integer>> entry : mapOther.entrySet()) {
            Map<Integer, Integer> map = new HashMap<>();
            for (Integer i : entry.getValue()) {
                if (map.containsKey(i)) {
                    List<Integer> record = Arrays.asList(mapOne.get(entry.getKey()), map.get(i), i);
                    result.add(record);
                } else {
                    map.put(entry.getKey() - i, i);
                }
            }
        }
        List<List<Integer>> fis = new ArrayList<>();
        for (List<Integer> l : result) {
            if (!exist(fis, l)) {
                fis.add(l);
            }
        }
        return fis;
    }

    private boolean exist(List<List<Integer>> ll, List<Integer> l) {
        l.sort(Comparator.comparingInt(a -> a));
        for (List<Integer> list : ll) {
            if (list.get(0) == l.get(0) && list.get(1) == l.get(1)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
     * <p>
     * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
     * <p>
     * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
     * <p>
     * 你可以假设数组中不存在重复的元素。
     * <p>
     * 你的算法时间复杂度必须是 O(log n) 级别。
     * <p>
     * 示例 1:
     * <p>
     * 输入: nums = [4,5,6,7,0,1,2], target = 0
     * 输出: 4
     * 示例 2:
     * <p>
     * 输入: nums = [4,5,6,7,0,1,2], target = 3
     * 输出: -1
     */
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        if (nums.length == 1) return nums[0] == target ? 0 : -1;
        int position = findPosition(nums, 0, nums.length - 1);
        if (position == 0) position = nums.length - 1;
        if (target >= nums[0]) {
            return findIndex(nums, 0, position, target);
        } else {
            return findIndex(nums, position, nums.length - 1, target);
        }
    }

    private int findIndex(int[] nums, int start, int end, int target) {
        while (start <= end) {
            int m = (start + end) / 2;
            if (nums[m] == target) {
                return m;
            } else {
                if (nums[m] < target) {
                    start = m;
                } else {
                    end = m;
                }
            }
        }
        return -1;
    }

    private int findPosition(int[] nums, int start, int end) {
        if (nums[start] < nums[end]) {
            return 0;
        }
        while (start <= end) {
            int m = (start + end) / 2;
            if (nums[m] > nums[m + 1])
                return m + 1;
            else if (nums[m] > nums[start]) {
                start = m;
            } else {
                end = m;
            }
        }
        return 0;
    }

    /**
     * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
     * <p>
     * 你的算法时间复杂度必须是 O(log n) 级别。
     * <p>
     * 如果数组中不存在目标值，返回 [-1, -1]。
     * <p>
     * 示例 1:
     * <p>
     * 输入: nums = [5,7,7,8,8,10], target = 8
     * 输出: [3,4]
     * 示例 2:
     * <p>
     * 输入: nums = [5,7,7,8,8,10], target = 6
     * 输出: [-1,-1]
     */
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        int length = nums.length;
        int start = positionStart(nums, 0, length - 1, target);
        int end = positionEnd(nums, 0, length - 1, target);
        return new int[]{start, end};
    }

    private int positionStart(int[] nums, int start, int end, int target) {
        while (start <= end) {
            int m = (start + end) / 2;
            if (nums[m] > target) {
                end = m - 1;
            } else if (nums[m] < target) {
                start = m + 1;
            } else if (m == 0) {
                return 0;
            } else if (nums[m - 1] == target) {
                end = m - 1;
            } else {
                return m;
            }
        }
        return -1;
    }

    private int positionEnd(int[] nums, int start, int end, int target) {
        while (start <= end) {
            int m = (start + end) / 2;
            if (nums[m] > target) {
                end = m - 1;
            } else if (nums[m] < target) {
                start = m + 1;
            } else if (m == nums.length - 1) {
                return nums.length - 1;
            } else if (nums[m + 1] == target) {
                start = m + 1;
            } else {
                return m;
            }
        }
        return -1;
    }

    /**
     * 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
     * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     * 说明：你不能倾斜容器，且 n 的值至少为 2。
     * 图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
     */
    public int maxArea(int[] height) {
        int length = height.length;
        int head = 0;
        int tail = length - 1;
        int result = 0;
        boolean flag = true;
        while (head < tail) {
            result = Math.max(result, (tail - head) * Math.min(height[head], height[tail]));
            if (height[head] < height[tail]) {
                head++;
            } else {
                tail--;
            }
        }
        return result;

//        int result = 0;
//        int length = height.length;
//        for(int i=0;i<length-1;i++){
//            for(int j=i+1;j<length;j++){
//                int value = (j-i)*Math.min(height[i],height[j]);
//                result = value>=result?value:result;
//            }
//        }
//        return result;
    }

    /**
     * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
     * 示例:
     * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
     * 输出: 6
     */
    public int trap(int[] height) {
        if (height == null || height.length < 3) {
            return 0;
        }
        int length = height.length;
        int maxRight = 0;
        int[] maxRs = new int[length];
        for (int i = length - 1; i >= 0; i--) {
            maxRight = Math.max(maxRight, height[i]);
            maxRs[i] = maxRight;
        }
        int maxLeft = 0;
        int sum = 0;
        for (int i = 0; i < length; i++) {
            maxLeft = Math.max(maxLeft, height[i]);
            int left = maxLeft;
            sum += Math.max(0, Math.min(left, maxRs[i]) - height[i]);
        }
        return sum;
    }

    /**
     * 给定一个没有重复数字的序列，返回其所有可能的全排列。
     * 输入: [1,2,3]
     * 输出:
     * [
     * [1,2,3],
     * [1,3,2],
     * [2,1,3],
     * [2,3,1],
     * [3,1,2],
     * [3,2,1]
     * ]
     */
    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) return new ArrayList<>();
        List<Integer> ns = IntStream.of(nums).boxed().collect(Collectors.toList());
        List<List<Integer>> result = permuteList(ns);
        return result;
    }

    private List<List<Integer>> permuteList(List<Integer> nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.size() == 1) {
            List<Integer> record = Arrays.asList(nums.get(0));
            result.add(record);
            return result;
        }
        for (Integer i : nums) {
            List<Integer> sub = new ArrayList<>();
            sub.addAll(nums);
            sub.remove(i);
            List<List<Integer>> subResult = permuteList(sub);
            for (List<Integer> list : subResult) {
                List<Integer> temp = new ArrayList<>();
                temp.add(i);
                temp.addAll(list);
                result.add(temp);
            }
        }
        return result;
    }

    /**
     * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     * candidates 中的数字可以无限制重复被选取。
     * 说明：
     * 所有数字（包括 target）都是正整数。
     * 解集不能包含重复的组合。
     * 示例 1:
     * 输入: candidates = [2,3,6,7], target = 7,
     * 所求解集为:
     * [
     * [7],
     * [2,2,3]
     * ]
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        if (candidates != null || candidates.length > 0) {
            combinationSumWork(candidates, 0, target, new ArrayList<Integer>(), 0);
        }
        return combinationSumList;
    }

    private List<List<Integer>> combinationSumList = new ArrayList<>();

    private void combinationSumWork(int[] numbers, int sum, int target, List<Integer> list, int index) {
        if (sum > target) {
            return;
        }
        if (sum == target) {
            combinationSumList.add(list);
            return;
        }
        for (int i = numbers.length - 1; i >= index; i--) {
            List<Integer> temp = new ArrayList<>(list);
            temp.add(numbers[i]);
            combinationSumWork(numbers, sum + numbers[i], target, temp, i);
        }
    }

    /**
     * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * 示例:
     * 输入: [-2,1,-3,4,-1,2,1,-5,4],
     * 输出: 6
     * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
     * 进阶:
     * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
     */
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Integer.MIN_VALUE;
        }
        int length = nums.length;
        int[] sum = new int[length];

        List<List<Integer>> list = new ArrayList<>();
        List<Integer> maxList = new ArrayList<>();

        sum[0] = nums[0];
        int max = sum[0];

        List<Integer> list0 = Arrays.asList(nums[0]);
        list.add(list0);
        maxList = list0;
        for (int i = 1; i < length; i++) {
            if (sum[i - 1] + nums[i] > nums[i]) {
                sum[i] = sum[i - 1] + nums[i];
                List<Integer> record = new ArrayList<>(list.get(i - 1));
                record.add(nums[i]);
                list.add(record);
            } else {
                sum[i] = nums[i];
                List<Integer> record = Arrays.asList(nums[i]);
                list.add(record);
            }
            if (max <= sum[i]) {
                max = sum[i];
                maxList = list.get(i);
            }
        }
        System.out.print(maxList.stream().map(String::valueOf).collect(Collectors.joining(",")));
        return max;
    }

    /**
     * 给定一个非负整数数组，你最初位于数组的第一个位置。
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     * 判断你是否能够到达最后一个位置。
     * 示例 1:
     * 输入: [2,3,1,1,4]
     * 输出: true
     * 解释: 从位置 0 到 1 跳 1 步, 然后跳 3 步到达最后一个位置。
     * 示例 2:
     * 输入: [3,2,1,0,4]
     * 输出: false
     * 解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
     */
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) return false;
//        return jump(nums,0);
        int length = nums.length;
//        int[] ms = IntStream.iterate(0, i -> i + 1).limit(length).toArray();
        int[] ms = new int[length];
        for (int i = 0; i < length; i++) {
            ms[i] = i;
        }
        int index = length - 2;
        while (index >= 0) {
            if (nums[index] > 0) {
                int end = Math.min(ms[index] + nums[index], length - 1);
                for (int i = index; i <= end; i++) {
                    ms[index] = Math.max(ms[index], ms[i]);
                }
            }
            index--;
        }
        return ms[0] == length - 1;
    }

    private boolean jump(int[] nums, int index) {
        int end = index + nums[index];
        if (end >= nums.length - 1)
            return true;
        int i = index + 1;
        while (i <= end) {
            boolean temp = jump(nums, i);
            if (temp) {
                return true;
            }
            i++;
        }
        return false;
    }

    /**
     * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
     */
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int length = nums.length;
        if (length == 1) return 1;
        List<Integer> list = new ArrayList<>();
        int result = 1;
        list.add(nums[0]);
        for (int i = 1; i < length; i++) {
            if (nums[i] > list.get(list.size() - 1)) {
                list.add(nums[i]);
                result++;
            } else {
                int index = 0;
                for (int j = list.size() - 2; j >= 0; j--) {
                    if (list.get(j) < nums[i]) {
                        index = j + 1;
                        break;
                    }
                }
                list.set(index, nums[i]);
            }
        }
        return result;
    }

    /**
     * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
     * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
     */
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) return 0;
        int result = 0;
        int length = heights.length;
        int a, b;
        for (int i = 0; i < length; i++) {
            a = i;
            b = i;
            while (a > 0) {
                if (heights[a - 1] >= heights[i]) {
                    a--;
                } else {
                    break;
                }
            }
            while (b < length - 1) {
                if (heights[b + 1] >= heights[i]) {
                    b++;
                } else {
                    break;
                }
            }
            result = Math.max(result, heights[i] * (b - a + 1));
        }
        return result;
    }

    /**
     * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
     * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
     * 注意:
     * 不能使用代码库中的排序函数来解决这道题。
     * 示例:
     * 输入: [2,0,2,1,1,0]
     * 输出: [0,0,1,1,2,2]
     * 进阶：
     * 一个直观的解决方案是使用计数排序的两趟扫描算法。
     * 首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
     * 你能想出一个仅使用常数空间的一趟扫描算法吗？
     */
    public void sortColors(int[] nums) {
        int cur = 0;
        int p0 = 0;
        int p2 = nums.length - 1;
        int temp = 0;
        while (cur <= p2) {
            if (nums[cur] == 0) {
                temp = nums[p0];
                nums[p0++] = nums[cur];
                nums[cur++] = temp;
            } else if (nums[cur] == 2) {
                temp = nums[cur];
                nums[cur] = nums[p2];
                nums[p2--] = temp;
            } else cur++;
        }
    }

    /**
     * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
     * 说明：解集不能包含重复的子集。
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<Integer> empty = Collections.emptyList();
        List<List<Integer>> result = new ArrayList<>();
        result.add(empty);
        if (nums == null || nums.length == 0) return result;
        for (int i = 0; i < nums.length; i++) {
            List<List<Integer>> tempList = new ArrayList<>();
            for (List<Integer> record : result) {
                if (record.size() == 0) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(nums[i]);
                    tempList.add(temp);
                } else {
                    List<Integer> temp = new ArrayList<>(record);
                    temp.add(nums[i]);
                    tempList.add(temp);
                }
            }
            result.addAll(tempList);
        }
        return result;
    }

    /**
     * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
     * 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
     * 注意你不能在买入股票前卖出股票。
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;
        int result = 0;
        int length = prices.length;
        int[] arr = new int[length];
        int max = prices[length - 1];
        for (int i = length - 1; i >= 0; i--) {
            max = arr[i] = Math.max(prices[i], max);
        }
        for (int i = 0; i < length; i++) {
            result = Math.max(result, arr[i] - prices[i]);
        }
        return result;
    }

    /**
     * 给定一个未排序的整数数组，找出最长连续序列的长度。
     * 要求算法的时间复杂度为 O(n)。
     * 示例:
     * 输入: [100, 4, 200, 1, 3, 2]
     * 输出: 4
     * 解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
     */
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return 1;
        int length = nums.length;
        Set<Integer> set = new TreeSet<>();
        for (int i = 0; i < length; i++) {
            set.add(nums[i]);
        }
        int result = 1;
        Iterator<Integer> it = set.iterator();
        int current = it.next();
        int temp = 1;
        while (it.hasNext()) {
            int num = it.next();
            if (num - current == 1) {
                temp++;
            } else {
                result = Math.max(result, temp);
                temp = 1;
            }
            current = num;
        }
        result = Math.max(result, temp);
        return result;
//        int max = nums[0];int min =nums[0];
//        for(int i=1;i<length;i++){
//            max = Math.max(max,nums[i]);
//            min = Math.min(min,nums[i]);
//        }
//        int nl = max-min+1;
//        int[] arr = new int[nl];
//        for(int i=0;i<length;i++){
//            arr[nums[i]-min] = 1;
//        }
//        int result =0;
//        int temp = 0;
//        for(int i=0;i<nl;i++){
//            if(arr[i]==1){
//                temp++;
//            } else {
//                result = Math.max(result,temp);
//                temp = 0;
//            }
//        }
//        return result;
    }

    /**
     * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     */
    public int singleNumber(int[] nums) {
        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            result ^= nums[i];
        }
        return result;
    }

    /**
     * 给定一个大小为 n 的数组，找到其中的众数。众数是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
     * 你可以假设数组是非空的，并且给定的数组总是存在众数
     */
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int length = nums.length;
        if (length == 1) return nums[0];
        int compare = length / 2;
        for (int i = 0; i < length; i++) {
            if (map.containsKey(nums[i])) {
                int newValue = map.get(nums[i]) + 1;
                if (newValue > compare) return nums[i];
                map.replace(nums[i], newValue);
            } else {
                map.put(nums[i], 1);
            }
        }
        return 0;
    }

    /**
     * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
     */
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int length = nums.length;
        int clude = nums[0];
        int nonclude = 0;
        int temp = 0;
        for (int i = 1; i < length; i++) {
            temp = Math.max(clude, nonclude);
            clude = nonclude + nums[i];
            nonclude = temp;
        }
        return Math.max(clude, nonclude);
    }

    /**
     * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
     */
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    /**
     * 给定长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
     * 进阶：
     * 你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
     */
    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) return new int[0];
        if (nums.length == 1) return nums;

        int length = nums.length;
        int[] result = new int[length];
        result[0] = 1;
        for (int i = 1; i < length; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }
        int temp = 1;
        for (int i = length - 1; i >= 0; i--) {
            result[i] = result[i] * temp;
            temp = temp * nums[i];
        }
        return result;
    }

    /**
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     */
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length < 2) return;
        int j = 0, length = nums.length;
        for (int i = 0; i < length; i++) {
            if (nums[i] != 0) {
                nums[j++] = nums[i];
            }
        }
        while (j < length) {
            nums[j++] = 0;
        }
    }

    /**
     * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
     */
    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0) return -1;
        int[] rs = new int[amount + 1];
        rs[0] = 0;
        for (int i = 1; i <= amount; i++) {
            rs[i] = Integer.MAX_VALUE;
        }
        int length = coins.length;
        for (int i = 0; i < length; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                rs[j] = Math.min(rs[j], rs[j - coins[i]] == Integer.MAX_VALUE ? Integer.MAX_VALUE : rs[j - coins[i]] + 1);
            }
        }
        if (rs[amount] == Integer.MAX_VALUE) return -1;
        return rs[amount];
    }

    /**
     * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
     */
    public int findDuplicate(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        int left = 1, right = nums.length - 1;
        while (left < right) {
            int middle = (left + right) / 2;
            int count = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] <= middle) {
                    count++;
                }
            }
            if (count <= middle) {
                left = middle;
            } else {
                right = middle;
            }
        }
        return left;
    }

    /**
     * 根据每日 气温 列表，请重新生成一个列表，对应位置的输入是你需要再等待多久温度才会升高的天数。如果之后都不会升高，请输入 0 来代替。
     * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
     * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的都是 [30, 100] 范围内的整数。
     */
    public int[] dailyTemperatures(int[] T) {
        if (T == null) return null;
        int length = T.length;
        if (length == 0) return T;
        if (length == 1) return new int[]{0};
        int[] result = new int[length];
        Stack<Integer> stack = new Stack<>();
        for (int i = length - 1; i >= 0; i--) {
            while ((!stack.isEmpty()) && T[stack.peek()] <= T[i]) {
                stack.pop();
            }
            result[i] = stack.isEmpty() ? 0 : stack.peek() - i;
            stack.push(i);
        }
        return result;
    }

    /**
     * 给定一个整数数组，你需要寻找一个连续的子数组，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
     * 你找到的子数组应是最短的，请输出它的长度。
     * 示例 1:
     * 输入: [2, 6, 4, 8, 10, 9, 15]
     * 输出: 5
     * 解释: 你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
     */
    public int findUnsortedSubarray(int[] nums) {
        if (nums == null || nums.length < 2) return 0;
        int length = nums.length;
        int min = nums[length - 1];
        int max = nums[0];
        int[] arr = new int[length];

        int left = -1;
        int right = -1;

        for (int i = length - 1; i >= 0; i--) {
            min = arr[i] = Math.min(min, nums[i]);
        }
        for (int i = 0; i < length; i++) {
            if (nums[i] != arr[i]) {
                left = i;
                break;
            }
        }

        for (int i = 0; i < length; i++) {
            max = arr[i] = Math.max(max, nums[i]);
        }
        for (int i = length - 1; i >= 0; i--) {
            if (nums[i] != arr[i]) {
                right = i;
                break;
            }
        }
        if (left == -1) {
            return 0;
        } else {
            return right - left + 1;
        }
    }

    /**
     * 给定一个用字符数组表示的 CPU 需要执行的任务列表。其中包含使用大写的 A - Z 字母表示的26 种不同种类的任务。任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。CPU 在任何一个单位时间内都可以执行一个任务，或者在待命状态。
     * 然而，两个相同种类的任务之间必须有长度为 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
     * 你需要计算完成所有任务所需要的最短时间。
     * 示例 1：
     * 输入: tasks = ["A","A","A","B","B","B"], n = 2
     * 输出: 8
     * 执行顺序: A -> B -> (待命) -> A -> B -> (待命) -> A -> B.
     * 注：
     * 任务的总个数为 [1, 10000]。
     * n 的取值范围为 [0, 100]。
     */
    public int leastInterval(char[] tasks, int n) {
        if (tasks == null) return 0;
        if (n == 0) return tasks.length;
        int[] arr = new int[26];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 0;
        }
        for (int i = 0; i < tasks.length; i++) {
            arr[tasks[i] - 'A']++;
        }

        int max = 0; //最多任务的数量
        int maxCount = 0; //最多数量的任务数，AB同时为3，该值为2
        int sum = 0; //总任务个数
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
            sum += arr[i];
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == max) {
                maxCount++;
            }
        }

        int mainSum = (max - 1) * n + max;//主线时间
        int otherSum = Math.max(0, sum - max - (max - 1) * n - (maxCount - 1));
        int result = mainSum + otherSum + (maxCount - 1);
        return result;
    }

    /**
     * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
     */
    public int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        int length = nums.length;
        if (length == 1) return nums[0] == k ? 1 : 0;
        int[] rs = new int[length];
        int result = 0;
        for (int i = 0; i < length; i++) {
            rs[i] = nums[i];
            if (rs[i] == k) result++;
            for (int j = i + 1; j < length; j++) {
                rs[j] = rs[j - 1] + nums[j];
                if (rs[j] == k) result++;
            }
        }
        return result;
    }

    /**
     * 峰值元素是指其值大于左右相邻值的元素。
     * 给定一个输入数组 nums，其中 nums[i] ≠ nums[i+1]，找到峰值元素并返回其索引。
     * 数组可能包含多个峰值，在这种情况下，返回任何一个峰值所在位置即可。
     * 可以假设 nums[-1] = nums[n] = -∞。
     */
    public int findPeakElement(int[] nums) {
        if (nums == null || nums.length < 1) return -1;
        int length = nums.length;
        if (length == 1) return 0;
        if (nums[0] > nums[1]) return 0;
        if (nums[length - 1] > nums[length - 2]) return length - 1;
        return handlePeak(nums, 1, length - 2);
    }

    private int handlePeak(int[] nums, int left, int right) {
        while (left < right) {
            int middle = (left + right) / 2;
            if (nums[middle] > nums[middle - 1] && nums[middle] > nums[middle + 1]) {
                return middle;
            } else if (nums[middle] > nums[middle - 1]) {
                return handlePeak(nums, middle + 1, right);
            } else {
                return handlePeak(nums, left, middle - 1);
            }
        }
        return left;
    }
}
