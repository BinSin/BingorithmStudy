/*
BinSin
https://www.acmicpc.net/problem/3025
 */

package problem.dp.basic;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class problem3025 {

    private static int R, C;
    private static char[][] board;
    private static Stack<Pair>[] dp;

    static class Pair {
        int x, y;
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static void down(int n, int x, int y) {
        while (x + 1 <= R && board[x + 1][y] != 'X') {
            if (board[x + 1][y] == 'O') {
                if (y - 1 > 0 && board[x + 1][y - 1] == '.' && board[x][y - 1] == '.') { // left
                    x++;
                    y--;
                } else if (y + 1 <= C && board[x + 1][y + 1] == '.' && board[x][y + 1] == '.') { // right
                    x++;
                    y++;
                } else { // up
                    break;
                }
            } else { // nextLocation == '.'
                x++;
            }
            dp[n].add(new Pair(x, y));
        }
        board[x][y] = 'O';
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R+1][C+1];
        for (int r=1; r<=R; r++) {
            String s = br.readLine();
            for (int c=1; c<=C; c++) {
                board[r][c] = s.charAt(c-1);
            }
        }

        dp = new Stack[C+1];
        for (int i=1; i<=C; i++) dp[i] = new Stack<>();

        int N = Integer.parseInt(br.readLine());
        while(N-- != 0) {
            int n = Integer.parseInt(br.readLine());

            while (!dp[n].isEmpty() && board[dp[n].peek().x][dp[n].peek().y] != '.') { // 돌이 없는 지점까지 pop
                dp[n].pop();
            }

            if (dp[n].isEmpty()) {
                down(n, 1, n);
            } else {
                down(n, dp[n].peek().x, dp[n].peek().y);
            }
        }

        for (int r=1; r<=R; r++) {
            for (int c=1; c<=C; c++) {
                bw.write(board[r][c]);
            }
            bw.write("\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
