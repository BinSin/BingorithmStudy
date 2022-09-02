/*
BinSin
https://www.acmicpc.net/problem/1351
 */

package problem.dp.basic;

import java.io.*;
import java.util.*;

public class Problem1351 {

    static Map<Long, Long> map = new HashMap<>();

    private static long A(long N, int P, int Q) {
        if (N == 0) return 1;
        if (!map.containsKey(N)) {
            map.put(N, A(N / P, P, Q) + A(N / Q, P, Q));
        }
        return map.get(N);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long N = Long.parseLong(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        bw.write(A(N, P, Q) + "");
        bw.flush();
        bw.close();
        br.close();
    }
}