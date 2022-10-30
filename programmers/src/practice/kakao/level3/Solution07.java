/*
BinSin
https://school.programmers.co.kr/learn/courses/30/lessons/67258
분류: 투 포인터
풀이: https://tech.kakao.com/2020/07/01/2020-internship-test/
 */

package practice.kakao.level3;

import java.util.*;
import java.util.stream.Collectors;

public class Solution07 {

    public static int[] solution(String[] gems) {
        int totalCount = (int) Arrays.stream(gems).distinct().count();

        Map<String, Integer> map = new HashMap<>(); // 보석 갯수 저장
        Deque<String> q = new ArrayDeque<>(); // 해당 구간 보석 이름 저장

        // 초기 세팅
        int range = 0, start = 0, end = 0;
        int index = 0;
        while (map.size() != totalCount) {
            String gem = gems[index++];
            q.add(gem);
            map.put(gem, map.getOrDefault(gem, 0) + 1);

            range++;
            end++;
        }
        int[] answer = new int[] {start + 1, end};

        int min = range + 1;
        while (end <= gems.length) {
            int currentSize = map.size();
            if (totalCount == currentSize) {
                range = end - start + 1;
                if (range < min) {
                    min = range;
                    answer = new int[] {start + 1, end};

                    if (min == totalCount) return answer; // 정답이 최소 단위일 때 바로 return
                }

                String currentGem = q.pop();
                if (map.get(currentGem) == 1) map.remove(currentGem);
                else map.put(currentGem, map.getOrDefault(currentGem, 0) - 1);
                start++;
            } else {
                if (end == gems.length) break;

                String nextGem = gems[end];
                map.put(nextGem, map.getOrDefault(nextGem, 0) + 1);
                q.add(nextGem);
                end++;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        String[][] gems = {{"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"}, {"AA", "AB", "AC", "AA", "AC"}, {"XYZ", "XYZ", "XYZ"}, {"ZZZ", "YYY", "NNNN", "YYY", "BBB"}};
        for (int i=0; i<4; i++) System.out.println(Arrays.stream(solution(gems[i])).mapToObj(String::valueOf).collect(Collectors.joining(" ")));

        String[] gems2 = {"A", "A", "B"};
        System.out.println(Arrays.stream(solution(gems2)).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
    }

}
