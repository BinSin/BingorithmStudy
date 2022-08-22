/*
BinSin
https://www.acmicpc.net/problem/1654
 */

package problem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution1654 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        List<Long> onlineList = new ArrayList<>();
        for (int i=0; i<K; i++) {
            long onlineLength = Long.parseLong(br.readLine());
            onlineList.add(onlineLength);
        }

        long left = 1;
        long right = Integer.MAX_VALUE;
        while (left <= right) {
            long mid = (left + right) / 2;
            long sum = onlineList.stream().mapToLong(v -> v / mid).sum();

            if (sum < N) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        bw.write(right + "");
        bw.flush();
        bw.close();
        br.close();
    }

}
