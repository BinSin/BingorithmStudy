/*
메뉴 리뉴얼
https://programmers.co.kr/learn/courses/30/lessons/72411
 */

package practice.kakao.level2;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Solution05 {

    private static List<String> answerList = new ArrayList<>();
    private static Map<String, Integer> map;
    private static boolean[] visited;
    private static char[] answer;

    public static void combination(int depth, int c, int n, char[] order) {
        if (depth == c) {
            String ans = String.valueOf(answer);
            map.put(ans, map.getOrDefault(ans, 0) + 1);
            return;
        }
        for (int i=n; i<order.length; i++) {
            if(!visited[i]) {
                visited[i] = true;
                answer[depth] = order[i];
                combination(depth + 1, c, i + 1, order);
                visited[i] = false;
            }
        }
    }

    public static String[] solution(String[] orders, int[] course) {
        for(int c : course) {
            map = new HashMap<>();
            for(String order : orders) {
                char[] sortOrder = order.toCharArray();
                Arrays.sort(sortOrder);
                int len = order.length();
                visited = new boolean[len];
                answer = new char[c];
                combination(0, c, 0, sortOrder);
            }

            AtomicInteger max = new AtomicInteger();
            map.forEach((k, v) -> {
                if(v > 1) max.set(Math.max(max.get(), v));
            });

            map.forEach((k, v) -> {
                if(v == max.get()) answerList.add(k);
            });
        }
        Collections.sort(answerList);
        return answerList.toArray(String[]::new);
    }

    public static void main(String[] args) {
        String[] orders1 = new String[] {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
        int[] course1 = {2,3,4};
        Arrays.stream(solution(orders1, course1)).map(e -> e + " ").forEach(System.out::print);
        System.out.println();
        answerList.clear();

        String[] orders2 = new String[] {"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"};
        int[] course2 = {2,3,5};
        Arrays.stream(solution(orders2, course2)).map(e -> e + " ").forEach(System.out::print);
        System.out.println();
        answerList.clear();

        String[] orders3 = new String[] {"XYZ", "XWY", "WXA"};
        int[] course3 = {2,3,4};
        Arrays.stream(solution(orders3, course3)).map(e -> e + " ").forEach(System.out::print);
    }

}
