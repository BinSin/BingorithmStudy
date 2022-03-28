/*
추석 트래픽
https://programmers.co.kr/learn/courses/30/lessons/17676
 */

package practice.kakao.level3;

import java.util.*;

public class Solution02 {

    private static final int lastTime = 1000*24*60*60;

    public static int solution(String[] lines) {
        int answer = 0;
        int[] count = new int[lastTime];

        for (String line : lines) {
            StringTokenizer st = new StringTokenizer(line);
            st.nextToken();
            String S = st.nextToken();

            StringTokenizer st2 = new StringTokenizer(S, ":");
            int hour = Integer.parseInt(st2.nextToken());
            int minutes = Integer.parseInt(st2.nextToken());

            String secondStr = st2.nextToken();
            StringTokenizer st3 = new StringTokenizer(secondStr, ".");
            int second = Integer.parseInt(st3.nextToken());
            int millisecond = Integer.parseInt(st3.nextToken());

            int milliseconds = hour * 60 * 60 * 1000 + minutes * 60 * 1000 + second * 1000 + millisecond;

            String T = st.nextToken();
            StringBuilder sb = new StringBuilder(T.replace("s", "").replace(".", ""));
            while(sb.length() != 4) {
                sb.append("0");
            }

            int diffMilliseconds = Integer.parseInt(sb.toString());
            int startSeconds = milliseconds - diffMilliseconds - 998;

            for (int i=startSeconds; i<=milliseconds; i++) {
                if (i >= 0) {
                    count[i]++;
                    answer = Math.max(count[i], answer);
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        String[] lines1 = {"2016-09-15 01:00:04.001 2.0s", "2016-09-15 01:00:07.000 2s"};
        System.out.println(solution(lines1));

        String[] lines2 = {"2016-09-15 01:00:04.002 2.0s", "2016-09-15 01:00:07.000 2s"};
        System.out.println(solution(lines2));

        String[] lines3 = {"2016-09-15 20:59:57.421 0.351s",
                "2016-09-15 20:59:58.233 1.181s",
                "2016-09-15 20:59:58.299 0.8s",
                "2016-09-15 20:59:58.688 1.041s",
                "2016-09-15 20:59:59.591 1.412s",
                "2016-09-15 21:00:00.464 1.466s",
                "2016-09-15 21:00:00.741 1.581s",
                "2016-09-15 21:00:00.748 2.31s",
                "2016-09-15 21:00:00.966 0.381s",
                "2016-09-15 21:00:02.066 2.62s"};
        System.out.println(solution(lines3));
    }

}
