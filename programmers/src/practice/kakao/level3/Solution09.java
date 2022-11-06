/*
BinSin
https://school.programmers.co.kr/learn/courses/30/lessons/64062
분류: 이분 탐색
 */

package practice.kakao.level3;

public class Solution09 {

    private static final int MAX_STONE_VALUE = 200000001;

    private static boolean canCrossBride(int[] stones, int k, int countOfFriends) {
        int count = 0;
        for (int stone : stones) {
            if (stone - countOfFriends >= 0) count = 0; // 건널 수 있음
            else count++; // 건널 수 없음

            if (count == k) return false;
        }
        return true;
    }

    public static int solution(int[] stones, int k) {
        int min = 1;
        int max = MAX_STONE_VALUE;

        int answer = 0;
        while(min <= max) {
            int mid = (min + max) / 2;
            if (canCrossBride(stones ,k, mid)) {
                answer = Math.max(answer, mid);
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] stones = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
        int k = 3;
        System.out.println(solution(stones, k));
    }

}
