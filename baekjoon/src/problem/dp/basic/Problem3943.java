/*
BinSin
https://www.acmicpc.net/problem/3943
 */

package problem.dp.basic;

import java.io.*;

public class Problem3943 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        while(T-- != 0) {
            int n = Integer.parseInt(br.readLine());
            int max = n;
            while (n != 1) {
                if (n % 2 == 0) n /= 2;
                else n = n * 3 + 1;
            }
            bw.write(max + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
