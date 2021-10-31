package naver_2021_10;

import java.util.*;

public class Solution01 {

    public static int solution(String[] id_list, int k) {
        int answer = 0;
        Map<String, Integer> idMap = new HashMap<>();
        for(int i=0; i< id_list.length; i++) {
            String ids = id_list[i];
            StringTokenizer idSt = new StringTokenizer(ids);
            Set<String> idSet = new HashSet<>();

            while(idSt.hasMoreTokens()) {
                String id = idSt.nextToken();
                idSet.add(id);
            }

            idSet.forEach(id -> idMap.put(id, idMap.getOrDefault(id, 0) + 1));
        }

        for (Map.Entry<String, Integer> entry : idMap.entrySet()) {
            Integer couponCount = entry.getValue();

            if(couponCount >= k) answer += k;
            else answer += couponCount;
        }

        return answer;
    }

    public static void main(String[] args) {
        String[] id_list1 = new String[] {"A B C D", "A D", "A B D", "B D"};
        int k1 = 2;
        System.out.println(solution(id_list1, k1));

        String[] id_list2 = new String[] {"JAY", "JAY ELLE JAY MAY", "MAY ELLE MAY", "ELLE MAY", "ELLE ELLE ELLE", "MAY"};
        int k2 = 3;
        System.out.println(solution(id_list2, k2));
    }
}
