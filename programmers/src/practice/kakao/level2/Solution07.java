/*
뉴스 클러스터링
https://programmers.co.kr/learn/courses/30/lessons/17677
 */

package practice.kakao.level2;

import java.util.HashMap;
import java.util.Map;

public class Solution07 {

    public static Integer union(Map<String, Integer> str1, Map<String, Integer> str2) {
        Map<String, Integer> map = new HashMap<>(str1);
        str2.forEach((k, v) -> map.put(k, Math.max(map.getOrDefault(k, 0), v)));

        return map.values().stream()
            .mapToInt(i -> i)
            .sum();
    }

    public static Integer intersection(Map<String, Integer> str1, Map<String, Integer> str2) {
        return str1.entrySet().stream()
            .filter(e -> str2.containsKey(e.getKey()))
            .map(e -> Math.min(e.getValue(), str2.get(e.getKey())))
            .mapToInt(Integer::intValue)
            .sum();
    }

    public static boolean check(String str) {
        char[] cArr = str.toCharArray();
        for (char c : cArr) {
            if(c < 'a' || c > 'z') return false;
        }
        return true;
    }

    public static int solution(String str1, String str2) {
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        Map<String, Integer> str1List = new HashMap<>();
        for (int i=0; i<str1.length()-1; i++) {
            String str = str1.substring(i, i+2);
            if(check(str)) {
                str1List.put(str, str1List.getOrDefault(str, 0) + 1);
            }
        }

        Map<String, Integer> str2List = new HashMap<>();
        for (int i=0; i<str2.length()-1; i++) {
            String str = str2.substring(i, i+2);
            if(check(str)) {
                str2List.put(str, str2List.getOrDefault(str, 0) + 1);
            }
        }

        int union = union(str1List, str2List);
        int intersection = intersection(str1List, str2List);

        if(union == 0) return 65536 * 1;
        else if(intersection == 0) return 0;
        else return 65536 * intersection / union;
    }

    public static void main(String[] args) {
        String str11 = "FRANCE";
        String str21 = "french";
        System.out.println(solution(str11, str21) == 16384);

        String str12 = "handshake";
        String str22 = "shake hands";
        System.out.println(solution(str12, str22) == 65536);

        String str13 = "aa1+aa2";
        String str23 = "AAAA12";
        System.out.println(solution(str13, str23) == 43690);

        String str14 = "E=M*C^2";
        String str24 = "e=m*c^2";
        System.out.println(solution(str14, str24) == 65536);
    }

}
