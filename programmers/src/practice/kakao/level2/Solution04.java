/*
단체사진 찍기
https://programmers.co.kr/learn/courses/30/lessons/1835
 */

package practice.kakao.level2;

import java.util.ArrayList;

public class Solution04 {

    private static final char[] list = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    private static char[] answer = new char[8];
    private static boolean[] check = new boolean[8];
    private static int count = 0;

    private static void permutation(int depth, int r, String[] data) {
        if(depth == r) {
            if(check(data)) count++;
            return;
        }

        for (int i = 0; i < r; i++) {
            if(!check[i]) {
                check[i] = true;
                answer[depth] = list[i];
                permutation(depth + 1, r, data);
                check[i] = false;
            }
        }
    }

    private static boolean check(String[] data) {
        for(String str : data) {
            char a = str.charAt(0);
            char b = str.charAt(2);
            char sign = str.charAt(3);
            int c = str.charAt(4) - '0' + 1;

            String answerString = String.valueOf(answer);
            int aIndex = answerString.indexOf(a);
            int bIndex = answerString.indexOf(b);
            int diff = Math.abs(aIndex - bIndex);

            switch (sign) {
                case '>' -> {
                    if(diff <= c) return false;
                }
                case '=' -> {
                    if(diff != c) return false;
                }
                case '<' -> {
                    if(diff >= c) return false;
                }
            }
        }
        return true;
    }

    public static int solution(int n, String[] data) {
        permutation(0, 8, data);
        return count;
    }

    public static void main(String[] args) {
        int n1 = 2;
        String[] data1 = {"N~F=0", "R~T>2"};
        System.out.println(solution(n1, data1));

        // 전역 변수 초기화
        answer = new char[8];
        check = new boolean[8];
        count = 0;
        
        int n2 = 2;
        String[] data2 = {"M~C<2", "C~M>1"};
        System.out.println(solution(n2, data2));
    }
}
