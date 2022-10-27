/*
BinSin
https://school.programmers.co.kr/learn/courses/30/lessons/67258
분류: 투 포인터??
풀이: https://tech.kakao.com/2020/07/01/2020-internship-test/
 */

package practice.kakao.level3;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Solution07 {

    public static int[] solution(String[] gems) {
        int[] answer = new int[2];
        int totalCount = (int) Arrays.stream(gems).distinct().count();

        int start = 0, end = totalCount - 1;

        int min = gems.length + 1;
        while (start <= end) {
            if (end == gems.length) break;

            Set<String> set = new HashSet<>();
            for (int i=start; i<=end; i++) {
                set.add(gems[i]);
            }

            int currentSize = set.size();
            if (totalCount == currentSize) {
                int range = end - start + 1;
                if (range < min) {
                    min = range;
                    answer = new int[] {start + 1, end + 1};
                }
                start++;
            } else {
                end++;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        String[][] gems = {{"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"}, {"AA", "AB", "AC", "AA", "AC"}, {"XYZ", "XYZ", "XYZ"}, {"ZZZ", "YYY", "NNNN", "YYY", "BBB"}};
        for (int i=0; i<4; i++) System.out.println(Arrays.stream(solution(gems[i])).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
    }

}
