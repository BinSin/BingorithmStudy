package exam.programmers_dev_2022_04_02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution01 {

    private static int[] reverse(int[] ans) {
        int len = ans.length;
        for (int i=0; i<len/2; i++) {
            int tmp = ans[i];
            ans[i] = ans[len - i - 1];
            ans[len - i - 1] = tmp;
        }
        return ans;
    }

    public static int[][] solution(int[][] dist) {
        int n = dist.length;
        int[][] answer = new int[2][n];

        int[] d = dist[0]; // 0을 기준으로 위치 지정
        int base = d[1]; // 1을 대상으로 거리 비교
        List<Integer> dList = new ArrayList<>(List.of(0, base)); // 여기에 그대로 저장해논 뒤 나중에 indexOf 메서드로 순서를 찾는다.
        for (int i=2; i<n; i++) {
            int next = d[i];
            int compare = dist[1][i]; // 실제 두 거리의 차이

            if (Math.abs(base + next) == compare) { // 합이 실제 두 거리의 차이일 경우 반대 방향
                d[i] = next * -1;
                dList.add(next * -1);
            } else { // 합이 실제 두 거리의 합일 경우, 같은 방향
                dList.add(next);
            }
        }

        List<Integer> list = new ArrayList<>(); // 올바른 인덱스를 이곳에 저장
        Arrays.stream(d).sorted().forEach(e -> list.add(dList.indexOf(e))); // stream 으로 sort 하고 dList를 통하여 인덱스를 list에 저장

        answer[0] = list.stream().mapToInt(Integer::intValue).toArray();
        answer[1] = reverse(answer[0].clone()); // answer 의 반대는 항상 참

        if (answer[0][0] > answer[1][0]) { // 오름차순으로 되기 위해, 내림차순의 경우 위치 바꾸기
            int[] tmp = answer[0];
            answer[0] = answer[1];
            answer[1] = tmp;
        }

        return answer;
    }



    public static void main(String[] args) {
        int[][] dist1 = {{0,5,2,4,1},{5,0,3,9,6},{2,3,0,6,3},{4,9,6,0,3},{1,6,3,3,0}};
        Arrays.stream(solution(dist1)).forEach(e -> System.out.println(Arrays.toString(e)));

        int[][] dist2 = {{0,2,3,1},{2,0,1,1},{3,1,0,2},{1,1,2,0}};
        Arrays.stream(solution(dist2)).forEach(e -> System.out.println(Arrays.toString(e)));
    }
}
