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
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Problem9376 {

  private static BufferedReader br;
  private static BufferedWriter bw;
  private static int h, w;
  private static char[][] map;
  private static int[][][] visited;

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
    br = new BufferedReader(new InputStreamReader(System.in));
    bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int t = Integer.parseInt(br.readLine());
    while (t-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      h = Integer.parseInt(st.nextToken());
      w = Integer.parseInt(st.nextToken());

      List<Pair> prisonerLocations = new ArrayList<>();
      map = new char[h + 2][w + 2];
      for (int i = 0; i < h + 2; i++) {
        Arrays.fill(map[i], '.');
      }
      visited = new int[3][h + 2][w + 2];

      List<Pair> doorLocations = new ArrayList<>();
      for (int i = 1; i <= h; i++) {
        String s = br.readLine();
        for (int j = 1; j <= w; j++) {
          char c = s.charAt(j - 1);
          if (c == '$') {
            prisonerLocations.add(new Pair(i, j, 0));
            c = '.';
          } else if (c == '#') {
            doorLocations.add(new Pair(i, j, 0));
          }
          map[i][j] = c;
        }
      }

      for (int prisoner = 0; prisoner < 3; prisoner++) {
        for (int i = 0; i < h + 2; i++) {
          Arrays.fill(visited[prisoner][i], -1);
        }
      }

      prisonerLocations.add(new Pair(0, 0, 0));
      for (int i = 0; i < 3; i++) {
        Pair pl = prisonerLocations.get(i);
        openDoor(i, pl);
      }

      int answer = Integer.MAX_VALUE;
      for (Pair dl : doorLocations) {
        int sum = 0;
        for (int i = 0; i < 3; i++) {
          sum += visited[i][dl.x][dl.y];
        }
        answer = Math.min(answer, sum - 2);
      }

      int count = 0;
      for (int i = 0; i < 3; i++) {
        if (visited[i][0][0] == 0) {
          count++;
        }
      }
      if (count == 3) {
        answer = 0;
      }

      bw.write(answer + "\n");
    }

    bw.flush();
    bw.close();
    br.close();
  }

  private static void openDoor(int prisoner, Pair pl) {
    PriorityQueue<Pair> q = new PriorityQueue<>(Comparator.comparingInt(o -> o.value));

    q.add(new Pair(pl.x, pl.y, 0));
    visited[prisoner][pl.x][pl.y] = 0;

    while (!q.isEmpty()) {
      Pair p = q.remove();

      for (Pair n : next) {
        int nextX = p.x + n.x;
        int nextY = p.y + n.y;

        if (nextX < 0 || nextX >= h + 2 || nextY < 0 || nextY >= w + 2) {
          continue;
        }

        if (visited[prisoner][nextX][nextY] != -1) {
          continue;
        }

        if (map[nextX][nextY] == '*') {
          continue;
        }

        int value = map[nextX][nextY];
        if (value == '.') {
          visited[prisoner][nextX][nextY] = visited[prisoner][p.x][p.y];
        } else if (value == '#') {
          visited[prisoner][nextX][nextY] = visited[prisoner][p.x][p.y] + 1;
        }
        q.add(new Pair(nextX, nextY, visited[prisoner][nextX][nextY]));
      }
    }
  }

}
