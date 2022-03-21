package kakaobank_2022_03_19;

import java.util.Arrays;

public class Solution03 {

    static final Pair[] next = {new Pair(0, 1), new Pair(0, -1), new Pair(1, 0), new Pair(-1, 0),
            new Pair(1, 1), new Pair(-1, 1), new Pair(1, -1), new Pair(-1, -1)
    };

    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static boolean[] solution(String[][] grids) {
        int n = grids.length;
        int m = grids[0].length;
        boolean[] answer = new boolean[n];

        Pair firstP;
        boolean flag = false;
        char[][] map = new char[n][m];
        for (int i = 0; i < n; i++) {
            String[] grid = grids[i];
            for (int j = 0; j < m; j++) {
                char c = grid[i].charAt(j);
                map[i][j] = c;
                if (c == 'X' && !flag) {
                    flag = true;
                    firstP = new Pair(i, j);
                }
                ;
            }
            map[i] = grid[i].toCharArray();

        }

        return answer;
    }

    public static void main(String[] args) {
        String[][] grids = {{".....",".XXX.",".X.X.",".XXX.","....."},{"XXXXX","XXXXX","XXX.X","XXX.X","XXXXX"},{"XXXXX","X...X","X.X.X","X...X","XXXXX"},{"....X",".....","XXX..","X.X..","XXX.."},{".......","XXX.XXX","X.X.X.X","XXX.XXX","......."},{"XXXXX","XX.XX","X...X","XX.XX","XXXXX"}};
        System.out.println(Arrays.toString(solution(grids)));

    }

}
