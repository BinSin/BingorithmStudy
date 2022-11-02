package practice.kakao.level3;

public class Solution09 {

    private static final int MAX_STONE_VALUE = 200001;

    public static int solution(int[] stones, int k) {
        int length = stones.length;
        int min = MAX_STONE_VALUE;
        for (int i=0; i<length - k + 1; i++) {
            int max = 0;
            for (int j=i; j<i+k; j++) max = Math.max(max, stones[j]);
            min = Math.min(min, max);
        }
        return min;
    }

    public static void main(String[] args) {
        int[] stones = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
        int k = 3;
        System.out.println(solution(stones, k));
    }

}
