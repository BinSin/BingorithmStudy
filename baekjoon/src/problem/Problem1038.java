/*
BinSin
https://www.acmicpc.net/problem/1038
 */

package problem;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Problem1038 {

    private static void bp(List<Long> list, long number, int index) {
        if (index > 10) return; // 최대 수는 9876543210 이므로 10 자리가 넘으면 return
        list.add(number);
        for (int i=0; i<number % 10; i++) {
            bp(list, (number * 10) + i, index + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        long answer;

        List<Long> list = new ArrayList<>();
        if (N <= 10) answer = N;
        else if (N > 1022) answer = -1; // list.indexOf(9876543210L) = 1022 이므로 이 값을 초과할 수 없다.
        else {
            for (int i = 0; i < 10; i++) {
                bp(list, i, 1);
            }
            Collections.sort(list);
            answer = list.get(N);
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }

}
