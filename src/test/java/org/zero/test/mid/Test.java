package org.zero.test.mid;

public class Test {

    // 合并两个单向有序链表

    static class Node {
        int val;
        Node next;

        public Node(int v, Node n) {
            this.val = v;
            this.next = n;
        }
        public Node(int v) {
            this.val = v;
        }
        public Node() {}

        public String toString() {
            StringBuilder sb = new StringBuilder();
            Node curr = this;
            do {
                sb.append("," + curr.val);
                curr = curr.next;
            } while(curr != null);
            return sb.toString();
        }
    }

    static Node buildLink(int[] arr) {
        if(arr.length == 0) {
            return null;
        }
        Node link = new Node();
        Node curr = link;
        for(int i=0; i<arr.length; i++) {
            curr.next = new Node(arr[i]);
            curr = curr.next;
        }
        return link.next;
    }

    public static void main(String[] args) {
        Node link1 = buildLink(new int[]{1,3,5});
        Node link2 = buildLink(new int[]{2,4,6,10, 12});
        System.out.println("link1: " + link1.toString());
        System.out.println("link2: " + link2.toString());
        Node merged = merge(link1, link2);
        System.out.println("merged: " + merged.toString());
    }

    public static Node merge(Node link1, Node link2) {
        Node merged = new Node();
        Node mergedCurr = merged;
        Node link1Curr = link1;
        Node link2Curr = link2;
        do {
            if(link1Curr.val > link2Curr.val) {
                mergedCurr.next = new Node(link2Curr.val);
                mergedCurr = mergedCurr.next;
                link2Curr = link2Curr.next;
            } else {
                mergedCurr.next = new Node(link1Curr.val);
                mergedCurr = mergedCurr.next;
                link1Curr = link1Curr.next;
            }
        } while(link1Curr == null || link2Curr == null);
        // 出现一个已经遍历完成，则处理剩下的
        if(link1Curr != null) {
            mergedCurr.next = link1Curr;
        }
        if(link2Curr != null) {
            mergedCurr.next = link2Curr;
        }
        return merged.next;
    }
}
