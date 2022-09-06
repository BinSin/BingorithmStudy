package problem.dp.basic;

import java.io.*;
import java.util.StringTokenizer;

public class Problem1149 {

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
        for (int i=1; i<=N; i++) {
            dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + rgb[i][0];
            dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + rgb[i][1];
            dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + rgb[i][2];
        }

        bw.write(Math.min(Math.min(dp[N][0], dp[N][1]), dp[N][2]) + "");
        bw.flush();
        bw.close();
        br.close();
    }
}
