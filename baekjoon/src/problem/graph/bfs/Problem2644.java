/*
BinSin
https://www.acmicpc.net/problem/2644
 */

package problem.graph.bfs;

import java.io.*;
import java.util.*;

public class Problem2644 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(br.readLine());
        int[] count = new int[n+1];
        Arrays.fill(count, -1);
        List<Integer>[] list = new ArrayList[n+1];
        for (int i=1; i<=n; i++) list[i] = new ArrayList<>();
        for (int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            list[x].add(y);
            list[y].add(x);
        }

        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        count[start] = 0;
        while (!q.isEmpty()) {
            int next = q.remove();
            int prevCount = count[next];
            for (int nextN : list[next]) {
                if (count[nextN] == -1) {
                    count[nextN] = prevCount + 1;
                    q.add(nextN);
                }
            }
        }

        bw.write(String.valueOf(count[end]));
        bw.flush();
        bw.close();
        br.close();
    }
}
