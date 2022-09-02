/*
BinSin
https://www.acmicpc.net/problem/12852
 */

package problem.dp.basic;

import java.io.*;

public class Problem12852_2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[N+1];
        int[] trace = new int[N+1];

        for (int i=2; i<=N; i++) {
            // 1
            trace[i] = i - 1;
            // 2
            if (i % 2 == 0 && dp[i / 2] < dp[trace[i]]) {
                trace[i] = i / 2;
            }
            // 3
            if (i % 3 == 0 && dp[i / 3] < dp[trace[i]]) {
                trace[i] = i / 3;
            }
            dp[i] = dp[trace[i]] + 1;
        }

        bw.write(dp[N] + "\n");
        for (int i=N; i>0; i = trace[i]) {
            bw.write(i + " ");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
