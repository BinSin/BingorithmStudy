package practice.kakao.level2;

import java.util.LinkedList;
import java.util.Queue;

public class Solution13 {

    private static void find(boolean[][] isDelete, int m, int n, char[][] b) {
        for (int i=0; i<m-1; i++) {
            for (int j=0; j<n-1; j++) {
                if(b[i][j] == '0') continue;
                if (b[i][j] == b[i][j+1] && b[i][j+1] == b[i+1][j] && b[i+1][j] == b[i+1][j+1]) {
                    for (int k=i; k<=i+1; k++) {
                        for (int l=j; l<=j+1; l++) {
                            isDelete[k][l] = true;
                        }
                    }
                }
            }
        }
    }

    private static int delete(boolean[][] isDelete, int m, int n, char[][] b) {
        int count = 0;
        for (int i=0; i<m; i++) {
            for (int j = 0; j < n; j++) {
                if(isDelete[i][j]) {
                    b[i][j] = '0';
                    count++;
                }
            }
        }
        return count;
    }

    private static void down(int m, int n, char[][] b) {
        for (int j=0; j<n; j++) {
            Queue<Character> q = new LinkedList<>();
            for (int i=m-1; i>=0; i--) {
                if(b[i][j] != '0') q.add(b[i][j]);
            }

            for (int i=m-1; i>=0; i--) {
                char c;
                if(!q.isEmpty()) c = q.poll();
                else c = '0';
                b[i][j] = c;
            }
        }
    }

    public static int solution(int m, int n, String[] board) {
        int answer = 0;
        char[][] b = new char[m][n];
        for (int i=0;i <m; i++) {
            b[i] = board[i].toCharArray();
        }

        while(true) {
            boolean[][] isDelete = new boolean[m][n];
            // step1) 지워질 블록 찾기
            find(isDelete, m, n, b);

            // step2) 블록 터트리고 갯수 세기
            int count = delete(isDelete, m, n, b);
            if(count == 0) break; // 터진 블록 없으면 stop
            answer += count;

            // step3) 블록 내래기
            down(m, n, b);
        }
        return answer;
    }

    public static void main(String[] args) {
        int m1 = 4;
        int n1 = 5;
        String[] board1 = {"CCBDE", "AAADE", "AAABF", "CCBBF"};
        System.out.println(solution(m1, n1, board1));

        int m2 = 6;
        int n2 = 6;
        String[] board2 = {"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"};
        System.out.println(solution(m2, n2, board2));
    }

}
