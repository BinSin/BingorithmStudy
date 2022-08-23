/*
BinSin
https://www.acmicpc.net/problem/1010
dp를 이용한 조합 nCk 구하기
 */

package problem.dp.basic;

import java.io.*;
import java.util.StringTokenizer;

public class Problem1010 {

    // nCk = n-1Ck-1 + n-1Ck
    private static int[][] nCk(int N, int M) {
        int[][] C = new int[N+1][M+1];
        C[0][0] = 1;
        for (int n=1; n<=N; n++) {
            C[n][0] = 1;
            for (int k=1; k<=M; k++) {
                C[n][k] = C[n-1][k-1] + C[n-1][k];
            }
        }
        return C;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        int[][] nCk = nCk(30, 30);
        while (T-- != 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            bw.write(nCk[M][N] + "\n"); // mCn
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
