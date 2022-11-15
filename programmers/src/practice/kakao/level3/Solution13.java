/*
BinSin
https://school.programmers.co.kr/learn/courses/30/lessons/81303
분류: 연결 리스트
 */

package practice.kakao.level3;

import java.util.*;

public class Solution13 {

    public static String solution(int n, int k, String[] cmd) {
        Set<Integer> set = new TreeSet<>();
        for (int i=0; i<n; i++) set.add(i);

        int selectIndex = k;
        Deque<Integer> stack = new ArrayDeque<>();
        for (String c : cmd) {
            String[] cArr = c.split(" ");
            switch (cArr[0]) {
                case "C" -> {
                    int selectNumber = (int) set.toArray()[selectIndex];
                    stack.add(selectNumber);
                    set.remove(selectNumber);
                    if (selectIndex == set.size()) selectIndex--; // 삭제된 행이 가장 마지막 행인 경우 바로 윗 행을 선택
                }
                case "Z" -> {
                    int recoveryNumber = stack.removeLast();
                    set.add(recoveryNumber);
                    if (selectIndex >= recoveryNumber) selectIndex++;
                }
                case "D" -> {
                    int move = Integer.parseInt(cArr[1]);
                    selectIndex += move;
                }
                case "U" -> {
                    int move = Integer.parseInt(cArr[1]);
                    selectIndex -= move;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        Object[] setArr = set.toArray();
        for (int i=0, j=0; i<n; i++) {
            if ((int) setArr[j] == i) {
                sb.append("O");
                j++;
            }
            else sb.append("X");
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
