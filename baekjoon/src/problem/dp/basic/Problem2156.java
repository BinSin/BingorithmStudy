/*
BinSin
https://www.acmicpc.net/problem/2156
 */

package problem.dp.basic;

import java.io.*;

public class Problem2156 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] wine = new int[n+1];
        for (int i=1; i<=n; i++) {
            wine[i] = Integer.parseInt(br.readLine());
        }

        int[][] dp = new int[n+1][3];
        for (int i=1; i<=n; i++) {
            dp[i][0] = Math.max(Math.max(dp[i-1][0], dp[i-1][1]), dp[i-1][2]);
            dp[i][1] = dp[i-1][0] + wine[i];
            dp[i][2] = dp[i-1][1] + wine[i];
        }

        bw.write(Math.max(Math.max(dp[n][0], dp[n][1]), dp[n][2]) + "");
        bw.flush();
        bw.close();
        br.close();
    }
}
