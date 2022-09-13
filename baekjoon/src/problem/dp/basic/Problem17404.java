/*
BinSin
https://www.acmicpc.net/problem/17404
 */

package problem.dp.basic;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem17404 {

    private static final int max = 1000 * 1000 + 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[][] rgb = new int[N+1][3];
        for (int i=1; i<=N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0; j<3; j++) {
                rgb[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[N+1][3];
        int min = max;

        for (int i=0; i<3; i++) {
            // 첫번째 색 고정
            for (int j=0; j<3; j++) {
                if (i == j) dp[1][j] = rgb[1][j];
                else dp[1][j] = max;
            }

            for (int j=2; j<=N; j++) {
                dp[j][0] = Math.min(dp[j-1][1], dp[j-1][2]) + rgb[j][0];
                dp[j][1] = Math.min(dp[j-1][0], dp[j-1][2]) + rgb[j][1];
                dp[j][2] = Math.min(dp[j-1][0], dp[j-1][1]) + rgb[j][2];
            }

            // 마지막 집은 i 가 아닌 경우만 가능
            for (int j=0; j<3; j++) {
                if (i == j) continue;
                min = Math.min(dp[N][j], min);
            }
        }

        bw.write(min + "");
        bw.flush();
        bw.close();
        br.close();
    }
}
