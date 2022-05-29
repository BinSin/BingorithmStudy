package exam.nhn_2022_05_29;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Solution03 {

    private static final Pair[] next = {new Pair(0, 1), new Pair(1 ,0), new Pair(0 ,-1), new Pair(-1, 0)};

    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int[] solution(String[] maze, String[] queries) {
        int rowSize = maze.length;
        int colSize = maze[0].length();
        int[] answer = new int[queries.length];
        char[][] map = new char[rowSize][colSize];
        for (int i=0; i<rowSize; i++) {
            map[i] = maze[i].toCharArray();
        }

        for (int i=0; i<queries.length; i++) {
            StringTokenizer querySt = new StringTokenizer(queries[i]);
            Pair entrance = new Pair(Integer.parseInt(querySt.nextToken()) - 1, Integer.parseInt(querySt.nextToken()) - 1);
            Pair exit = new Pair(Integer.parseInt(querySt.nextToken()) - 1, Integer.parseInt(querySt.nextToken()) - 1);
            String possibleAlphabet = querySt.nextToken();

            int[][] mapCount = new int[rowSize][colSize];

            Queue<Pair> q = new LinkedList<>();
            q.add(entrance);
            boolean isDone = false; // 출구 찾았을 경우의 플래그
            while (!q.isEmpty()) {
                Pair p = q.remove();

                for (Pair n : next) {
                    int nx = p.x + n.x;
                    int ny = p.y + n.y;

                    if (nx < 0 || nx >= rowSize || ny < 0 || ny >= colSize) continue; // 범위 벗어남
                    if (!possibleAlphabet.contains(String.valueOf(map[nx][ny]))) continue; // 갈 수 없는 알파벳
                    if(mapCount[nx][ny] > 0) continue; // 이미 방문한 지점

                    mapCount[nx][ny] = mapCount[p.x][p.y] + 1;
                    if (nx == exit.x && ny == exit.y) { // 출구 찾았을 경우 더이상 반복문 돌지 않도록
                        isDone = true;
                        answer[i] = mapCount[nx][ny] + 1;
                        break;
                    }
                    q.add(new Pair(nx, ny));
                }

                if (isDone) {
                    break;
                }
            }

            if (mapCount[exit.x][exit.y] == 0) { // 방문할 수 없다면 -1 리턴
                answer[i] = -1;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        String[] maze1 = {"AAAAA", "AABBB", "CAEFG", "AAEFF"};
        String[] queries1 = {"1 1 1 5 AF", "1 1 4 5 AF", "2 1 4 5 FAE", "1 5 4 5 ABF", "1 1 4 1 A"};
        System.out.println(Arrays.stream(solution(maze1, queries1)).boxed().collect(Collectors.toList()));

        String[] maze2 = {"AAA", "ABB", "ABA"};
        String[] queries2 = {"1 1 1 3 A", "1 3 3 1 A", "1 1 3 3 A", "1 1 3 3 AB"};
        System.out.println(Arrays.stream(solution(maze2, queries2)).boxed().collect(Collectors.toList()));
    }

}
