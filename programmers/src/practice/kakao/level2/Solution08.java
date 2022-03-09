/*
거리두기 확인하기
https://programmers.co.kr/learn/courses/30/lessons/81302
 */

package practice.kakao.level2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution08 {
    public static Pair[] next = {new Pair(0, 1), new Pair(1, 0), new Pair(0, -1), new Pair(-1, 0)};

    static class Pair {
        int x, y;
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int check(String[] place) {
        char[][] map = new char[5][5];
        Queue<Pair> people = new LinkedList<>();

        for(int i=0; i<5; i++) {
            char[] p = place[i].toCharArray();
            for(int j=0; j<5; j++) {
                char c = p[j];
                map[i][j] = c;
                if(c == 'P') {
                    people.add(new Pair(i, j));
                }
            }
        }

        if(people.isEmpty()) return 1;

        while(!people.isEmpty()) {
            Queue<Pair> q = new LinkedList<>();
            int[][] dist = new int[5][5];

            Pair startP = people.poll();
            q.add(startP);
            dist[startP.x][startP.y] = -1;
            while (!q.isEmpty()) {
                Pair curP = q.poll();
                int curX = curP.x;
                int curY = curP.y;
                int curDist = dist[curX][curY];

                for (Pair n : next) {
                    int nextX = curX + n.x;
                    int nextY = curY + n.y;
                    if (nextX < 0 || nextX > 4 || nextY < 0 || nextY > 4) continue;

                    int nextDist  = dist[nextX][nextY];
                    if (nextDist != 0) continue;

                    if (map[nextX][nextY] == 'X') continue;
                    else if (map[nextX][nextY] == 'P') {
                        if (curDist == -1 || curDist == 1) return 0;
                        else continue;
                    }

                    dist[nextX][nextY] = curDist == -1 ? 1 : curDist + 1;
                    q.add(new Pair(nextX, nextY));
                }
            }
        }
        return 1;
    }

    public static int[] solution(String[][] places) {
        int[] answer = new int[5];
        for(int i=0; i<5; i++) {
            String[] place = places[i];
            answer[i] = check(place);
        }
        return answer;
    }

    public static void main(String[] args) {
        String[][] places = {
                {"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
                {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
                {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
                {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
                {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}
        };

        Arrays.stream(solution(places)).forEach(System.out::println);
    }
}
