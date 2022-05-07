package exam.kakao_2022_05;

import java.util.*;

public class Solution02 {

    private static class CustomQueue {
        Queue<Integer> queue1, queue2;
        long diff;
        CustomQueue(Queue<Integer> queue1, Queue<Integer> queue2) {
            this.queue1 = queue1;
            this.queue2 = queue2;

            long sum1 = 0, sum2 = 0;
            for (Integer v : queue1) sum1 += v;
            for (Integer v : queue2) sum2 += v;
            diff = sum1 - sum2;
        }

        public void leftPop() {
            Integer v = queue1.remove();
            queue2.add(v);
            diff -= (v * 2);
        }

        public void rightPop() {
            Integer v = queue2.remove();
            queue1.add(v);
            diff += (v * 2);
        }

    }

    // 정답이 나올 수 있는 경우인지 체크
    private static boolean check(int[] queue1, int[] queue2) {
        List<Integer> list = new ArrayList<>();
        long sum = 0;
        
        for (int q : queue1) {
            sum += q;
            list.add(q);
        }
        for (int q : queue2) {
            sum += q;
            list.add(q);
        }
        if (sum % 2 == 1) return false; // 합이 홀수일 경우 유효하지 않으므로 false

        for (int q : queue1) list.add(q);

        long div = sum / 2;
        long acc = 0;
        int index = 0;
        for (int i=0; i<list.size();) {
            int v = list.get(i);
            if (acc < div) {
                acc += v;
                i++;
            } else if (acc > div) {
                acc -= list.get(index++);  // 누적 합이 적을 때 가장 처음에 더했던 숫자 빼기
            } else {
                return true;
            }
        }

        return false;
    }

    public static int solution(int[] queue1, int[] queue2) {
        int answer = 0;

        if (!check(queue1, queue2)) {
            return -1;
        }

        Queue<Integer> q1 = new LinkedList<>();
        for (int v : queue1) q1.add(v);
        Queue<Integer> q2 = new LinkedList<>();
        for (int v : queue2) q2.add(v);
        CustomQueue cq = new CustomQueue(q1, q2);

        while (true) {
            if (cq.diff == 0) {
                break;
            } else if (cq.diff > 0) {
                cq.leftPop();
            } else {
                cq.rightPop();
            }
            answer++;
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] queue11 = {3, 2, 7, 2};
        int[] queue21 = {4, 6, 5, 1};
        System.out.println(solution(queue11, queue21));

        int[] queue12 = {1, 2, 1, 2};
        int[] queue22 = {1, 10, 1, 2};
        System.out.println(solution(queue12, queue22));

        int[] queue13 = {1, 1};
        int[] queue23 = {1, 5};
        System.out.println(solution(queue13, queue23));
    }

}
