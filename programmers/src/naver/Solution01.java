package naver;

import java.util.Arrays;
import java.util.Collections;

public class Solution01 {

    public static int solution(int[] prices, int[] discounts) {
        int answer = 0;

        int pLen = prices.length;
        int dLen = discounts.length;

        Integer[] prices2 = Arrays.stream(prices).boxed().toArray(Integer[]::new);
        Integer[] discounts2 = Arrays.stream(discounts).boxed().toArray(Integer[]::new);

        Arrays.sort(prices2, Collections.reverseOrder());
        Arrays.sort(discounts2, Collections.reverseOrder());

        for (int i = 0; i < dLen; i++) {
            answer += prices2[i] * (100 - discounts2[i]) / 100;
        }

        if(pLen > dLen) {
            for(int i=dLen; i< pLen; i++) {
                answer += prices2[i];
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] prices1 = {13000, 88000, 10000};
        int[] discounts1 = {30, 20};

        int[] prices2 = {32000, 18000, 42500};
        int[] discounts2 = {50, 20, 65};

        int[] prices3 = {100, 100, 100};
        int[] discounts3 = {30};

        System.out.println(solution(prices1, discounts1));
        System.out.println(solution(prices2, discounts2));
        System.out.println(solution(prices3, discounts3));
    }
}
