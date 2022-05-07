package exam.programmers_dev_2022_04_02;

import java.util.Arrays;

public class Solution01 {

    private static int[] reverse(int[] ans) {
        int len = ans.length;
        for (int i=0; i<len/2; i++) {
            int tmp = ans[i];
            ans[i] = ans[len/2 - i - 1];
            ans[len/2 - i - 1] = tmp;
        }
        return ans;
    }

    public static int[][] solution(int[][] dist) {
        int[][] answer = {};
        int n = dist.length;

        int[] ans = new int[n];
        for (int i=0; i<n; i++) {
            int base = dist[0][i];
            for (int j=i+1; j<n; j++) {
                int next = dist[i][j];

                if (base > next) {

                } else if (base < next) {

                }
            }
        }

        answer[0] = ans;
        answer[1] = reverse(ans);

        return answer;
    }



    public static void main(String[] args) {
        int[][] dist1 = {{0,5,2,4,1},{5,0,3,9,6},{2,3,0,6,3},{4,9,6,0,3},{1,6,3,3,0}};
        System.out.println(Arrays.toString(solution(dist1)));

        int[][] dist2 = {{0,2,3,1},{2,0,1,1},{3,1,0,},{1,1,2,0}};
        System.out.println(Arrays.toString(solution(dist2)));
    }
}
