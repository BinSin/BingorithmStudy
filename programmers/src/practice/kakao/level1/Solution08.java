/*
BinSin
https://school.programmers.co.kr/learn/courses/30/lessons/77484?language=java
 */

package practice.kakao.level1;

import java.util.*;
import java.util.stream.Collectors;

public class Solution08 {

    public static int[] solution(int[] lottos, int[] win_nums) {
        Set<Integer> winNumList = Arrays.stream(win_nums).boxed().collect(Collectors.toSet());

        int lottoCount = 7;
        int zeroCount = 0;
        for (int lotto : lottos) {
            if (lotto == 0) {
                zeroCount++;
                continue;
            }

            if (!winNumList.add(lotto)) {
                lottoCount--;
            }
        }

        int high = lottoCount - zeroCount == 7 ? 6 : lottoCount - zeroCount;
        int low = lottoCount == 7 ? 6 : lottoCount;

        return new int[] {high, low};
    }

    public static void main(String[] args) {
        int[] lottes1 = {44, 1, 0, 0, 31, 25};
        int[] win_nums1 = {31, 10, 45, 1, 6, 19};
        System.out.println(Arrays.stream(solution(lottes1, win_nums1)).mapToObj(String::valueOf).collect(Collectors.joining(" ")));

        int[] lottes2 = {0, 0, 0, 0, 0, 0};
        int[] win_nums2 = {38, 19, 20, 40, 15, 25};
        System.out.println(Arrays.stream(solution(lottes2, win_nums2)).mapToObj(String::valueOf).collect(Collectors.joining(" ")));

        int[] lottes3 = {45, 4, 35, 20, 3, 9};
        int[] win_nums3 = {20, 9, 3, 45, 4, 35};
        System.out.println(Arrays.stream(solution(lottes3, win_nums3)).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
    }
}
