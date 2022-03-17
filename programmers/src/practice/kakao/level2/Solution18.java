/*
N진수 게임
https://programmers.co.kr/learn/courses/30/lessons/17687
 */

package practice.kakao.level2;

public class Solution18 {

    private static char[] numbers = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    private static String convert(int nextNumber, int n) {
        StringBuilder sb = new StringBuilder();
        while (nextNumber != 0) {
            sb.append(numbers[nextNumber % n]);
            nextNumber /= n;
        }
        return sb.reverse().toString();
    }

    public static String totalString(int n, int t, int m) {
        int size = t * m;
        StringBuilder sb = new StringBuilder();
        sb.append("0");
        int nextNumber = 1;
        while (sb.length() < size) {
            String next = convert(nextNumber++, n);
            sb.append(next);
        }
        return sb.toString();
    }

    public static String solution(int n, int t, int m, int p) {
        String totalString = totalString(n, t, m);
        StringBuilder answer = new StringBuilder();
        for (int i=0; i<t; i++) {
            int l = m * i + p - 1;
            answer.append(totalString.charAt(l));
        }
        return answer.toString();
    }

    public static void main(String[] args) {
        int n1 = 2, t1 = 4, m1 = 2, p1 = 1;
        System.out.println(solution(n1, t1, m1, p1).equals("0111"));

        int n2 = 16, t2 = 16, m2 = 2, p2 = 1;
        System.out.println(solution(n2, t2, m2, p2).equals("02468ACE11111111"));

        int n3 = 16, t3 = 16, m3 = 2, p3 = 2;
        System.out.println(solution(n3, t3, m3, p3).equals("13579BDF01234567"));
    }

}
