/*
가장 먼 노드
https://programmers.co.kr/learn/courses/30/lessons/49189
 */

package practice.kakao.level3;

import java.util.*;

public class Solution01 {

    public static int solution(int n, int[][] edge) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i=1; i<=n; i++) map.put(i, new ArrayList<>());

        for(int[] e : edge) {
            int s = e[0];
            int d = e[1];
            map.get(s).add(d);
            map.get(d).add(s);
        }

        Queue<Integer> q = new LinkedList<>();
        int[] count = new int[n+1];
        count[1] = -1;
        q.add(1);
        while(!q.isEmpty()) {
            int cur = q.poll();
            List<Integer> list = map.get(cur);
            list.forEach(next -> {
                if (count[next] == 0) {
                    q.add(next);
                    if(cur == 1) count[next] = 1; // 제일 처음 출발할 때 해당 로직 타도록 설정
                    else count[next] = count[cur] + 1;
                }
            });
        }

        int max = Arrays.stream(count).max().getAsInt();
        return (int) Arrays.stream(count).filter(e -> e == max).count();
    }

    public static void main(String[] args) {
        int n = 6;
        int[][] vertex = {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};
        System.out.println(solution(n, vertex));
    }
}
