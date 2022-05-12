/*
리틀 프렌즈 사천성
https://programmers.co.kr/learn/courses/30/lessons/1836
 */

package practice.kakao.level3;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;


public class Solution04 {

    private static final Pair[] next = { new Pair(0, 1, -1, 0), new Pair(0, -1, -1, 0), new Pair(1, 0, 1, 0), new Pair(-1, 0, 1, 0) };

    private static class Pair {
        int x, y;
        char c; // 알파벳
        int d; // 방향 (음수: 가로, 양수: 세로)
        int count; // 꺾은 수
        Pair(int x, int y, int d, int count) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.count = count;
        }
        Pair(int x, int y, char c) {
            this.x = x;
            this.y = y;
            this.c = c;
        }
    }

    // TODO: BFS로 접근했는데 꺾는 것을 최소화 하여 기록하기 위해서는 DFS로 접근해야할 것 같음 다시 풀어보자!!
    public static String solution(int m, int n, String[] board) {
        StringBuilder sb = new StringBuilder();

        Set<Pair> set = new TreeSet<>(Comparator.comparingInt(o -> o.c)); // 검색할 문자 (알파벳 순으로 저장)
        char[][] map = new char[m][n];
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                char c = board[i].charAt(j);
                map[i][j] = c;
                if (!Character.isAlphabetic(c)) continue;
                set.add(new Pair(i, j, c));
            }
        }

        while(!set.isEmpty()) {
            boolean flag = false; // 타일이 제거 되었는지 확인
            for (Pair s : set) {
                char startC = s.c;
                int startX = s.x;
                int startY = s.y;

                Queue<Pair> q = new LinkedList<>();
                boolean[][] check = new boolean[m][n]; // 방문 체크
                q.add(new Pair(startX, startY, 0, 0));
                check[startX][startY] = true;
                while(!q.isEmpty()) {
                    Pair p = q.remove();
                    int px = p.x;
                    int py = p.y;
                    int pd = p.d;
                    int pCount = p.count;

                    for (Pair nt : next) {
                        int nx = px + nt.x;
                        int ny = py + nt.y;
                        int nd = nt.d;
                        int nCount = pCount;

                        if (pd != nd) nCount++; // 방향이 다르면 꺾음

                        if (nCount > 2) continue; // 두번 이상 꺾을 수 없음
                        if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue; // 영역 밖으로 넘을 수 없음
                        if (map[nx][ny] == '*') continue; // 벽은 방문할 수 없음
                        if (check[nx][ny]) continue;

                        if (map[nx][ny] == '.') {
                            q.add(new Pair(nx, ny, nd, nCount));
                            check[nx][ny] = true;
                        } else if (map[nx][ny] == startC) {
                            flag = true;
                            map[startX][startY] = map[nx][ny] = '.';
                            set.remove(new Pair(startX, startY, startC));
                            sb.append(startC);
                            break;
                        }
                    }
                    if (flag) break;
                }
                if (flag) break;
            }
            if (!flag) return "IMPOSSIBLE";
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        int m1 = 3, n1 = 3;
        String[] board1 = {"DBA", "C*A", "CDB"};
        System.out.println(solution(m1, n1, board1));

        int m2 = 2, n2 = 4;
        String[] board2 = {"NRYN", "ARYA"};
        System.out.println(solution(m2, n2, board2));

        int m3 = 4, n3 = 4;
        String[] board3 = {".ZI.", "M.**", "MZU.", ".IU."};
        System.out.println(solution(m3, n3, board3));

        int m4 = 5, n4 = 5;
        String[] board4 = {"FGHEI", "BAB..", "D.C*.", "CA..I", "DFHGE"};
        System.out.println(solution(m4, n4, board4)); // ABCDFHGIE

        int m5 = 3, n5 = 3;
        String[] board5 = {"A*A", ".*.", "..."};
        System.out.println(solution(m5, n5, board5));
    }

}
