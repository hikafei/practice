package bag;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * 一个背包的总容量为V,现在有N类物品,第i类物品的重量为weight[i],价值为value[i]
 * 那么往该背包里装东西,怎样装才能使得最终包内物品的总价值最大。这里装物品主要由三种装法：
 * 0-1背包：每类物品只能装一次
 * 完全背包：每类物品可以无限次装进包内
 * 多重背包：每类物品都有个数限制，第i件物品最多可以使用num[i]次
 * <p>
 * 背包可以分为必须装满和不必装满两种情况
 */
public class BagHelper {

    /**
     * 01背包,背包要求全部装满的最大价值
     *
     * @param v  背包容量
     * @param ws 物品的体积
     * @param vs 物品的价值
     */
    public int packZeroOneWithFull(int v, int[] ws, int[] vs) {
        if (v <= 0 || ws == null || ws.length == 0 || vs == null || vs.length == 0 || (ws.length != vs.length)) {
            return 0;
        }
        int goodCount = ws.length;

        int[] volumes = new int[v + 1];
        volumes[0] = 0;
        for (int i = 1; i <= v; i++) {
            volumes[i] = Integer.MIN_VALUE;
        }
//        IntStream.of(volumes).forEach(a -> System.out.print(a + " "));  //打印volumes数组
//        System.out.println();

        for (int i = 0; i < goodCount; i++) {
            for (int j = v; j >= ws[i]; j--) {
                volumes[j] = Math.max(volumes[j], volumes[j - ws[i]] + vs[i]);
            }
//            IntStream.of(volumes).forEach(a -> System.out.print(a + " "));//打印volumes数组
//            System.out.println();
        }
        return volumes[v];
    }

    /**
     * 01背包,背包不要求全部装满的最大价值
     */
    public int packZeroOneWithoutFull(int v, int[] ws, int[] vs) {
        if (v <= 0 || ws == null || ws.length == 0 || vs == null || vs.length == 0 || (ws.length != vs.length)) {
            return 0;
        }
        int goodCount = ws.length;

        int[] volumes = new int[v + 1];
        for (int i = 0; i <= v; i++) {
            volumes[i] = 0;
        }
        for (int i = 0; i < goodCount; i++) {
            for (int j = v; j >= ws[i]; j--) {
                volumes[j] = Math.max(volumes[j], volumes[j - ws[i]] + vs[i]);
            }
        }
        return volumes[v];
    }

    /**
     * 01背包,背包不要求全部装满的最大价值，且过程中，打印满足最大价值的所有情况
     *
     * @param v
     * @param ws
     * @param vs
     * @return
     */
    public int packZeroOneWithoutFullAndPrint(int v, int[] ws, int[] vs) {
        if (v <= 0 || ws == null || ws.length == 0 || vs == null || vs.length == 0 || (ws.length != vs.length)) {
            return 0;
        }
        int goodCount = ws.length;
        int[] volumes = new int[v + 1];
//        List<List<List<Integer>>> records = new ArrayList<>();
        for (int i = 0; i <= v; i++) {
            volumes[i] = 0;
//            List<List<Integer>> record = new ArrayList<>();
//            records.add(record);
        }
        for (int i = 0; i < goodCount; i++) {
            for (int j = v; j >= ws[i]; j--) {
                if (volumes[j] <= volumes[j - ws[i]] + vs[i]) {
//                    List<List<Integer>> current = new ArrayList<>();
                    if (volumes[j] < volumes[j - ws[i]] + vs[i]) {
                        volumes[j] = volumes[j - ws[i]] + vs[i];
                    } else {
//                        current.addAll(records.get(j));
                    }
//                    List<List<Integer>> record = records.get(j - ws[i]);
//                    if (record.size() == 0) {
//                        List<Integer> per = new ArrayList<>();
//                        per.add(i + 1);
//                        current.add(per);
//                    } else {
//                        for (List<Integer> per : record) {
//                            List<Integer> temp = new ArrayList<>();
//                            temp.addAll(per);
//                            temp.add(i + 1);
//                            current.add(temp);
//                        }
//                    }
//                    records.remove(j);
//                    records.add(j, current);
                }
            }
        }
//        List<List<Integer>> r = records.get(v);
//        for (List<Integer> l : r) {
//            l.forEach(a -> System.out.print(a + " "));
//            System.out.println();
//        }
        return volumes[v];
    }

