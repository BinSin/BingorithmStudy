package exam.kakao_2021_09;

import java.util.*;

public class Solution03 {

    final static int endMintues = 24 * 60 - 1;

    public static int[] solution(int[] fees, String[] records) {
       Map<String, Queue<Integer>> map = new HashMap<>();

        for(int i=0; i< records.length; i++) {
            StringTokenizer st = new StringTokenizer(records[i]);
            String time = st.nextToken();
            StringTokenizer timeSt = new StringTokenizer(time, ":");
            int hours = Integer.parseInt(timeSt.nextToken());
            int minutes = Integer.parseInt(timeSt.nextToken());
            minutes += (hours * 60);

            String carNumber = st.nextToken();

            Queue<Integer> queue = map.getOrDefault(carNumber, new LinkedList<>());
            queue.add(minutes);
            map.put(carNumber, queue);
        }

        List<String> list = new ArrayList<>();
        for (Map.Entry<String, Queue<Integer>> entry : map.entrySet()) {
            String carNumber = entry.getKey();
            list.add(carNumber);
        }
        Collections.sort(list);

        int[] answer = new int[map.size()];
        for(int i=0; i<list.size(); i++) {
            String carNumber = list.get(i);
            Queue<Integer> timeQueue = map.get(carNumber);
            if(timeQueue.size() % 2 == 1) {
                timeQueue.add(endMintues);
            }

            Integer spendTime = 0;
            while(!timeQueue.isEmpty()) {
                Integer inTime = timeQueue.poll();
                Integer outTime = timeQueue.poll();
                spendTime += outTime - inTime;
            }

            Integer billTime = spendTime - fees[0] <= 0 ? 0 : spendTime - fees[0];
            answer[i] = fees[1] + (int)(Math.ceil((double)billTime / (double)fees[2]))  * fees[3];
        }


        return answer;
    }

    public static void main(String[] args) {
        int[] fees1 = new int[] {180, 5000, 10, 600};
        String[] records1 = new String[] {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};
        int[] print1 = solution(fees1, records1);
        for(int i=0; i<print1.length; i++) System.out.print(print1[i] + " ");
        System.out.println();

        int[] fees2 = new int[] {120, 0, 60, 591};
        String[] records2 = new String[] {"16:00 3961 IN","16:00 0202 IN","18:00 3961 OUT","18:00 0202 OUT","23:58 3961 IN"};
        int[] print2 = solution(fees2, records2);
        for(int i=0; i<print2.length; i++) System.out.print(print2[i] + " ");
        System.out.println();

        int[] fees3 = new int[] {1, 461, 1, 10};
        String[] records3 = new String[] {"00:00 1234 IN"};
        int[] print3 = solution(fees3, records3);
        for(int i=0; i<print3.length; i++) System.out.print(print3[i] + " ");
        System.out.println();
    }
}
