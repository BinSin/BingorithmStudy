/*
BinSin
https://www.acmicpc.net/problem/13263
 */

package problem.dp.difficult;

import java.io.*;
import java.util.Arrays;

public class Problem13263 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] a = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] b = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        long[] dp = new long[n];

        dp[0] = b[0];
        for (int i=1; i<n; i++) {
            dp[i] = (long) a[i] * b[i - 1] + dp[i-1];
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
