package leetcode;

import common.TreeNode;

import java.util.*;

/**
 * leetcode 树题目练习
 */
public class TreeNodeHelper {
    /**
     * 给定一个二叉树，返回它的中序 遍历。
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        if (root.left != null) result.addAll(inorderTraversal(root.left));
        result.add(root.val);
        if (root.right != null) result.addAll(inorderTraversal(root.right));
        return result;
    }

    /**
     * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
     * 假设一个二叉搜索树具有如下特征：
     * 节点的左子树只包含小于当前节点的数。
     * 节点的右子树只包含大于当前节点的数。
     * 所有左子树和右子树自身必须也是二叉搜索树。
     * 示例 1:
     * 输入:
     * 2
     * / \
     * 1   3
     * 输出: true
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        boolean result = true;
        if (root.left != null) {
            if (root.left.val >= root.val) {
                return false;
            }
        }
        if (root.right != null) {
            if (root.right.val <= root.val) {
                return false;
            }
        }
        result = result & isValidBST(root.left) & isValidBST(root.right);
        return result;
    }

    /**
     * 给定一个二叉树，检查它是否是镜像对称的。
     * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return symmetric(root.left, root.right);
    }

    private boolean symmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null)
            return true;
        if ((left == null && right != null) || (left != null && right == null))
            return false;
        if (left.val != right.val)
            return false;
        boolean result = true;
        return result && symmetric(left.left, right.right) && symmetric(left.right, right.left);
    }

    /**
     * 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);
        List<Integer> list = new ArrayList<>();
        while (queue.size() > 0) {
            TreeNode temp = queue.poll();
            if (temp == null) {
                List<Integer> record = new ArrayList<>(list);
                result.add(record);
                list = new ArrayList<>();
                if (queue.peek() != null) {
                    queue.add(null);
                }
            } else {
                list.add(temp.val);
                if (temp.left != null) queue.add(temp.left);
                if (temp.right != null) queue.add(temp.right);
            }
        }
        return result;
    }

    /**
     * 给定一个二叉树，找出其最大深度。
     * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
     * 说明: 叶子节点是指没有子节点的节点。
     */
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /**
     * 给定一个二叉树，原地将它展开为链表。
     */
    public void flatten(TreeNode root) {
        if (root == null) return;
        flat(root);
        root.val = nodes.get(0);
        TreeNode p = root;
        for (int i = 1; i < nodes.size(); i++) {
            TreeNode t = new TreeNode(nodes.get(i));
            p.right = t;
            p.left = null;
            p = p.right;
        }
    }

    private List<Integer> nodes = new ArrayList<>();

    private void flat(TreeNode root) {
        if (root == null) return;
        nodes.add(root.val);
        flat(root.left);
        flat(root.right);
    }

    /**
     * 翻转一棵二叉树
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode n = new TreeNode(root.val);
        n.left = invertTree(root.right);
        n.right = invertTree(root.left);
        return n;
    }

    /**
     * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (p == null && q == null) return root;
        getPath(root, p);
        List<TreeNode> plist = new ArrayList<>(parents);
        parents = new ArrayList<>();
        getPath(root, q);
        List<TreeNode> qlist = new ArrayList<>(parents);
        Collections.reverse(qlist);
        Collections.reverse(plist);
        TreeNode result = null;
        int length = Math.min(plist.size(), qlist.size());
        for (int i = 0; i < length; i++) {
            if (plist.get(i) == qlist.get(i)) {
                result = plist.get(i);
            } else {
                break;
            }
        }
        return result;
    }

    List<TreeNode> parents = new ArrayList<>();

    private boolean getPath(TreeNode root, TreeNode a) {
        if (root == null) {
            return false;
        }
        if (root == a) {
            parents.add(root);
            return true;
        }
        if (getPath(root.left, a) || getPath(root.right, a)) {
            parents.add(root);
            return true;
        }
        return false;
    }

    /**
     * 给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
     * 你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点。
     */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return null;
        if (t1 == null) return t2;
        if (t2 == null) return t1;
        TreeNode node = new TreeNode(t1.val + t2.val);
        node.left = mergeTrees(t1.left, t2.left);
        node.right = mergeTrees(t1.right, t2.right);
        return node;
    }
}
