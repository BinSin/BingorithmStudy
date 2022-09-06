/*
BinSin
https://www.acmicpc.net/problem/17626
 */

package problem.dp.basic;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Problem17626 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n+1];
        for (int i=1; i<=n; i++) dp[i] = i;

        List<Integer> squareNumberList = new ArrayList<>();
        for (int i=2; i*i<=n; i++) squareNumberList.add(i*i);

        for (int squreNumber : squareNumberList) {
            for (int i=squreNumber; i<=n; i++) {
                dp[i] = Math.min(dp[i - squreNumber] + 1, dp[i]);
            }
        }

        bw.write(dp[n] + "");
        bw.flush();
        bw.close();
        br.close();
    }
}
