package exam.kakao_2021_09;

import java.math.BigInteger;
import java.util.StringTokenizer;

public class Solution02 {

    public static String change(int n, int k) {
        String stringNumber = "";
        while(n > 0) {
            stringNumber = (n % k) + stringNumber;
            n /= k;
        }
        return stringNumber;
    }

    public static int solution(int n, int k) {
        int answer = 0;
        String changeNumber = change(n, k);
        StringTokenizer st = new StringTokenizer(changeNumber, "0");

        while(st.hasMoreTokens()) {
            Long next = Long.parseLong(st.nextToken());
            BigInteger bi = BigInteger.valueOf(next);
            if(bi.isProbablePrime(10)) answer++;
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(437674, 3)); // 3
        System.out.println(solution(110011, 10)); // 2

    }
}
