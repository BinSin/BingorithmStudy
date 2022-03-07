/*
괄호 변환
https://programmers.co.kr/learn/courses/30/lessons/60058
 */

package practice.kakao.level2;

import java.util.Stack;

public class Solution06 {

    public static boolean check(String p) {
        char[] cArr = p.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char c : cArr) {
            switch (c) {
                case '(' -> stack.add(c);
                case ')' -> {
                    if(stack.isEmpty()) return false;
                    stack.pop();
                }
            }
        }
        return true;
    }

    public static String[] divide(String p) {
        String[] strArr = new String[2];
        char[] cArr = p.toCharArray();
        int[] count = new int[2];
        for (int i=0; i<p.length(); i++) {
            char c = cArr[i];
            switch (c) {
                case '(' -> count[0]++;
                case ')' -> count[1]++;
            }
            if (count[0] == count[1]) {
                strArr[0] = p.substring(0, i + 1);
                strArr[1] = p.substring(i + 1);
                break;
            }
        }
        return strArr;
    }

    public static String solution(String p) {
        // 1
        if(p.equals("")) {
            return "";
        }

        // 2
        String[] strArr = divide(p);
        String u = strArr[0];
        String v = strArr[1];

        StringBuilder sb = new StringBuilder();
        boolean check = check(u);
        if (check) { // 3
            return u + sb.append(solution(v));
        } else { // 4
            sb.append("(");
            sb.append(solution(v));
            sb.append(")");
            String reserveU = u.substring(1, u.length() - 1);
            for (int i=0; i<reserveU.length(); i++) {
                if(reserveU.charAt(i) == '(') sb.append(")");
                else sb.append("(");
            }
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        String p1 = "(()())()";
        System.out.println(solution(p1).equals("(()())()"));

        String p2 = ")(";
        System.out.println(solution(p2).equals("()"));

        String p3 = "()))((()";
        System.out.println(solution(p3).equals("()(())()"));
    }

}
