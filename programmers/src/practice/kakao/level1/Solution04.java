package practice.kakao.level1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Solution04 { // 신고 결과 받기

    public static int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        Map<String, List<String>> sendMap = new HashMap<>(); // key: 신고한 유저 , value: 신고당한 유저 리스트
        Map<String, Integer> badUserMap = new HashMap<>(); // key: 신고당한 유저, value: 신고당한 횟수

        String[] distinctReport = Arrays.stream(report).distinct().collect(Collectors.toList()).toArray(String[]::new);
        for (String str : distinctReport) {
            StringTokenizer st = new StringTokenizer(str);
            String reporter = st.nextToken();
            String reported = st.nextToken();

            if(Objects.isNull(sendMap.get(reporter))) {
                sendMap.put(reporter, new ArrayList<>());
            }
            List<String> list = sendMap.get(reporter);
            list.add(reported);

            badUserMap.put(reported, badUserMap.getOrDefault(reported, 0) + 1);
        }

        List<String> badUserList = new ArrayList<>(); // 정지된 유저
        for (String id : id_list) {
            if(Objects.isNull(badUserMap.get(id))) continue;
            if(badUserMap.get(id) >= k) {
                badUserList.add(id);
            }
        }

        int index = 0;
        for (String id : id_list) {
            if(Objects.isNull(sendMap.get(id))) continue;
            List<String> sendList = sendMap.get(id);

            int count = (int) sendList.stream().filter(badUserList::contains).count();
            answer[index++] = count;
        }

        return answer;
    }

    public static void main(String[] args) {
        String[] id_list1 = {"muzi", "frodo", "apeach", "neo"};
        String[] report1 = {"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"};
        int k1 = 2;
        Arrays.stream(solution(id_list1, report1, k1)).mapToObj(e -> e + " ").forEach(System.out::print); // 2,1,1,0
        System.out.println();

        String[] id_list2 = {"con", "ryan"};
        String[] report2 = {"ryan con", "ryan con", "ryan con", "ryan con"};
        int k2 = 3;
        Arrays.stream(solution(id_list2, report2, k2)).mapToObj(e -> e + " ").forEach(System.out::print); // 0,0
        System.out.println();

        String[] id_list3 = {"muzi", "frodo", "apeach", "neo"};
        String[] report3 = {"muzi frodo", "frodo muzi", "apeach neo", "neo apeach"};
        int k3 = 1;
        Arrays.stream(solution(id_list3, report3, k3)).mapToObj(e -> e + " ").forEach(System.out::print); // 0,0

    }

}
