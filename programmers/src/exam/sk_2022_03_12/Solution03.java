package exam.sk_2022_03_12;

import java.util.List;

public class Solution03 {

    public static int answer = 0;

    private static final List<Pair> next = List.of(new Pair(0, 1), new Pair(1, 0), new Pair(0, -1), new Pair(-1, 0));
    private static boolean[][] visited;

    static class Pair {
        int x, y;
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void dfs(int[][] map, int move, int x, int y, int width, int height) {
        if(move > 1) return;

        if(x == width && y == height) {
            if(move == 1) answer++;
            return;
        }

        if(!visited[x][y]) {
            if (map[x][y] == -1) {
                int nextX = x - 1;
                int nextY = y + 1;
                if(nextX < 0 || nextX > width || nextY < 0 || nextY > height) return;
                visited[nextX][nextY] = true;
                dfs(map, move + 1, nextX, nextY, width, height);
                visited[nextX][nextY] = false;
            } else if (map[x][y] == -2) {
                int nextX = x + 1;
                int nextY = y - 1;
                if(nextX < 0 || nextX > width || nextY < 0 || nextY > height) return;
                visited[nextX][nextY] = true;
                dfs(map, move + 1, nextX, nextY, width, height);
                visited[nextX][nextY] = false;
            }

            for (Pair n : next) {
                int nextX = x + n.x;
                int nextY = y + n.y;
                if(nextX < 0 || nextX > width || nextY < 0 || nextY > height) return;
                visited[nextX][nextY] = true;
                dfs(map, move, nextX, nextY, width, height);
                visited[nextX][nextY] = false;
            }
        }
    }

    public static int solution(int width, int height, int[][] diagonals) {
        visited = new boolean[width+1][height+1];
        int[][] map = new int[width+1][height+1];
        for(int[] d : diagonals) {
            int x = d[0];
            int y = d[1];
            map[width-x][y-1] = -1; // -1, 1 만큼 이동
            map[width-(x-1)][y] = -2; // 1, -1 만큼 이동
        }

        dfs(map, 0, 2, 0, width, height);

        return answer;
    }

    public static void main(String[] args) {
        int width1 = 2;
        int height1 = 2;
        int[][] diagonals1 = {{1, 1}, {2, 2}};
        System.out.println(solution(width1, height1, diagonals1));
        answer = 0;

        int width2 = 51;
        int height2 = 37;
        int[][] diagonals2 = {{17,19}};
        System.out.println(solution(width2, height2, diagonals2));
    }

}
