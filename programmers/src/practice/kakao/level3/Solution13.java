/*
BinSin
https://school.programmers.co.kr/learn/courses/30/lessons/81303
분류: 연결 리스트
 */

package practice.kakao.level3;

import java.util.*;

public class Solution13 {

    private static class Node {
        int number, prev, next;

        public Node(int number, int prev, int next) {
            this.number = number;
            this.prev = prev;
            this.next = next;
        }
    }

    public static String solution(int n, int k, String[] cmd) {
        Node[] nodeArr = new Node[n];
        for (int i=0; i<n; i++) {
            nodeArr[i] = new Node(i, i-1, i+1);
        }
        nodeArr[n-1].next = -1;

        StringBuilder sb = new StringBuilder("O".repeat(n));
        int selectIndex = k;
        Deque<Node> stack = new ArrayDeque<>();
        for (String c : cmd) {
            String[] cArr = c.split(" ");
            switch (cArr[0]) {
                case "C" -> {
                    Node selectNode = nodeArr[selectIndex];
                    int current = selectNode.number;
                    int prev = selectNode.prev;
                    int next = selectNode.next;

                    stack.add(selectNode);
                    if (prev != -1) nodeArr[prev].next = next;
                    if (next != -1) nodeArr[next].prev = prev;
                    sb.setCharAt(current, 'X');

                    if (next != -1) selectIndex = nodeArr[selectIndex].next;
                    else selectIndex = nodeArr[selectIndex].prev; // 삭제된 행이 가장 마지막 행인 경우 바로 윗 행을 선택
                }
                case "Z" -> {
                    Node recoveryNode = stack.removeLast();
                    int current = recoveryNode.number;
                    int prev = recoveryNode.prev;
                    int next = recoveryNode.next;

                    if (prev != -1) nodeArr[prev].next = current;
                    if (next != -1) nodeArr[next].prev = current;
                    sb.setCharAt(current, 'O');
                }
                case "D" -> {
                    int move = Integer.parseInt(cArr[1]);
                    while (move-- > 0) {
                        selectIndex = nodeArr[selectIndex].next;
                    }
                }
                case "U" -> {
                    int move = Integer.parseInt(cArr[1]);
                    while (move-- > 0) {
                        selectIndex = nodeArr[selectIndex].prev;
                    }
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int n1 = 8, k1 = 2;
        String[] cmd1 = {"D 2","C","U 3","C","D 4","C","U 2","Z","Z"};
        System.out.println(solution(n1, k1, cmd1));

        int n2 = 8, k2 = 2;
        String[] cmd2 = {"D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"};
        System.out.println(solution(n2, k2, cmd2));
    }
}
