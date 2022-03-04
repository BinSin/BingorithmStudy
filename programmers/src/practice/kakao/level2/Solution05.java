/*
메뉴 리뉴얼
https://programmers.co.kr/learn/courses/30/lessons/72411
 */

package practice.kakao.level2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Solution05 {

    private static Map<String, Integer> map;
    private static boolean[] visited;
    private static char[] answer;
    private static Character[] menu; // 요리 가능한 메뉴들
    private static int max = 0;

    public static void check(String str, String[] orders) {
        char[] cArr = str.toCharArray();
        for (String order : orders) {
            int count = 0;
            for (char c : cArr) {
                if (order.contains(String.valueOf(c)))
                    count++;
            }
            if (count == str.length()) {
                int nextValue = map.getOrDefault(str, 0) + 1;
                map.put(str, nextValue);
                max = Math.max(nextValue, max);
            }
        };

    }

    public static void combination(int depth, int r, int n, int menuCount, String[] orders) {
        if (depth == r) {
            check(String.valueOf(answer), orders);
            return;
        }
        for (int i=n; i<menuCount; i++) {
            if(!visited[i]) {
                visited[i] = true;
                answer[depth] = menu[i];
                combination(depth + 1, r, i, menuCount, orders);
                visited[i] = false;
            }
        }
    }

    public static String[] solution(String[] orders, int[] course) {
        Set<Character> set = new HashSet<>();
        for(String order : orders) {
            char[] cArr = order.toCharArray();
            for(char c : cArr) set.add(c);
        }
        menu = set.toArray(Character[]::new);

        List<String> finalList = new ArrayList<>();
        int menuCount = menu.length;
        for(int c : course) {
            map = new HashMap<>();
            visited = new boolean[menuCount];
            answer = new char[c];
            max = 0;
            combination(0, c, 0, menuCount, orders);

            map.forEach((k, v) -> {
                if(v > 1 && v == max) {
                    finalList.add(k);
                }
            });
        }
        finalList.sort(String::compareTo);

        return finalList.toArray(String[]::new);
    }

    public static void main(String[] args) {
        String[] orders1 = new String[] {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
        int[] course1 = {2,3,4};
        Arrays.stream(solution(orders1, course1)).map(e -> e + " ").forEach(System.out::print);
        System.out.println();

        String[] orders2 = new String[] {"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"};
        int[] course2 = {2,3,5};
        Arrays.stream(solution(orders2, course2)).map(e -> e + " ").forEach(System.out::print);
        System.out.println();

        String[] orders3 = new String[] {"XYZ", "XWY", "WXA"};
        int[] course3 = {2,3,4};
        Arrays.stream(solution(orders3, course3)).map(e -> e + " ").forEach(System.out::print);
    }

}
