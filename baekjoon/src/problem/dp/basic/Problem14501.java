package problem.dp.basic;

import java.io.*;
import java.util.StringTokenizer;

public class Problem14501 {

    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[] T = new int[N];
        int[] P = new int[N];
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N+1];
        for (int i=0; i<N; i++) {
            if (i + T[i] > N) continue;
            dp[i + T[i]] = Math.max(dp[i] + P[i], dp[i + T[i]]);
            for (int j=i+T[i]+1; j<=N; j++) {
                dp[j] = Math.max(dp[i + T[i]], dp[j]);
            }
        }
        bw.write(dp[N] + "");
        bw.flush();
        bw.close();
        br.close();
    }
}
