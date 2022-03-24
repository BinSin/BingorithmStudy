package kakaobank_2022_03_19;

import java.util.Arrays;

public class Solution03 {

    private static final Pair[] next = { new Pair(0, 1), new Pair(0, -1), new Pair(1, 0), new Pair(-1, 0) };
    private static int maxX;
    private static int maxY;
    private static int minX;
    private static int minY;

    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // x, y 값의 최대 최소값을 구하여 직사각형 모양인지 검사 (c는 어떤 색을 구별할 것인지 체크)
    private static boolean isSquare(char[][] map, char c) {
        for (int x=minX; x<=maxX; x++) { // 세로 검사
            if (map[x][minY] != c) return false;
            if (map[x][maxY] != c) return false;
        }
        for (int y=minY; y<=maxY; y++) { // 가로 검사
            if (map[minX][y] != c) return false;
            if (map[maxX][y] != c) return false;
        }
        return true;
    }

    private static void findIsland(Pair p, char[][] map, boolean[][] visited, char c, int sx, int sy, int ex, int ey) {
        if (p.x < sx || p.x > ex || p.y < sy || p.y > ey) { // 범위를 벗어나면 stop
            return;
        } else if (visited[p.x][p.y]) { // 방문을 했으면 stop
            return;
        } else if (map[p.x][p.y] != c) { // 동일한 섬이 아니면 stop
            return;
        }

        visited[p.x][p.y] = true;
        minX = Math.min(minX, p.x);
        minY = Math.min(minY, p.y);
        maxX = Math.max(maxX, p.x);
        maxY = Math.max(maxY, p.y);

        for (Pair nt : next) {
            int nextX = p.x + nt.x;
            int nextY = p.y + nt.y;
            Pair nextP = new Pair(nextX, nextY);
            findIsland(nextP, map, visited, c, sx, sy, ex, ey);
        }
    }

    public static boolean[] solution(String[][] grids) {
        int len = grids.length;
        boolean[] answer = new boolean[len];

        for (int k=0; k<len; k++) {
            String[] grid = grids[k];
            int n = grid.length;
            int m = grid[0].length();
            char[][] map = new char[n][m];
            for (int i = 0; i < n; i++) {
                map[i] = grid[i].toCharArray();
            }

            // 섬의 갯수 찾기
            boolean[][] visited = new boolean[n][m];
            int count = 0;
            maxX = 0; maxY = 0; minX = n-1; minY = m-1; // x, y 최대 최소값 세팅
            for (int i=0; i<n; i++) {
                for (int j=0; j<m; j++) {
                    if (!visited[i][j] && map[i][j] == 'X') { // 방문하지 않았고 검은색일 때 검은색 섬 찾기
                        findIsland(new Pair(i, j), map, visited, 'X', 0, 0, n-1, m-1); // 검은색
                        count++;
                    }
                }
            }

            // 검은색 검의 갯수가 1개가 아니면 false
            // 검은색 부분의 x, y 최대 최소값의 차이가 둘 다 1 이하면 false
            // 검은색 부분이 사각형이 아니면 false
            if (count != 1 || !isSquare(map, 'X')) {
                answer[k] = false;
                continue;
            }
            
            // 안쪽의 흰색 탐색
            int isx = minX + 1, iex = maxX, jsx = minY + 1, jex = maxY;
            maxX = isx; maxY = jsx; minX = iex-1; minY = jex-1; // 흰색의 최소 최대값 세팅
            count = 0;
            for (int i=isx; i<iex; i++) {
                for (int j=jsx; j<jex; j++) {
                    if (!visited[i][j] && map[i][j] == '.') { // 방문하지 않았고 흰색일 때 찾기
                        findIsland(new Pair(i, j), map, visited, '.', isx, jsx, iex-1, jex-1); // 흰색
                        count++;
                    }
                }
            }

            // 내부에 흰색 섬이 1개가 아니면 false
            // 흰색 검이 사각형이 아니면 false
            if (count != 1 || !isSquare(map, '.')) {
                answer[k] = false;
                continue;
            }

            // 흰색이 사각형인지 확인
            answer[k] = true;

        }

        return answer;
    }



    public static void main(String[] args) {
        String[][] grids = {{".....",".XXX.",".X.X.",".XXX.","....."},{"XXXXX","XXXXX","XXX.X","XXX.X","XXXXX"},{"XXXXX","X...X","X.X.X","X...X","XXXXX"},{"....X",".....","XXX..","X.X..","XXX.."},{".......","XXX.XXX","X.X.X.X","XXX.XXX","......."},{"XXXXX","XX.XX","X...X","XX.XX","XXXXX"}};
        System.out.println(Arrays.toString(solution(grids)));

        String[][] grids2 = {{"XXXX.","XX.X.","XX.X.","XX.X.","XXXX."}};
        System.out.println(Arrays.toString(solution(grids2)));
    }

}
