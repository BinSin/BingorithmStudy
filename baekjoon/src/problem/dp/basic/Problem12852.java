/*
BinSin
https://www.acmicpc.net/problem/12852
 */

package problem.dp.basic;

import java.io.*;
import java.util.Stack;

public class Problem12852 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());

        Stack<Integer>[] dp = new Stack[N+1];
        for (int i=0; i<=N; i++) dp[i] = new Stack<>();
        dp[1].add(1); // 초기값 세팅

        for (int i=2; i<=N; i++) {
            int one = i - 1, two = i / 2, three = i / 3;
            // 1
            int operation = 1;
            int min = dp[one].size();
            // 2
            if (i % 2 == 0 && dp[two].size() < min) {
                operation = 2;
                min = dp[two].size();
            }
            // 3
            if (i % 3 == 0 && dp[three].size() < min) {
                operation = 3;
            }

            int prevNumber;
            switch (operation) { // 백준은 Java 11 버전까지만 사용하므로 switch 연산에 람다 사용 못함
                case 2 : prevNumber = two; break;
                case 3 : prevNumber = three; break;
                default : prevNumber = one; break;
            }

            dp[i] = (Stack<Integer>) dp[prevNumber].clone();
            dp[i].add(i);
        }

        bw.write(dp[N].size() - 1 + "\n");
        while(!dp[N].isEmpty()) bw.write(dp[N].pop() + " ");

        bw.flush();
        bw.close();
        br.close();
    }
}
