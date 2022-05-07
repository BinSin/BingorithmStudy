package exam.naver_2021_10;

public class Solution02 {

    private static class Edge {
        int x, y;
        Edge(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int[] solution(int n, int jump) {
        final int MAX = n * n;
        int[] answer = {};

        int[][] map = new int[n][n];
        map[0][0] = 1;
        Edge curLocation = new Edge(0, 0);
        Edge[] nextLocation = {new Edge(1, 0), new Edge(0, 1), new Edge(-1, 0), new Edge(0, -1)};
        int direction = 0;
        int block = 0;

        for(int i=2; i<=MAX;) {
            for(int j=0; j<jump;) {
                int nextX = curLocation.x + nextLocation[direction].x;
                int nextY = curLocation.y + nextLocation[direction].y;

                if(direction == 3 && nextX == block && nextY == block) {
                    block++;
                    direction = 0;
                    nextX = block;
                    nextY = block;
                    curLocation = new Edge(block, block);
                }

               if ((block <=  nextX &&  nextX < n - block && block <=  nextY &&  nextY < n - block) ||
                        (block == (n-1)/2 && nextX == block && nextY == block)) {
                    curLocation = new Edge(nextX, nextY);

                    if(map[nextX][nextY] == 0) {
                        j++;
                        if(j == jump) {
                            map[nextX][nextY] = i++;

                            if(i == MAX + 1) {
                                answer = new int[]{nextY + 1, nextX + 1};
                            }
                        }
                    }
                } else {
                    direction = (direction + 1) % 4;

                    if((n % 2 == 0 && block == (n-1)/2 + 1) ||
                            (n % 2 == 1 && block == (n-1)/2)) {
                        direction = 0;
                        block = 0;
                        curLocation =  new Edge(0, 0);
                    }
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        int n1 = 5;
        int jump1 = 3;
        int[] result1 = solution(n1, jump1);
        System.out.println(result1[0] + " " + result1[1]);

        int n2 = 4;
        int jump2 = 1;
        int[] result2 = solution(n2, jump2);
        System.out.println(result2[0] + " " + result2[1]);

        int n3 = 3;
        int jump3 = 10;
        int[] result3 = solution(n3, jump3);
        System.out.println(result3[0] + " " + result3[1]);
    }
}
