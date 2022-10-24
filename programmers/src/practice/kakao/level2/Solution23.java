package practice.kakao.level2;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution23 {

    public static int solution(int[] queue1, int[] queue2) {
        int max = queue1.length + queue2.length;
        if (max % 2 == 1) return -1;

        long sum1 = 0;
        long sum2 = 0;

        Deque<Integer> q1 = new ArrayDeque<>();
        for (int q : queue1) {
            q1.add(q);
            sum1 += q;
        }

        Deque<Integer> q2 = new ArrayDeque<>();
        for (int q : queue2) {
            q2.add(q);
            sum2 += q;
        }

        int answer = 0;
        while(true) {
            if (sum1 > sum2) {
                int q = q1.pop();
                q2.add(q);
                sum2 += q;
                sum1 -= q;
            } else if ( sum1 < sum2) {
                int q = q2.pop();
                q1.add(q);
                sum1 += q;
                sum2 -= q;
            } else {
                return answer;
            }
            answer++;

            if(answer >= max) return -1;
        }
    }

    public static void main(String[] args) {
        int[][] queue1 = {{3, 2, 7, 2}, {1, 2, 1, 2}, {1, 1}, {1, 1, 1}};
        int[][] queue2 = {{4, 6, 5, 1}, {1, 10, 1, 2}, {1, 5}, {1, 2}};
        for (int i=0; i<4; i++) System.out.println(solution(queue1[i], queue2[i]));
    }
}
