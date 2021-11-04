/*
https://programmers.co.kr/learn/courses/30/lessons/42888
 */

package practice.kakao.level2;

import java.util.*;

public class Solution02 {

    final static String enterWord = "님이 들어왔습니다.";
    final static String leaveWord = "님이 나갔습니다.";

    public static String[] solution(String[] record) {
        Map<String, String> curNameMap = new HashMap<>();
        Map<String, List<Integer>> prevIdMap = new HashMap<>();
        int len = 0;
        for(int i=0; i< record.length; i++) {
            StringTokenizer st = new StringTokenizer(record[i]);
            String behavior = st.nextToken();
            String id = st.nextToken();
            String name = behavior.equals("Leave") ? null : st.nextToken();

            switch(behavior) {
                case "Enter" ->  {
                    curNameMap.put(id, name);
                    if(prevIdMap.get(id) == null) {
                        List<Integer> list = new ArrayList<>();
                        list.add(i);
                        prevIdMap.put(id, list);
                    } else {
                        List<Integer> list = prevIdMap.get(id);
                        list.add(i);
                    }
                    len++;
                }
                case "Leave" -> {
                    List<Integer> list = prevIdMap.get(id);
                    list.add(i * (-1));
                    len++;
                }
                case "Change" -> {
                    curNameMap.put(id, name);
                }
            }
        }

        String[] tmpAnswer = new String[record.length];
        for (Map.Entry<String, List<Integer>> entry : prevIdMap.entrySet()) {
            String id = entry.getKey();
            List<Integer> indexList = entry.getValue();
            indexList.forEach(index -> {
                String name = curNameMap.get(id);
                if(index < 0) {
                    tmpAnswer[Math.abs(index)] = name + leaveWord;
                } else {
                    tmpAnswer[index] = name + enterWord;
                }
            } );
        }
        return Arrays.stream(tmpAnswer).filter(Objects::nonNull).toArray(String[]::new);
    }

    public static void main(String[] args) {
        String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};
        Arrays.stream(solution(record)).forEach(System.out::println);
    }

}
