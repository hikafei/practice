package leetcode;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * leetcode字符串练习
 */
public class StringHelper {
    /**
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     * think:
     * 遍历s，使用stringbuilder(sb)来判断是否包含当前下标的字符，如包含，更新result，删除sb中该字符(含)前的所有字符；不存在的话，sb添加该字符
     *
     * cal:
     * O(n) o(n)
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        int result = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            String a = String.valueOf(s.charAt(i));
            int index = sb.indexOf(a);
            if (index >= 0) {
                result = Math.max(sb.length(), result);
                sb = sb.delete(0, index + 1);
            }
            sb.append(a);
        }
        return Math.max(sb.length(), result);
    }

    /**
     * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
     */
    public String longestPalindrome(String s) {
        if (s == null || s.equals("") || s.length() == 1) {
            return s;
        }
        String result = "";
        for (int i = 1; i < s.length(); i++) {
            String str = palindrome(s, i);
            if (str.length() > result.length()) {
                result = str;
            }
        }
        return result;
    }

    private String palindrome(String s, int index) {
        int a = index, b = index;
        int length = s.length();
        while (a >= 1) {
            if (s.charAt(a - 1) == s.charAt(index))
                a--;
            else
                break;
        }
        while (b < length - 1 && a >= 1) {
            if (s.charAt(a - 1) == s.charAt(b + 1)) {
                a--;
                b++;
            } else {
                break;
            }
        }
        return s.substring(a, b + 1);
    }

    /**
     * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
     * <p>
     * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
     * <p>
     * L   C   I   R
     * E T O E S I I G
     * E   D   H   N
     * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
     */
    public String convert(String s, int numRows) {
        if (numRows <= 0) return null;
        int length = s.length();
        if (s == null || length == 0 || length == 1 || numRows == 1) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        int sum = (numRows - 1) * 2;
        for (int i = 0; i <= numRows - 1; i++) {
            for (int j = 0; j < length; j++) {
                if (j % sum == i || j % sum == sum - i) {
                    sb.append(s.charAt(j));
                }
            }
        }
        String result = sb.toString();
        return result;
    }

    /**
     * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
     * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     * 2:abc 3:def 4:ghi ……
     */
    public List<String> letterCombinations(String digits) {
        int index = digits.length();
        List<String> result = Arrays.asList("");
        while (index > 0) {
            List<String> temp = new ArrayList<>();
            temp.addAll(result);
            List<String> letters = getLetters(digits.charAt(--index));
            result = letters.stream().flatMap(s ->
                    temp.stream()
                            .map(t -> String.format("%s%s", s, t))
            ).collect(Collectors.toList());
        }
        return result;
    }

    private List<String> getLetters(char s) {
        switch (s) {
            case '2': {
                return Arrays.asList("a", "b", "c");
            }
            case '3': {
                return Arrays.asList("d", "e", "f");
            }
            case '4': {
                return Arrays.asList("g", "h", "i");
            }
            case '5': {
                return Arrays.asList("j", "k", "l");
            }
            case '6': {
                return Arrays.asList("m", "n", "o");
            }
            case '7': {
                return Arrays.asList("p", "q", "r", "s");
            }
            case '8': {
                return Arrays.asList("t", "u", "v");
            }
            case '9': {
                return Arrays.asList("w", "x", "y", "z");
            }
            default: {
                return Arrays.asList("");
            }
        }
    }

    /**
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
     * 有效字符串需满足：
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * 注意空字符串可被认为是有效字符串。
     */
    public boolean isValid(String s) {
        if (s.equals("")) return true;
        Stack<Character> stack = new Stack<>();
        int length = s.length();
        int index = 0;
        boolean flag = false;
        while (index < length) {
            Character c = s.charAt(index++);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                int stackSize = stack.size();
                if (stackSize <= 0) return false;
                Character temp = stack.pop();
                if ((c == ')' && temp == '(') ||
                        (c == ']' && temp == '[') ||
                        (c == '}' && temp == '{')) {
                    continue;
                } else {
                    return false;
                }
            }
        }
        if (stack.size() == 0) flag = true;
        return flag;
    }

    /**
     * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
     * <p>
     * 例如，给出 n = 3，生成结果为：
     * <p>
     * [
     * "((()))",
     * "(()())",
     * "(())()",
     * "()(())",
     * "()()()"
     * ]
     */
    public List<String> generateParenthesis(int n) {
        String ps = "()";
        if (n == 0) return new ArrayList<>();
        if (n == 1) return Arrays.asList(ps);
        List<String> result = new ArrayList<>();
        List<String> pre = generateParenthesis(n - 1);
        for (String s : pre) {
            int length = s.length();
            for (int i = 1; i < length; i++) {
                if (s.charAt(i) == ')') {
                    result.add(s.substring(0, i) + "()" + s.substring(i));
                }
            }
        }
        result.add(String.join("", Collections.nCopies(n, ps)));
        result = result.stream().distinct().collect(Collectors.toList());
        return result;
    }

    /**
     * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
     * 示例:
     * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
     * 输出:
     * [
     * ["ate","eat","tea"],
     * ["nat","tan"],
     * ["bat"]
     * ]
     * 说明：
     * 所有输入均为小写字母。
     * 不考虑答案输出的顺序。
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) return new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            String s = Stream.of(strs[i].split("")).sorted().collect(Collectors.joining());
            if (map.containsKey(s)) {
                List<String> temp = new ArrayList<String>(map.get(s));
                temp.add(strs[i]);
                map.replace(s, temp);
            } else {
                List<String> temp = new ArrayList<String>();
                temp.add(strs[i]);
                map.put(s, temp);
            }
        }
        List<List<String>> result = new ArrayList<>(map.values());
        return result;
    }
}
