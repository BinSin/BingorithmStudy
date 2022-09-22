/*
BinSin
https://www.acmicpc.net/problem/2468
 */

package problem.graph.bfs;

import java.io.*;
import java.util.*;

public class Problem2468 {

    private static Point[] next = {new Point(0, 1), new Point(1, 0), new Point(0, -1), new Point(-1, 0)};

    private static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        Set<Integer> depthSet = new HashSet<>();
        StringTokenizer st;
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++) {
                int n = Integer.parseInt(st.nextToken());
                map[i][j] = n;
                depthSet.add(n);
            }
        }
        depthSet.add(0); // 깊이가 0일때를 해야 비가 안왔을 경우 1로 출력

        int max = 0;
        for (int depth : depthSet) {
            int count = 0;
            Queue<Point> q = new LinkedList<>();
            boolean[][] visited = new boolean[N][N];
            for (int i=0; i<N; i++) {
                for (int j=0; j<N; j++) {
                    if (visited[i][j]) continue;
                    if (map[i][j] <= depth) continue;
                    q.add(new Point(i, j));
                    visited[i][j] = true;
                    while(!q.isEmpty()) {
                        Point p = q.remove();
                        int px = p.x;
                        int py = p.y;
                        for (Point n : next) {
                            int nx = px + n.x;
                            int ny = py + n.y;
                            if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                            if (visited[nx][ny]) continue;
                            if (map[nx][ny] <= depth) continue;
                            q.add(new Point(nx, ny));
                            visited[nx][ny] = true;
                        }
                    }
                    count++;
                }
                max = Math.max(max, count);
            }
        }
        bw.write(String.valueOf(max));
        bw.flush();
        bw.close();
        br.close();
    }
}
