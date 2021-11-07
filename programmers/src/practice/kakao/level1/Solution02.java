/*
https://programmers.co.kr/learn/courses/30/lessons/42889
 */
package practice.kakao.level1;

import java.util.*;
import java.util.stream.Collectors;

public class Solution02 {

    public static int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        Integer[] success = new Integer[N+2];
        for(int i=0; i<=N+1; i++) success[i] = 0;

        Map<Integer, Double> stageMap = new TreeMap<>();
        for(int stage : stages) {
            for(int i=1; i<=stage; i++) success[i]++;
            if(stage > N) continue;
            stageMap.put(stage, stageMap.getOrDefault(stage, 0.0) + 1);
        }

        for(int i=1; i<=N; i++) {
            if(success[i] == 0) stageMap.put(i, 0.0);
            else stageMap.put(i, stageMap.getOrDefault(i, 0.0) / success[i]);

        }

        List<Map.Entry<Integer, Double>> entries = new LinkedList<>(stageMap.entrySet());
        entries.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));

        int index = 0;
        for (Map.Entry<Integer, Double> entry : entries) {
            answer[index++] = entry.getKey();
        }
        return answer;
    }

    public static void main(String[] args) {
        int N1 = 5;
        int[] stages1 = {2,1,2,6,2,4,3,3};
        System.out.println(Arrays.stream(solution(N1, stages1)).mapToObj(String::valueOf).collect(Collectors.joining(",")));

        int N2 = 4;
        int[] stages2 = {4,4,4,4,4};
        System.out.println(Arrays.stream(solution(N2, stages2)).mapToObj(String::valueOf).collect(Collectors.joining(",")));
    }

}
