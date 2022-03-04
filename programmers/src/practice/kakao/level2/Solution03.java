/*
카카오 프렌즈 컬러링북
https://programmers.co.kr/learn/courses/30/lessons/1829
 */

package practice.kakao.level2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution03 {

    private static class Pair {
        int x, y;
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static Pair[] nextPair = new Pair[] {new Pair(0, 1), new Pair(1, 0), new Pair(0, -1), new Pair(-1, 0)};

    public static int bfs(int m, int n, int[][] picture, boolean[][] check, int i, int j) {
        int area = 1;
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(i, j));
        check[i][j] = true;

        int curN = picture[i][j];
        while(!queue.isEmpty()) {
            Pair cur = queue.poll();
            int curX = cur.x;
            int curY = cur.y;
            for(Pair next : nextPair) {
                int nextX = curX + next.x;
                int nextY = curY + next.y;
                if(nextX < 0 || nextX >= m || nextY < 0 || nextY >= n) continue;
                if(check[nextX][nextY] || curN != picture[nextX][nextY]) continue;
                check[nextX][nextY] = true;
                area++;
                queue.add(new Pair(nextX, nextY));
            }
        }
        return area;
    }

    public static int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        boolean[][] check = new boolean[m][n];

        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if(!check[i][j] && picture[i][j] != 0) {
                    numberOfArea++;
                    maxSizeOfOneArea = Math.max(maxSizeOfOneArea, bfs(m, n, picture, check, i, j));
                }
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }

    public static void main(String[] args) {
        int m = 6;
        int n = 4;
        int[][] picture = {{1,1,1,0},{1,2,2,0},{1,0,0,1},{0,0,0,1},{0,0,0,3},{0,0,0,3}};
        Arrays.stream(solution(m, n, picture)).forEach(System.out::println);
    }

}
