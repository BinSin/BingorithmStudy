package exam.wanted_2022_07_02;

import java.io.*;
import java.util.StringTokenizer;

public class Main03 {

    public static void main(String[] args)  throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int answer = 0;
        for (int i=1; i<=n; i++) {
            if (n % i == 0) answer += i;
        }
        bw.write(answer + "");
        bw.flush();
        bw.close();
        br.close();
    }
}
