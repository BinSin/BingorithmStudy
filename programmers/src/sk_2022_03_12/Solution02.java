package sk_2022_03_12;

import java.util.LinkedList;
import java.util.Queue;

public class Solution02 {

    private static final Pair[] next = {new Pair(0, 1), new Pair(1, 0), new Pair(0, -1), new Pair(-1, 0)};

    static class Pair {
        int x, y, d;
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
        Pair(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }

    private static void init(Queue<Pair> q, int[][] map, int n, boolean clockwise) {
        Pair p1, p2, p3, p4;
        if (clockwise) {
            p1 = new Pair(0, 0, 0);
            p2 = new Pair(0, n-1, 1);
            p3 = new Pair(n-1, n-1, 2);
            p4 = new Pair(n-1, 0, 3);
        } else {
            p1 = new Pair(0, 0, 1);
            p2 = new Pair(n-1, 0, 0);
            p3 = new Pair(n-1, n-1, 3);
            p4 = new Pair(0, n-1, 2);
        }

        q.add(p1);
        q.add(p2);
        q.add(p3);
        q.add(p4);

        map[0][0] = 1;
        map[n-1][0] = 1;
        map[n-1][n-1] = 1;
        map[0][n-1] = 1;
    }

    public static int[][] solution(int n, boolean clockwise) {
        int[][] answer = new int[n][n];
        Queue<Pair> q = new LinkedList<>();
        init(q, answer, n, clockwise);


        while(!q.isEmpty()) {
            Pair p = q.poll();
            int curX = p.x;
            int curY = p.y;
            int curD = p.d;

            int flag = 1;
            if(!clockwise) flag = -1;

            for (int i=0; i<4; i++) {
                int nextD = (4 + curD + (i * flag)) % 4;
                int nextX = curX + next[nextD].x;
                int nextY = curY + next[nextD].y;
                if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= n) continue;
                if (answer[nextX][nextY] != 0) continue;
                answer[nextX][nextY] = answer[curX][curY] + 1;
                q.add(new Pair(nextX, nextY, nextD));
                break;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int n1 = 5;
        boolean clockwise1 = true;
        int[][] result1 = solution(n1, clockwise1);
        for(int i=0; i<n1; i++) {
            for(int j=0; j<n1; j++) {
                System.out.print(result1[i][j] + " ");
            }
            System.out.println();
        }

        int n2 = 6;
        boolean clockwise2 = false;
        int[][] result2 = solution(n2, clockwise2);
        for(int i=0; i<n2; i++) {
            for(int j=0; j<n2; j++) {
                System.out.print(String.format("%03d", result2[i][j]) + " ");
            }
            System.out.println();
        }
        System.out.println();

        int n3= 9;
        boolean clockwise3 = false;
        int[][] result3 = solution(n3, clockwise3);
        for(int i=0; i<n3; i++) {
            for(int j=0; j<n3; j++) {
                System.out.print(String.format("%03d", result3[i][j]) + " ");
            }
            System.out.println();
        }
        System.out.println();

        int n4= 1;
        boolean clockwise4 = true;
        int[][] result4 = solution(n4, clockwise4);
        for(int i=0; i<n4; i++) {
            for(int j=0; j<n4; j++) {
                System.out.print(String.format("%03d", result4[i][j]) + " ");
            }
            System.out.println();
        }
        System.out.println();

        int n5= 1000;
        boolean clockwise5 = true;
        int[][] result5 = solution(n5, clockwise5);
        /*for(int i=0; i<n5; i++) {
            for(int j=0; j<n5; j++) {
                System.out.print(result5[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();*/

    }

}