    /**
     * 完全背包,背包不要求全部装满的最大价值，且过程中，打印满足最大价值的所有情况
     *
     * @param v
     * @param ws
     * @param vs
     * @return
     */
    public int packCompleteWithoutFullAndPrint(int v, int[] ws, int[] vs) {
        if (v <= 0 || ws == null || ws.length == 0 || vs == null || vs.length == 0 || (ws.length != vs.length)) {
            return 0;
        }
        int goodCount = ws.length;
        int[] volumes = new int[v + 1];
//        List<List<List<Integer>>> records = new ArrayList<>();
        for (int i = 0; i <= v; i++) {
            volumes[i] = 0;
//            List<List<Integer>> record = new ArrayList<>();
//            records.add(record);
        }
        for (int i = 0; i < goodCount; i++) {
            for (int j = ws[i]; j <= v; j++) {
                if (volumes[j] <= volumes[j - ws[i]] + vs[i]) {
//                    List<List<Integer>> current = new ArrayList<>();
                    if (volumes[j] < volumes[j - ws[i]] + vs[i]) {
                        volumes[j] = volumes[j - ws[i]] + vs[i];
                    } else {
//                        current.addAll(records.get(j));
                    }
//                    List<List<Integer>> record = records.get(j - ws[i]);
//                    if (record.size() == 0) {
//                        List<Integer> per = new ArrayList<>();
//                        per.add(i + 1);
//                        current.add(per);
//                    } else {
//                        for (List<Integer> per : record) {
//                            List<Integer> temp = new ArrayList<>();
//                            temp.addAll(per);
//                            temp.add(i + 1);
//                            current.add(temp);
//                        }
//                    }
//                    records.remove(j);
//                    records.add(j, current);
                }
            }
        }
//        List<List<Integer>> r = records.get(v);
//        for (List<Integer> l : r) {
//            l.forEach(a -> System.out.print(a + " "));
//            System.out.println();
//        }
        return volumes[v];
    }

    /**
     * @param ns 物品的个数限制,不要求恰好装满
     * @return
     */
    public int packMany(int v, int[] ws, int[] vs, int[] ns) {
        if (v <= 0 || ws == null || ws.length == 0 || vs == null || vs.length == 0 || ns == null || ns.length == 0 || (ws.length != vs.length)) {
            return 0;
        }

        // 将单个物品 按照个数限制，拆成多个物品，例如，ns[0] = 13 ,拆成 8+4+1， ns[1]=10 拆成8+2
        int oldLength = ns.length;
        List<Integer> wsList = new ArrayList<>();
        List<Integer> vsList = new ArrayList<>();
        for (int i = 0; i < oldLength; i++) {
            List<int[]> temp = getManyNumbers(ws[i], vs[i], ns[i]);
            if (temp.size() > 0) {
                for (int j = 0; j < temp.size(); j++) {
                    wsList.add(temp.get(j)[0]);
                    vsList.add(temp.get(j)[1]);
                }
            }
        }
        ws = wsList.stream().mapToInt(Integer::intValue).toArray();
        vs = vsList.stream().mapToInt(Integer::intValue).toArray();

        int goods = ws.length;
        int[] volumes = new int[v + 1];
        for (int i = 0; i <= v; i++) {
            volumes[i] = 0;
        }
        for (int i = 0; i < goods; i++) {
            for (int j = v; j >= ws[i]; j--) {
                volumes[j] = Math.max(volumes[j], volumes[j - ws[i]] + vs[i]);
            }
//            IntStream.of(volumes).forEach(a -> System.out.print(a + " "));
//            System.out.println();
        }
        return volumes[v];
    }

    private List<int[]> getManyNumbers(int weight, int value, int number) {
        if (number <= 0) return new ArrayList<>();
        List<int[]> result = new ArrayList<>();
        int i = 1;
        int product = 1;
        while (product <= number) {
            result.add(new int[]{weight, value});
            i++;
            product *= 2;
            weight *= 2;
            value *= 2;
        }
        return result;
    }
}
