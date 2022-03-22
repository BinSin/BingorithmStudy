/*
양궁대회
https://programmers.co.kr/learn/courses/30/lessons/92342
 */

package practice.kakao.level2;

import java.util.Arrays;

public class Solution21 {

    private static int[] answer;
    private static int[] score;
    private static int max;

    private static boolean check(int[] info) {
        int lion = 0, apeach = 0;
        for (int i=0; i<=10; i++) {
            if (info[i] == 0 && score[i] == 0) continue;
            if (score[i] > info[i]) lion += 10 - i;
            else apeach += 10 - i;
        }
        if (lion <= apeach) return false;

        if (lion - apeach >= max) {
            max = lion - apeach;
            answer = score.clone();
            return true;
        } else {
            return false;
        }
    }

    private static void dfs(int depth, int n, int[] info) {
        if (depth == n) {
            check(info);
            return;
        }
        for (int i=0; i<=10 && score[i] <= info[i]; i++) {
            score[i]++;
            dfs(depth + 1, n, info);
            score[i]--;
        }
    }

    public static int[] solution(int n, int[] info) {
        answer = new int[] {-1};
        score = new int[11];
        max = -100;

        dfs(0, n, info);
        return answer;
    }

    public static void main(String[] args) {
        int n1 = 5;
        int[] info1 = {2,1,1,1,0,0,0,0,0,0,0};
        System.out.println(Arrays.toString(solution(n1, info1)));

        int n2 = 1;
        int[] info2 = {1,0,0,0,0,0,0,0,0,0,0};
        System.out.println(Arrays.toString(solution(n2, info2)));

        int n3 = 9;
        int[] info3 = {0,0,1,2,0,1,1,1,1,1,1};
        System.out.println(Arrays.toString(solution(n3, info3)));

        int n4 = 10;
        int[] info4 = {0,0,0,0,0,0,0,0,3,4,3};
        System.out.println(Arrays.toString(solution(n4, info4)));
    }

}
