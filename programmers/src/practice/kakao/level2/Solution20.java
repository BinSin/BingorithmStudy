/*
주차 요금 계산
https://programmers.co.kr/learn/courses/30/lessons/92341
 */

package practice.kakao.level2;

import java.util.*;

public class Solution20 {

    private static final int lastTime = 24 * 60 - 1;

    // 출차 요금 계산
    private static Integer getFee(int sumTime, int basicTime, int basicFee, int unitTime, int unitFee) {
        int fee = basicFee;
        if (sumTime > basicTime) {
            int remainTime = sumTime - basicTime;
            fee += (Math.ceil((double) remainTime / unitTime) * unitFee);
        }
        return fee;
    }

    public static int[] solution(int[] fees, String[] records) {
        Map<String, Integer> answer = new TreeMap<>(String::compareTo);
        Map<String, Integer> map = new HashMap<>();

        int basicTime = fees[0];
        int basicFee = fees[1];
        int unitTime = fees[2];
        int unitFee = fees[3];

        for (String record : records) {
            StringTokenizer st = new StringTokenizer(record);
            String[] timeSplit = st.nextToken().split(":");
            String carNumber = st.nextToken();
            String method = st.nextToken();
            int nowTime = Integer.parseInt(timeSplit[0]) * 60 + Integer.parseInt(timeSplit[1]);

            int time = nowTime;
            if (method.equals("IN")) {
                map.put(carNumber, nowTime);
                time *= -1;
            } else {
                map.put(carNumber, -1);
            }
            answer.put(carNumber, answer.getOrDefault(carNumber, 0) + time);
        }

        // 남은 시간 계산
        for (String carNumber : map.keySet()) {
            int prevTime = map.get(carNumber);
            if(prevTime != -1) {
                answer.put(carNumber, answer.getOrDefault(carNumber, 0) + lastTime);
            }
        }

        for (String carNumber : answer.keySet()) {
            int sumTime = answer.get(carNumber);
            answer.put(carNumber, getFee(sumTime, basicTime, basicFee, unitTime, unitFee));
        }

        return answer.values().stream().mapToInt(i -> i).toArray();
    }


    public static void main(String[] args) {
        int[] fees1 = {180, 5000, 10, 600};
        String[] records1 = {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};
        System.out.println(Arrays.toString(solution(fees1, records1))); // [14600, 34400, 5000]

        int[] fees2 = {120, 0, 60, 591};
        String[] records2 = {"16:00 3961 IN","16:00 0202 IN","18:00 3961 OUT","18:00 0202 OUT","23:58 3961 IN"};
        System.out.println(Arrays.toString(solution(fees2, records2))); // [0, 591]

        int[] fees3 = {1, 461, 1, 10};
        String[] records3 = {"00:00 1234 IN"};
        System.out.println(Arrays.toString(solution(fees3, records3))); // 	[14841]
    }

}