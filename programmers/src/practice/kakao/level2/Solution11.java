/*
순위 검색
https://programmers.co.kr/learn/courses/30/lessons/72412
 */

package practice.kakao.level2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Solution11 {

    private static int binarySearch(List<Integer> list, int score) {
        int size = list.size();
        int start = 0;
        int end = size;
        while (start < end) {
            int mid = (start + end) / 2;
            if (score <= list.get(mid)) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return size - start;
    }

    public static int[] solution(String[] info, String[] query) {
        Map<String, List<Integer>> map = new HashMap<>();

        for (String i : info) {
            StringTokenizer st = new StringTokenizer(i);
            String[] lang = { st.nextToken(), "-" };
            String[] job = { st.nextToken(), "-" };
            String[] career = { st.nextToken(), "-" };
            String[] food = { st.nextToken(), "-" };
            int score = Integer.parseInt(st.nextToken());

            for (String l : lang) {
                for (String j : job) {
                    for (String c : career) {
                        for (String f : food) {
                            String[] arr = { l, j, c, f };
                            String str = String.join(" ", arr);
                            List<Integer> list = map.computeIfAbsent(str, k -> new ArrayList<>());
                            list.add(score);
                        }
                    }
                }
            }
        }

        map.forEach((k, v) -> Collections.sort(v));

        int[] answer = new int[query.length];
        for (int i=0; i<query.length; i++) {
            String q = query[i];
            q = q.replaceAll("and", "");
            StringTokenizer st = new StringTokenizer(q);
            String str = String.join(" ", new String[] { st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken() } );
            int score = Integer.parseInt(st.nextToken());

            List<Integer> list = map.getOrDefault(str, new ArrayList<>());
            answer[i] = binarySearch(list, score);
        }

        return answer;
    }

    public static void main(String[] args) {
        String[] info = {"java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"};
        String[] query = {"java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"};
        Arrays.stream(solution(info, query)).mapToObj(v -> v + " ").forEach(System.out::print);
    }
}
