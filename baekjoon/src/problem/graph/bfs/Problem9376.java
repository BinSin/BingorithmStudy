/*
BinSin
https://www.acmicpc.net/problem/9376
 */

package problem.graph.bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Problem9376 {

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

    int t = Integer.parseInt(br.readLine());
    while (t-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int h = Integer.parseInt(st.nextToken());
      int w = Integer.parseInt(st.nextToken());

      int answer = 0;
      List<Pair> prisonerLocations = new ArrayList<>();
      char[][] map = new char[h][w];
      int[][] visited = new int[h][w];

      for (int i = 0; i < h; i++) {
        String s = br.readLine();
        for (int j = 0; j < w; j++) {
          char c = s.charAt(j);
          if (c == '$') {
            prisonerLocations.add(new Pair(i, j, 0));
            c = '.';
          }
          map[i][j] = c;
        }

        Arrays.fill(visited[i], -1);
      }

      PriorityQueue<Pair> q = new PriorityQueue<>();
      for (Pair pl : prisonerLocations) {
        q.add(new Pair(pl.x, pl.y, 0));
        visited[pl.x][pl.y] = 0;
      }
      while (!q.isEmpty()) {
        Pair p = q.remove();

        for (Pair n : next) {
          int nextX = p.x + n.x;
          int nextY = p.y + n.y;

          if (nextX == 0 || nextY == 0) {
            break;
          }

          if (visited[nextX][nextY] != -1) {
            continue;
          }

          switch (map[nextX][nextY]) {
            case '*' -> {
            }
            case ',' -> visited[nextX][nextY] = visited[p.x][p.y];
            case '#' -> {

            }
          }
        }

      }

      bw.write(answer + "\n");
    }

    bw.flush();
    bw.close();
    br.close();
  }

}
