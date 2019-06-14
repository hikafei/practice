package leetcode;

import common.ListNode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 链表leetcode练习
 */
public class ListNodeHelper {

    /**
     * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
     * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
     * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * 原因：342 + 465 = 807
     *
     * think:
     * 利用新建对象保存结果和是否进1。新建多余的节点pre用于链表开始位置，用pre.next来返回结果。新建指针p用于遍历
     *
     * cal:
     * O(n),O(n)
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode pre = new ListNode(0);
        ListNode p = pre;
        int one = 0;
        while (l1 != null || l2 != null) {
            int x = 0, y = 0;
            if (l1 != null) {
                x = l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                y = l2.val;
                l2 = l2.next;
            }
            int sum = x + y + one;
            one = sum / 10;
            int temp = sum % 10;
            ListNode node = new ListNode(temp);
            p.next = node;
            p = p.next;
        }
        if (one == 1) {
            ListNode node = new ListNode(1);
            p.next = node;
        }
        return pre.next;
    }

    /**
     * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
     * 示例：
     * 给定一个链表: 1->2->3->4->5, 和 n = 2.
     * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
     * 说明：
     * 给定的 n 保证是有效的。
     */
    public ListNode removeNthFromEnd(ListNode head, int n) throws Exception {
        if (head == null) return null;
        ListNode fast = head;
        ListNode slow = head;
        ListNode slowPre = null;
        int count = 0;
        while (count < n - 1) {
            if (fast != null) {
                fast = fast.next;
                count++;
            } else {
                throw new Exception("n无效");
            }
        }
        if (fast.next == null) {
            head = head.next;
            return head;
        } else {
            while (fast.next != null) {
                fast = fast.next;
                slowPre = slow;
                slow = slow.next;
            }
            slowPre.next = slow.next;
            return head;
        }
    }

    /**
     * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     * <p>
     * 示例：
     * <p>
     * 输入：1->2->4, 1->3->4
     * 输出：1->1->2->3->4->4
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        int a = l1.val;
        int b = l2.val;
        ListNode node = null;
        if (a <= b) {
            node = new ListNode(a);
            node.next = mergeTwoLists(l1.next, l2);
        } else {
            node = new ListNode(b);
            node.next = mergeTwoLists(l1, l2.next);
        }
        return node;
    }

    /**
     * 反转一个单链表。
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode b = head, a=null, temp;
        while (b != null) {
            temp = b.next;
            b.next = a;
            a = b;
            b = temp;
        }
        return a;
//        if (head == null || head.next == null) return head;
//        ListNode node = reverseList(head.next);
//        head.next.next = head;
//        head.next = null;
//        return node;
    }

    /**
     * 给定一个链表，判断链表中是否有环。
     * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环
     */
    public boolean hasCycle(ListNode head) {
        if (head == null) return false;
        ListNode p1 = head;
        ListNode p2 = head;
        while (p1 != null && p1.next != null && p1.next.next != null) {
            p1 = p1.next.next;
            p2 = p2.next;
            if (p1.val == p2.val) {
                return true;
            }
        }
        return false;
    }

    /**
     * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
     * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null) return null;
        ListNode p1 = head, p2 = head, p3 = head;
        boolean flag = false;
        while (p1 != null && p1.next != null && p1.next.next != null) {
            p1 = p1.next.next;
            p2 = p2.next;
            if (p1 == p2) {
                flag = true;
                break;
            }
        }
        if (!flag) return null;
        while (p2 != p3) {
            p2 = p2.next;
            p3 = p3.next;
        }
        return p2;
    }

    /**
     * 编写一个程序，找到两个单链表相交的起始节点。
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        Stack<ListNode> s1 = new Stack<>();
        Stack<ListNode> s2 = new Stack<>();
        ListNode p1 = headA, p2 = headB;
        while (p1 != null) {
            s1.push(p1);
            p1 = p1.next;
        }
        while (p2 != null) {
            s2.push(p2);
            p2 = p2.next;
        }
        ListNode result = null;
        while (s1.size() >= 0 && s2.size() >= 0) {
            ListNode n1 = s1.pop();
            ListNode n2 = s2.pop();
            if (n1 == n2) {
                result = n1;
            } else {
                break;
            }
        }
        return result;
    }

    /**
     * 请判断一个链表是否为回文链表。
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null) return true;
        if (head.next == null) return true;
        ListNode n1 = head;
        ListNode n2 = head;
        while (n1 != null && n1.next != null) {
            n1 = n1.next.next;
            n2 = n2.next;
        }
        ListNode p = n2;
        ListNode pre = null;
        ListNode temp = null;
        while (p != null) {
            temp = p.next;
            p.next = pre;
            pre = p;
            p = temp;
        }
        n1 = head;
        while (pre != null) {
            if (pre.val != n1.val) {
                return false;
            }
            pre = pre.next;
            n1 = n1.next;
        }
        return true;
    }
}
