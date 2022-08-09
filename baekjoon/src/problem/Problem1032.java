/*
BinSin
https://www.acmicpc.net/problem/1032
 */

package problem;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class Problem1032 {

    public static String solution(int N, String[] fileNameArr) {
        StringBuilder answer = new StringBuilder();
        int fileNameLength = fileNameArr[0].length();
        for (int i=0; i<fileNameLength; i++) {
            char c = fileNameArr[0].charAt(i);
            Set<Character> set = new HashSet<>();
            set.add(c);
            for (int j=1; j<N; j++) {
                if (set.add(fileNameArr[j].charAt(i))) {
                    c = '?';
                    break;
                }
            }
            answer.append(c);
        }
        return answer.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        String[] fileNameArr = new String[N];
        for (int i=0; i<N; i++) fileNameArr[i] = br.readLine();
        bw.write(solution(N, fileNameArr));
        bw.flush();
        bw.close();
        br.close();
    }
}
