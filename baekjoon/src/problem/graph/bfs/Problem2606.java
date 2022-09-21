/*
BinSin
https://www.acmicpc.net/problem/2606
 */

package problem.graph.bfs;

import java.io.*;
import java.util.*;

public class Problem2606 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int computerCount = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());
        boolean[] visited = new boolean[computerCount+1];
        List<Integer>[] computerList = new ArrayList[computerCount+1];
        for (int i=1; i<=computerCount; i++) computerList[i] = new ArrayList<>(i);
        StringTokenizer st;
        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            computerList[a].add(b);
            computerList[b].add(a);
        }

        int virusCount = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        visited[1] = true;
        while(!q.isEmpty()) {
            int nextN = q.remove();
            for (int next : computerList[nextN]) {
                if (visited[next]) continue;
                virusCount++;
                visited[next] = true;
                q.add(next);
            }
        }

        bw.write(String.valueOf(virusCount));
        bw.flush();
        bw.close();
        br.close();
    }
}
