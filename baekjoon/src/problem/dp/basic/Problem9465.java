/*
BinSin
https://www.acmicpc.net/problem/9465
 */

package problem.dp.basic;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem9465 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        while (T-- != 0) {
            int n = Integer.parseInt(br.readLine());
            int[][] sticker = new int[2][n+1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i=1; i<=n; i++) sticker[0][i] = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            for (int i=1; i<=n; i++) sticker[1][i] = Integer.parseInt(st.nextToken());

            int[][] dp = new int[2][n+1];
            dp[0][1] = sticker[0][1];
            dp[1][1] = sticker[1][1];
            for (int i=2; i<=n; i++) {
                int max = Math.max(dp[0][i-2], dp[1][i-2]);
                dp[0][i] = sticker[0][i] + Math.max(dp[1][i-1], max);
                dp[1][i] = sticker[1][i] + Math.max(dp[0][i-1], max);
            }
            bw.write(Math.max(dp[0][n], dp[1][n]) + "\n");
            bw.flush();
        }
        bw.close();
        br.close();
    }
}
