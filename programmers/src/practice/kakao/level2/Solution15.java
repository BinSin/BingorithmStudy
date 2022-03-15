/*
방금그곡
https://programmers.co.kr/learn/courses/30/lessons/17683
 */

package practice.kakao.level2;

import java.util.StringTokenizer;

public class Solution15 {

    public static String convert(String m) {
        StringBuilder sb = new StringBuilder();
        int len = m.length();
        for (int i=0; i<len-1; i++) {
            char c = m.charAt(i);
            char nextC = m.charAt(i+1);

            if (nextC == '#') {
                i++;
                sb.append((char)(c + 32));
            } else {
                sb.append(c);
            }
        }

        char lastC = m.charAt(len-1);
        if(lastC != '#') sb.append(lastC);

        return sb.toString();
    }

    public static String solution(String m, String[] musicinfos) {
        String answer = "";

        m = convert(m);

        int maxTime = 0;
        for (String music : musicinfos) {
            StringTokenizer st = new StringTokenizer(music, ",");
            String start = st.nextToken();
            String[] startArr = start.split(":");
            int startTime = Integer.parseInt(startArr[0]) * 60 + Integer.parseInt(startArr[1]);

            String end = st.nextToken();
            String[] endArr = end.split(":");
            int endTime = Integer.parseInt(endArr[0]) * 60 + Integer.parseInt(endArr[1]);

            int diffTime = endTime - startTime;

            String title = st.nextToken();
            String melody = convert(st.nextToken());

            int melodyLen = melody.length();
            if (melodyLen > diffTime) {
                melody = melody.substring(0, diffTime);
            } else {
                StringBuilder sb = new StringBuilder();
                int mul = diffTime / melodyLen;
                int sub = diffTime % melodyLen;

                sb.append(melody.repeat(mul));
                sb.append(melody, 0, sub);

                melody = sb.toString();
            }

            if (melody.contains(m)) {
                if (maxTime < diffTime) {
                    maxTime = diffTime;
                    answer = title;
                }
            }
        }

        if(answer.equals("")) return "(None)";
        else return answer;
    }

    public static void main(String[] args) {
        String m1 = "ABCDEFG";
        String[] musicinfos1 = {"12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"};
        System.out.println(solution(m1, musicinfos1));

        String m2 = "CC#BCC#BCC#BCC#B";
        String[] musicinfos2 = {"03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B"};
        System.out.println(solution(m2, musicinfos2));

        String m3 = "ABC";
        String[] musicinfos3 = {"12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"};
        System.out.println(solution(m3, musicinfos3));
    }

}
