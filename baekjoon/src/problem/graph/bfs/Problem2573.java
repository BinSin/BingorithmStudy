/*
BinSin
https://www.acmicpc.net/problem/2573
 */

package problem.graph.bfs;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem2573 {

    private static int N, M;
    private static int[][] map;
    private static final Point[] next = {new Point(0, -1), new Point(0, 1), new Point(-1, 0), new Point(1, 0)};

    private static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static boolean isAllZero() {
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (map[i][j] != 0) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean isAllZero = false;
        int count = 0;
        int year = -1;
        while (count < 2) {
            isAllZero = isAllZero();
            if (isAllZero) break;

            count = 0;
            boolean[][] visited = new boolean[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (visited[i][j]) continue;
                    if (map[i][j] == 0) continue;
                    Queue<Point> q = new LinkedList<>();
                    q.add(new Point(i, j));
                    visited[i][j] = true;

                    while (!q.isEmpty()) {
                        Point p = q.remove();
                        int px = p.x;
                        int py = p.y;
                        for (Point n : next) {
                            int nx = px + n.x;
                            int ny = py + n.y;
                            if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                            if (visited[nx][ny]) continue;

                            if (map[nx][ny] == 0) {
                                if (map[px][py] > 0) {
                                    map[px][py]--;
                                }
                            } else if (map[nx][ny] > 0) {
                                q.add(new Point(nx, ny));
                                visited[nx][ny] = true;
                            }
                        }
                    }
                    count++;
                }
            }
            year++;
        }
        if (isAllZero) bw.write("0");
        else bw.write(String.valueOf(year));

        bw.flush();
        bw.close();
        br.close();
    }
}
