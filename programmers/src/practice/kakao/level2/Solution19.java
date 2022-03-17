/*
k진수에서 소수 개수 구하기
https://programmers.co.kr/learn/courses/30/lessons/92335
 */

package practice.kakao.level2;

import java.math.BigInteger;

public class Solution19 {

    public static int solution(int n, int k) {
        int answer = 0;
        String number = Integer.toString(n, k);
        String[] pArr = number.split("0");
        for (String p : pArr) {
            if (p.equals("")) continue;
            if (BigInteger.valueOf(Long.parseLong(p)).isProbablePrime(1)) answer++;
        }
        return answer;
    }

    public static void main(String[] args) {
        int n1 = 437674, k1 = 3;
        System.out.println(solution(n1, k1));

        int n2 = 110011, k2 = 10;
        System.out.println(solution(n2, k2));
    }

}
