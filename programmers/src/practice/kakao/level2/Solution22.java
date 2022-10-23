package practice.kakao.level2;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Solution22 {

    private static Point[] next = {new Point(0, 1), new Point(1, 0), new Point(0, -1), new Point(-1, 0)};

    private static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int rotate(int[][] map, Point minP, Point maxP, int rows, int columns) {
        Point cp = new Point(minP.x, minP.y);
        int cv = map[cp.x][cp.y];
        int min = cv;
        for (Point n : next) {
            while(true) {
                int nx = cp.x + n.x;
                int ny = cp.y + n.y;
                if (nx < 1 || nx > rows || ny < 1 || ny > columns) break;
                if (nx < minP.x || nx > maxP.x || ny < minP.y || ny > maxP.y) break;

                int nv = map[nx][ny];
                map[nx][ny] = cv;
                cv = nv;

                cp = new Point(nx, ny);
                min = Math.min(min, cv);
            }
        }
        return min;
    }

    public static int[] solution(int rows, int columns, int[][] queries) {
        int[][] map = new int[rows+1][columns+1];
        for (int i=1; i<=rows; i++) {
            for (int j=1; j<=columns; j++) {
                map[i][j] = (i-1) * columns + j;
            }
        }

        int queryLength = queries.length;
        int[] answer = new int[queryLength];
        for (int i=0; i<queryLength; i++) {
            int[] query = queries[i];
            Point minP = new Point(query[0], query[1]);
            Point maxP = new Point(query[2], query[3]);
            int min = rotate(map, minP, maxP, rows, columns);
            answer[i] = min;
        }

        return answer;
    }



    public static void main(String[] args) {
        int rows1 = 6, columns1 = 6;
        int[][] queries1 = {{2,2,5,4},{3,3,6,6},{5,1,6,3}};
        System.out.println(Arrays.stream(solution(rows1, columns1, queries1)).mapToObj(String::valueOf).collect(Collectors.joining(",")));

        int rows2 = 3, columns2 = 3;
        int[][] queries2 = {{1,1,2,2},{1,2,2,3},{2,1,3,2},{2,2,3,3}};
        System.out.println(Arrays.stream(solution(rows2, columns2, queries2)).mapToObj(String::valueOf).collect(Collectors.joining(",")));

        int rows3 = 3, columns3 = 3;
        int[][] queries3 = {{1,1,100,97}};
        System.out.println(Arrays.stream(solution(rows3, columns3, queries3)).mapToObj(String::valueOf).collect(Collectors.joining(",")));
    }
}
