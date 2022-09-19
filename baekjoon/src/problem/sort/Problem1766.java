/*
BinSin
https://www.acmicpc.net/problem/1766
 */

package problem.sort;

import java.io.*;
import java.util.*;

public class Problem1766 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] trace = new int[N+1];
        List<Integer>[] next = new ArrayList[N+1];
        for (int i=1; i<=N; i++) next[i] = new ArrayList<>();
        // Arrays.fill(next, new ArrayList<>()); // 이 코드 사용 시 같은 주소 참조

        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            next[A].add(B);
            trace[B]++;
        }

        PriorityQueue<Integer> q = new PriorityQueue<>();
        for (int i=1; i<=N; i++) {
            if (trace[i] == 0) q.add(i);
        }

        while(!q.isEmpty()) {
            int node = q.remove();
            bw.write(node + " ");

            for (int n : next[node]) {
                if (--trace[n] == 0) {
                    q.add(n);
                }
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
