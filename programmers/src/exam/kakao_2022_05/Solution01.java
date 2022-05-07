package exam.kakao_2022_05;

import java.util.HashMap;
import java.util.Map;

public class Solution01 {

    private final static char[] types = {'R', 'T', 'C', 'F', 'J', 'M', 'A', 'N'};

    private static String solution(String[] survey, int[] choices) {
        StringBuilder answer = new StringBuilder();
        Map<Character, Integer> map = new HashMap<>();
        for (char t : types) map.put(t, 0);

        int length = survey.length;
        for (int i=0; i<length; i++) {
            String s = survey[i];
            int c = choices[i];
            char s1 = s.charAt(0), s2 = s.charAt(1);

            if (c <= 3) {
                map.put(s1, map.get(s1) + (4 - c));
            } else if (c > 4) {
                map.put(s2, map.get(s2) + (c - 4));
            }
        }

        for (int i=0; i<8; i+=2) {
            char s1 = types[i], s2 = types[i+1];
            int value1 = map.get(s1), value2 = map.get(s2);
            if (value1 >= value2) {
                answer.append(s1);
            } else {
                answer.append(s2);
            }
        }

        return answer.toString();
    }

    public static void main(String[] args) {
        String[] survey1 = {"AN", "CF", "MJ", "RT", "NA"};
        int[] choices1 = {5, 3, 2, 7, 5};
        System.out.println(solution(survey1, choices1)); // TCMA

        String[] survey2 = {"TR", "RT", "TR"};
        int[] choices2 = {7, 1, 3};
        System.out.println(solution(survey2, choices2)); // RCJA
    }

}
