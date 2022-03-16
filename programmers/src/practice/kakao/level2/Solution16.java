/*
압축
https://programmers.co.kr/learn/courses/30/lessons/17684
 */

package practice.kakao.level2;

import java.util.*;

public class Solution16 {

    public static Map<String, Integer> initMap() {
        Map<String, Integer> map = new HashMap<>();
        for (int i=0; i<26; i++) {
            map.put(String.valueOf((char) ('A' +  i)), i + 1);
        }
        return map;
    }

    public static int[] solution(String msg) {
        List<Integer> list = new ArrayList<>();
        Map<String, Integer> map = initMap();

        int count = 27;
        for (int i=0; i<msg.length(); i++) {
            int next = 1;
            String wc = msg.substring(i, i + next);
            while (map.containsKey(wc)) {
                next++;
                if (i + next > msg.length()) break;
                wc = msg.substring(i, i + next);
            }

            String w;
            if (i + next > msg.length()) {
                w = wc;
            } else {
                w = wc.substring(0, wc.length() - 1);
            }
            list.add(map.get(w));

            if (i + next > msg.length()) break;
            map.put(wc, count++);
            i += w.length() - 1;
        }


        return list.stream().mapToInt(v -> v).toArray();
    }

    public static void main(String[] args) {
        String msg1 = "KAKAO";
        System.out.println(Arrays.toString(solution(msg1)));

        String msg2 = "TOBEORNOTTOBEORTOBEORNOT";
        System.out.println(Arrays.toString(solution(msg2)));

        String msg3 = "ABABABABABABABAB";
        System.out.println(Arrays.toString(solution(msg3)));
    }

}
