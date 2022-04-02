package programmers_dev_2022_04_02;

import java.util.*;

public class Solution02 {

    private static final char[] alphabet = new char[] {'a', 'b', 'c'};
    private static final Pair[] next = {new Pair(0, 1), new Pair(1, 0), new Pair(0, -1), new Pair(-1, 0)};
    private static int answer = 0;

    private static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // bfs를 활용하여 같은 알파벳으로 두 번 이상 탐색되는지 체크
    private static boolean check(char[][] map, int n, int m) {
        boolean[][] visited = new boolean[n][m]; // 방문 체크
        int[] count = new int[3]; // 각 알파벳 체크
        Queue<Pair> q = new LinkedList<>();
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                if (!visited[i][j]) {
                    char c = map[i][j];
                    int location = c - 'a';
                    if (count[location] == 1) return false; // 해당 알파벳으로 bfs로 체크된게 한번이라도 있으면 false
                    count[location]++;

                    q.add(new Pair(i, j));
                    while (!q.isEmpty()) {
                        Pair p = q.remove();
                        int x = p.x;
                        int y = p.y;
                        for (Pair nt : next) {
                            int nextX = x + nt.x;
                            int nextY = y + nt.y;
                            if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= m) continue; // index 벗어나면 안됨
                            if (visited[nextX][nextY]) continue; // 방문하지 않았어야함
                            if (map[nextX][nextY] != c) continue; // 같은 알파벳이어야함
                            visited[nextX][nextY] = true;
                            q.add(new Pair(nextX, nextY));
                        }
                    }
                }
            }
        }
        return true;
    }

    // 재퀴 함수를 이용하여 모든 경우의 수를 체크
    private static void insertAlphabet(List<Pair> questionMarkList, int i, char[][] map, int n, int m) {
        if (i == questionMarkList.size()) {
            if (check(map, n, m)) {
                answer++;
            }
            return;
        }
        Pair p = questionMarkList.get(i);
        for (char apb : alphabet) {
            map[p.x][p.y] = apb;
            insertAlphabet(questionMarkList, i + 1, map, n, m);
        }
    }

    public static int solution(String[] grid) {
        int n = grid.length;
        int m = grid[0].length();
        char[][] map = new char[n][m];
        List<Pair> questionMarkList = new ArrayList<>();
        for (int i=0; i< n; i++) {
            char[] cArr = grid[i].toCharArray();
            for (int j=0; j < m; j++) {
                char c = cArr[j];
                map[i][j] = c;
                if(c == '?') {
                    questionMarkList.add(new Pair(i, j));
                }
            }
        }
        insertAlphabet(questionMarkList, 0, map, n, m);
        return answer;
    }



    public static void main(String[] args) {
        String[] grid1 = {"??b", "abc", "cc?"};
        System.out.println(solution(grid1));
        answer = 0;

        String[] grid2 = {"abcabcab","????????"};
        System.out.println(solution(grid2));
        answer = 0;

        String[] grid3 = {"ab", "a?"};
        System.out.println(solution(grid3));
    }

}
