/*
BinSin
https://school.programmers.co.kr/learn/courses/30/lessons/67259
분류: BFS
 */

package practice.kakao.level3;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Solution10 {

    private static final Point[] next = { new Point(0, -1, 0), new Point(-1, 0, 1), new Point(0, 1, 2), new Point(1, 0, 3) };

    private static class Point {
        int x, y, direction;

        public Point(int x, int y, int direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }
    }

    private static int getCost(int[][] board, int[][][] costMap, int rowLength, int colLength) {
        for (int i=0; i<rowLength; i++) {
            for (int j=0; j<colLength; j++) {
                Arrays.fill(costMap[i][j], Integer.MAX_VALUE);
            }
        }

        Deque<Point> q = new ArrayDeque<>();

        if (board[1][0] == 0) {
            q.add(new Point(1, 0, 3));
            costMap[1][0][3] = 100;
        }

        if (board[0][1] == 0) {
            q.add(new Point(0, 1, 2));
            costMap[0][1][2] = 100;
        }

        while (!q.isEmpty()) {
            Point p = q.remove();
            int currentDirection = p.direction;

            for (Point n : next) {
                int nx = p.x + n.x;
                int ny = p.y + n.y;
                int nd = n.direction;

                if (nx == 0 && ny == 0) continue;
                if (nx < 0 || nx >= rowLength || ny < 0 || ny >= colLength) continue;
                if (board[nx][ny] == 1) continue;

                int cost = 100;
                if (currentDirection != n.direction) {
                    cost += 500;
                }

                int nextCost = costMap[p.x][p.y][p.direction] + cost;
                if (costMap[nx][ny][nd] <= nextCost) continue;
                costMap[nx][ny][nd] = nextCost;
                q.add(new Point(nx, ny, nd));
            }
        }

        return Arrays.stream(costMap[rowLength - 1][colLength - 1]).min().getAsInt();
    }

    public static int solution(int[][] board) {
        int rowLength = board.length;
        int colLength = board[0].length;

        int[][][] costMap = new int[rowLength][colLength][4];
        return getCost(board, costMap, rowLength, colLength);
    }

    public static void main(String[] args) {
        int[][] board1 = {{0,0,0},{0,0,0},{0,0,0}};
        System.out.println(solution(board1));

        int[][] board2 = {{0,0,0,0,0,0,0,1},{0,0,0,0,0,0,0,0},{0,0,0,0,0,1,0,0},{0,0,0,0,1,0,0,0},{0,0,0,1,0,0,0,1},{0,0,1,0,0,0,1,0},{0,1,0,0,0,1,0,0},{1,0,0,0,0,0,0,0}};
        System.out.println(solution(board2));

        int[][] board3 = {{0,0,1,0},{0,0,0,0},{0,1,0,1},{1,0,0,0}};
        System.out.println(solution(board3));

        int[][] board4 = {{0,0,0,0,0,0},{0,1,1,1,1,0},{0,0,1,0,0,0},{1,0,0,1,0,1},{0,1,0,0,0,1},{0,0,0,0,0,0}};
        System.out.println(solution(board4));

        int[][] board5 = {{0, 0, 0, 0, 0}, {0, 1, 1, 1, 0}, {0, 0, 1, 0, 0}, {1, 0, 0, 0, 1}, {0, 1, 1, 0, 0}};
        System.out.println(solution(board5));
    }
}
