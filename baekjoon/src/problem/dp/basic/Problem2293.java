/*
BinSin
https://www.acmicpc.net/problem/2293
 */

package problem.dp.basic;

import java.io.*;
import java.util.StringTokenizer;

public class Problem2293 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] coin = new int[n];
        for (int i=0; i<n; i++) {
            int coinValue = Integer.parseInt(br.readLine());
            coin[i] = coinValue;
        }

        int[] dp = new int[k+1];
        dp[0] = 1;
        for (int i=0; i<n; i++) {
            int value = coin[i];
            for (int j=value; j<=k; j++) {
                dp[j] += dp[j - value];
            }
        }

        bw.write(dp[k] + "");
        bw.flush();
        bw.close();
        br.close();
    }
}
