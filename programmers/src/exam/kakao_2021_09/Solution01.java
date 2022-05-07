package exam.kakao_2021_09;

import java.util.*;

public class Solution01 {

    public static int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];

        String[] distinctReport = Arrays.stream(report).distinct().toArray(String[]::new);

        Map<String, List<String>> map = new HashMap<>();
        Map<String, Integer> receive = new HashMap<>();
        Map<String, Integer> answerMap = new HashMap<>();
        for(int i=0; i< id_list.length; i++) {
            map.put(id_list[i], new ArrayList<>());
            receive.put(id_list[i], 0);
            answerMap.put(id_list[i], 0);
        }

        for(int i=0; i<distinctReport.length; i++) {
            StringTokenizer st = new StringTokenizer(distinctReport[i], " ");
            String citizen = st.nextToken();
            String suspect = st.nextToken();
            List<String> list = map.get(citizen);
            list.add(suspect);
            receive.put(suspect, receive.get(suspect) + 1);
        }

        List<String> suspectList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : receive.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            if(value >= k) {
                suspectList.add(key);
            }
        }

        suspectList.forEach(suspect -> {
            for (Map.Entry<String, List<String>> entry2 : map.entrySet()) {
                String citizen = entry2.getKey();
                List<String> suspectList2 = entry2.getValue();
                suspectList2.forEach(suspect2 -> {
                    if(suspect2.equals(suspect)) answerMap.put(citizen, answerMap.get(citizen) + 1); // 신고한 사람에게 메일 발송
                });
            }

        });

        for(int i=0; i< id_list.length; i++) {
            String id = id_list[i];
            answer[i] = answerMap.get(id);
        }

        return answer;
    }

    public static void main(String[] args) {
        String[] id_list1 = new String[] {"muzi", "frodo", "apeach", "neo"};
        String[] report1 = new String[] {"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"};
        int k1 = 2;
        Arrays.stream(solution(id_list1, report1, k1)).forEach(System.out::print);

        System.out.println();
        String[] id_list2 = new String[] {"con", "ryan"};
        String[] report2 = new String[] {"ryan con", "ryan con", "ryan con", "ryan con"};
        int k2 = 2;
        Arrays.stream(solution(id_list2, report2, k2)).forEach(System.out::print);
    }
}
