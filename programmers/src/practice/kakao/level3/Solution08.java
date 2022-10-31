/*
BinSin
https://school.programmers.co.kr/learn/courses/30/lessons/72413
분류: 다익스트라 알고리즘
 */

package practice.kakao.level3;

import java.util.*;

public class Solution08 {

    private static final int MAX_FARE = 100000 * 200 + 1;

    private static class Node implements Comparable<Node> {
        int index, fare;

        Node(int index, int fare) {
            this.index = index;
            this.fare = fare;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.fare, o.fare);
        }
    }

    private static void dijkstra(int s, int[] dist, List<List<Node>> graph) {
        Queue<Node> q = new PriorityQueue<>();
        q.add(new Node(s, 0)); // 시작 정점
        dist[s] = 0;

        while (!q.isEmpty()) {
            Node node = q.remove();

            if (dist[node.index] < node.fare) continue;

            List<Node> aroundNodeList = graph.get(node.index);
            for (Node nextNode : aroundNodeList) {
                if (dist[nextNode.index] <= node.fare + nextNode.fare) continue;

                dist[nextNode.index] = node.fare + nextNode.fare;
                q.add(new Node(nextNode.index, dist[nextNode.index]));
            }
        }
    }

    public static int solution(int n, int s, int a, int b, int[][] fares) {
        List<List<Node>> graph = new ArrayList<>();
        for (int i=0; i<=n; i++) graph.add(new ArrayList<>(i));
        for (int[] fare : fares) {
            int start = fare[0];
            int end = fare[1];
            int cost = fare[2];
            graph.get(start).add(new Node(end, cost));
            graph.get(end).add(new Node(start, cost));
        }

        int[] dist = new int[n+1]; // 정점 카운터
        int[] distA = new int[n+1];
        int[] distB = new int[n+1];

        Arrays.fill(dist, MAX_FARE);
        Arrays.fill(distA, MAX_FARE);
        Arrays.fill(distB, MAX_FARE);

        dijkstra(s, dist, graph);
        dijkstra(a, distA, graph);
        dijkstra(b, distB, graph);

        int answer = Integer.MAX_VALUE;
        for (int i=1; i<=n; i++) answer = Math.min(answer, dist[i] + distA[i] + distB[i]);
        return answer;
    }

    public static void main(String[] args) {
        int n = 6, s = 4, a = 6, b = 2;
        int[][] fares = {{4,1,10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}};
        System.out.println(solution(n, s, a, b, fares));
    }

}
