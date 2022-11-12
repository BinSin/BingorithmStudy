package exam.lgcns_2022_11;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Solution01 {

    private static int[] answer;
    private static int answerCount;

    public static int[] solution(int[] marbles) {
        int maxLength = marbles.length;
        answerCount = maxLength + 1;

        Arrays.sort(marbles); // 미리 오름차순으로 정렬
        for (int length=marbles.length; length>0; length--) {
            answer = new int[length];
            int[] selectMarbles = new int[length];
            boolean[] visited = new boolean[maxLength];

            permutation(marbles, selectMarbles, visited, 0, maxLength, length);

            if (answerCount == 0) return answer;
        }

        return answer;
    }

    // 순열: 순서 없이 n 개중에서 r 개를 뽑는 경우
    private static void permutation(int[] marbles, int[] selectMarbles, boolean[] visited, int depth, int n, int r) {
        if (depth == r) {
            int differentCount = isBeautiful(selectMarbles);
            if (differentCount == -1) return;
            if (!isMoreBeautiful(selectMarbles, differentCount)) return;
            answerCount = differentCount;
            answer = selectMarbles.clone();
            return;
        }

        for (int i=0; i<n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                selectMarbles[depth] = marbles[i];
                permutation(marbles, selectMarbles, visited, depth + 1, n, r);
                visited[i] = false;
            }
        }
    }

    // 해당 구슬 장식들이 아름다운지 비교, 좌우 구슬 수의 차이를 리턴
    private static int isBeautiful(int[] selectMarbles) {
        int length = selectMarbles.length;
        if (length == 1) return 0;

        int leftSum = 0;
        int rightSum = Arrays.stream(selectMarbles).sum();
        int count = length;
        for (int marble : selectMarbles) {
            rightSum -= marble;
            count--;
            if (leftSum == rightSum) return Math.abs(count);

            leftSum += marble;
            count--;
            if (leftSum == rightSum) return Math.abs(count);
        }
        return -1;
    }

    // 현재 정답보다 더 아름다운지 검사
    private static boolean isMoreBeautiful(int[] selectMarbles, int differentCount) {
        if (differentCount < answerCount) return true; // 1

        if (selectMarbles.length > answer.length) return true; // 2

        int selectSum = Arrays.stream(selectMarbles).sum();
        int answerSum = Arrays.stream(answer).sum();
        if (selectSum > answerSum) return true; // 3

        String selectString = Arrays.stream(selectMarbles).mapToObj(String::valueOf).collect(Collectors.joining());
        String answerString = Arrays.stream(answer).mapToObj(String::valueOf).collect(Collectors.joining());
        return selectString.compareTo(answerString) < 0; // 4
    }

    public static void main(String[] args) {
        int[] marbles1 = {1,2,3,4,4};
        System.out.println(Arrays.stream(solution(marbles1)).mapToObj(String::valueOf).collect(Collectors.joining(" ")));

        int[] marbles2 = {5,5,1,4};
        System.out.println(Arrays.stream(solution(marbles2)).mapToObj(String::valueOf).collect(Collectors.joining(" ")));

        int[] marbles3 = {3,9,7,5};
        System.out.println(Arrays.stream(solution(marbles3)).mapToObj(String::valueOf).collect(Collectors.joining(" ")));

        int[] marbles4 = {7,3,1};
        System.out.println(Arrays.stream(solution(marbles4)).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
    }

}
