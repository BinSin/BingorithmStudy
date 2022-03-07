/*
뉴스 클러스터링
https://programmers.co.kr/learn/courses/30/lessons/17677
 */

package practice.kakao.level2;

import java.util.HashMap;
import java.util.Map;

public class Solution07 {

    public static Map<String, Integer> union(Map<String, Integer> str1, Map<String, Integer> str2) {
        Map<String, Integer> map = new HashMap<>(str1);
        str2.forEach((k, v) -> map.merge(k, 1, Integer::sum));
        return map;
    }

    public static Map<String, Integer> intersection(Map<String, Integer> str1, Map<String, Integer> str2) {
        Map<String, Integer> map = new HashMap<>(str1);
        str2.forEach((k, v) -> {
//           if(map.get(k) =)
        });
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
        for (int i=0; i<str1.length(); i+=2) {
            String str = str1.substring(i, i+2);
            if(check(str)) {
                str1List.put(str, str1List.getOrDefault(str, 0) + 1);
            }
        }

        Map<String, Integer> str2List = new HashMap<>();
        for (int i=0; i<str2.length(); i+=2) {
            String str = str2.substring(i, i+2);
            if(check(str)) {
                str2List.put(str, str1List.getOrDefault(str, 0) + 1);
            }
        }

        Map<String, Integer> union = union(str1List, str2List);
        int jacquardSimilarity = 65536 * intersection(str1, str2) / union(str1, str2);

        int answer = 0;
        return answer;
    }

    public static void main(String[] args) {
        String str11 = "FRANCE";
        String str21 = "french";
        System.out.println(solution(str11, str21));

        String str12 = "handshake";
        String str22 = "shake hands";
        System.out.println(solution(str12, str22));

        String str13 = "aa1+aa2";
        String str23 = "AAAA12";
        System.out.println(solution(str13, str23));

        String str14 = "E=M*C^2";
        String str24 = "e=m*c^2";
        System.out.println(solution(str14, str24));
    }

}
