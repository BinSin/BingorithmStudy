/*
BinSin
https://school.programmers.co.kr/learn/courses/30/lessons/17678
분류: 날짜
 */

package practice.kakao.level3;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Solution11 {

    public static String solution(int n, int t, int m, String[] timetable) {
        LocalTime lastTime = LocalTime.of(9, 0);
        for (int i=0; i<n-1; i++) {
            lastTime = ChronoUnit.MINUTES.addTo(lastTime, t);
        }

        Map<LocalTime, List<LocalTime>> map = new HashMap<>();
        int count = 0; // 버스 인원
        LocalTime busTime = LocalTime.of(9, 0); // 버스 시간
        Arrays.sort(timetable);
        for (String time : timetable) {
            String[] timeArr = time.split(":");
            LocalTime currentTime = LocalTime.of(Integer.parseInt(timeArr[0]), Integer.parseInt(timeArr[1]));

            // 버스 인원이 꽉 차거나 현재 시간이 버스 시간보다 크면 다음 버스 시간으로 변경
            if (count == m || currentTime.isAfter(busTime)) {
                busTime = busTime.plusMinutes(t);
                count = 0;
            }

            map.computeIfAbsent(busTime, k -> new ArrayList<>());
            map.get(busTime).add(currentTime);
            count++;

            if (busTime.isAfter(lastTime)) break;
        }

        List<LocalTime> lastList = map.getOrDefault(lastTime, new ArrayList<>());
        if (lastList.size() == m) return lastList.get(lastList.size() - 1).minusMinutes(1).toString();
        return lastTime.toString();
    }

    public static void main(String[] args) {
        int n1 = 1, t1 = 1, m1 = 5;
        String[] timetable1 = {"08:00", "08:01", "08:02", "08:03"};
        System.out.println(solution(n1, t1, m1, timetable1));

        int n2 = 2, t2 = 10, m2 = 2;
        String[] timetable2 = {"09:10", "09:09", "08:00"};
        System.out.println(solution(n2, t2, m2, timetable2));

        int n3 = 3, t3 = 1, m3 = 2;
        String[] timetable3 = {"06:00", "23:59", "05:48", "00:01", "00:01"};
        System.out.println(solution(n3, t3, m3, timetable3));

        int n4 = 2, t4 = 1, m4 = 2;
        String[] timetable4 = {"09:00", "09:00", "09:00", "09:00"};
        System.out.println(solution(n4, t4, m4, timetable4));
    }
}
