/*
BinSin
https://www.acmicpc.net/problem/10451
 */

package problem.graph.bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Problem10451 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int t = Integer.parseInt(br.readLine());
    for (int i = 0; i < t; i++) {
      int n = Integer.parseInt(br.readLine());
      StringTokenizer st = new StringTokenizer(br.readLine());

      int[] nextArr = new int[n + 1];
      for (int x = 1; x <= n; x++) {
        int y = Integer.parseInt(st.nextToken());
        nextArr[x] = y;
      }

      int answer = 0;
      boolean[] isVisited = new boolean[n + 1];
      for (int x = 1; x <= n; x++) {
        if (isVisited[x]) {
          continue;
        }

        int next = x;
        while (!isVisited[next]) {
          isVisited[next] = true;
          next = nextArr[next];
        }
        answer++;
      }

      bw.write(answer + "\n");
    }

    bw.flush();
    bw.close();
    br.close();
  }
}
