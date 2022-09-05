/*
BinSin
https://www.acmicpc.net/problem/2294
 */

package problem.dp.basic;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Problem2294 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        Set<Integer> coin = new HashSet<>();
        for (int i=0; i<n; i++) coin.add(Integer.parseInt(br.readLine()));

        int[] dp = new int[k+1];
        Arrays.fill(dp, -1);
        dp[0] = 0;

        for (Integer value : coin) {
            if (value > k) continue;
            for (int j=value; j<=k; j++) {
                if (dp[j - value] == -1) continue;

                if (dp[j] != -1) dp[j] = Math.min(dp[j - value] + 1, dp[j]);
                else dp[j] = dp[j - value] + 1;
            }
        }

        bw.write(dp[k] + "");
        bw.flush();
        bw.close();
        br.close();
    }
}
