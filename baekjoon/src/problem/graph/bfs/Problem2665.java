/*
BinSin
https://www.acmicpc.net/problem/2665
 */

package problem.graph.bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Problem2665 {

  private static Pair[] next = {new Pair(0, -1, 0), new Pair(-1, 0, 0)
      , new Pair(0, 1, 0), new Pair(1, 0, 0)};

  private static class Pair {

    int x;
    int y;
    int value;

    public Pair(int x, int y, int value) {
      this.x = x;
      this.y = y;
      this.value = value;
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n = Integer.parseInt(br.readLine());
    int[][] map = new int[n][n];
    for (int i = 0; i < n; i++) {
      String s = br.readLine();
      for (int j = 0; j < n; j++) {
        map[i][j] = s.charAt(j) - '0';
      }
    }

    int[][] visited = new int[n][n];
    for (int i = 0; i < n; i++) {
      Arrays.fill(visited[i], -1);
    }

    PriorityQueue<Pair> q = new PriorityQueue<>(Comparator.comparingInt(o -> o.value));
    q.add(new Pair(0, 0, 0));
    visited[0][0] = 0;
    while (!q.isEmpty()) {
      Pair p = q.remove();
      int px = p.x;
      int py = p.y;

      for (Pair pn : next) {
        int nextX = px + pn.x;
        int nextY = py + pn.y;
        if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= n) {
          continue;
        }

        if (visited[nextX][nextY] != -1) {
          continue;
        }

        if (map[nextX][nextY] == 1) {
          visited[nextX][nextY] = visited[px][py];
        } else {
          visited[nextX][nextY] = visited[px][py] + 1;
        }
        q.add(new Pair(nextX, nextY, visited[nextX][nextY]));
      }
    }

    bw.write(visited[n - 1][n - 1] + "");
    bw.flush();
    bw.close();
    br.close();
  }

}
