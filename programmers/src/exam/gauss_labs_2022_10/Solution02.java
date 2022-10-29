/*

 */

package exam.gauss_labs_2022_10;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Solution02 {

    private static final Point[] next = {new Point(0, -1), new Point(0, 1), new Point(-1, 0), new Point(1, 0)};

    private static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // 폭탄 범위 3 으로 저장
    private static void drawBomArea(int[][] board, int k, int rowLength, int colLength) {
        Deque<Point> q = new ArrayDeque<>();
        for (int i=0; i<rowLength; i++) {
            for (int j=0; j<colLength; j++) {
                if (board[i][j] == 1) q.add(new Point(i, j));
            }
        }

        while(!q.isEmpty()) {
            Point p = q.pop();
            // 왼쪽
            for (int ny=p.y - 1; ny >= 0 && ny >= p.y - k; ny--) {
                if (board[p.x][ny] == 1 || board[p.x][ny] == 2) break;
                board[p.x][ny] = 3;
            }
            // 오른쪽
            for (int ny=p.y + 1; ny < colLength && ny <= p.y + k; ny++) {
                if (board[p.x][ny] == 1 || board[p.x][ny] == 2) break;
                board[p.x][ny] = 3;
            }
            // 위
            for (int nx=p.x - 1; nx >= 0 && nx>= p.x - k; nx--) {
                if (board[nx][p.y] == 1 || board[nx][p.y] == 2) break;
                board[nx][p.y] = 3;
            }
            // 아래
            for (int nx=p.x + 1; nx < rowLength && nx <= p.x + k; nx++) {
                if (board[nx][p.y] == 1 || board[nx][p.y] == 2) break;
                board[nx][p.y] = 3;
            }
        }
    }

    public static int solution(int[][] board, int k, int ax, int ay) {
        int rowLength = board.length;
        int colLength = board[0].length;
        drawBomArea(board, k, rowLength, colLength);
        if (board[ax][ay] == 0) return 0;

        int[][] visited = new int[rowLength][colLength];
        for (int i=0; i<rowLength; i++) Arrays.fill(visited[i], -1);

        Deque<Point> q = new ArrayDeque<>();
        visited[ax][ay] = 0;
        q.add(new Point(ax, ay));

        while(!q.isEmpty()) {
            Point p = q.pop();

            for (Point n : next) {
                int nx = p.x + n.x;
                int ny = p.y + n.y;
                if (nx < 0 || nx >= rowLength || ny < 0 || ny >= colLength) continue; // 범위 넘어서면 스킵
                if (visited[nx][ny] != -1) continue; // 이미 방문했다면 스킵
                if (board[nx][ny] == 1 || board[nx][ny] == 2) continue; // 벽이거나 폭탄이면 못감
                if (board[nx][ny] == 3) {
                    visited[nx][ny] = visited[p.x][p.y] + 1;
                    q.add(new Point(nx, ny));
                } else if (board[nx][ny] == 0) { // 0 일 때 멈춤
                    return visited[p.x][p.y] + 1;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[][] board1 = {{0,0,1,0,0,0}, {0,2,0,0,0,1}, {0,0,2,1,0,0}, {0,0,0,0,0,0}, {0,0,0,0,1,0}, {0,1,0,0,0,0}};
        int k1 = 2, ax1 = 1, ay1 = 2;
        System.out.println(solution(board1, k1, ax1, ay1));

        int[][] board2 = {{0,0,0,1}, {0,2,0,1}, {2,0,0,1}, {0,2,0,1}};
        int k2 = 2, ax2 = 2, ay2 = 1;
        System.out.println(solution(board2, k2, ax2, ay2) == 5);
    }

}
