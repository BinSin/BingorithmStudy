package exam.etc;

public class Solution02 {

    private static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int countDigeud(char[][] c, Point minPoint, Point maxPoint) {
        if (minPoint.x < 0 || minPoint.x >= c[0].length || maxPoint.y < 0 || maxPoint.y >= c[0].length) return -1;

        char minOwner = c[minPoint.x][minPoint.y];
        char maxOwner = c[maxPoint.x][maxPoint.y];
        if (minOwner != maxOwner) return -1;

        int count = (maxPoint.x - minPoint.x) * 2 + (maxPoint.y - minPoint.y) * 2;
        if (count == 4) return -1;

        int check = 4;
        for (int x=minPoint.x+1; x<=maxPoint.x; x++) {
            if (minOwner != c[x][minPoint.y]) {
                count -= (maxPoint.x - minPoint.x -1);
                check--;
                break;
            }
        }

        for (int x=minPoint.x; x<maxPoint.x; x++) {
            if (minOwner != c[x][maxPoint.y]) {
                count -= (maxPoint.x - minPoint.x -1);
                check--;
                break;
            }
        }
        if (check < 3) return -1;

        for (int y=minPoint.y+1; y<=maxPoint.y; y++) {
            if (minOwner != c[minPoint.x][y]) {
                count -= (maxPoint.y - minPoint.y - 1);
                check--;
                break;
            }
        }
        if (check < 3) return -1;

        for (int y=minPoint.y; y<maxPoint.y; y++) {
            if (minOwner != c[maxPoint.x][y]) {
                count -= (maxPoint.y - minPoint.y -1);
                check--;
                break;
            }
        }

        if (check < 3) return -1;

        if (check == 4) {
            count -= Math.min(maxPoint.x - minPoint.x-1, maxPoint.y - minPoint.y-1);
        }

        return count;
    }

    private static int maxArea(String[] cells) {
        int answer = -1;

        int row = cells.length;
        int col = cells[0].length();
        char[][] c = new char[row][col];
        for(int i=0; i<row; i++) c[i] = cells[i].toCharArray();

        for (int i=0; i<row-1; i++) {
            for (int j=0; j<col-1; j++) {
                for (int k=i+1; k<row; k++) {
                    for (int l=j+1; l<col; l++) {
                        answer = Math.max(answer, countDigeud(c, new Point(i, j), new Point(k, l)));
                    }
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        String[] cells1 = {"AAA", "AAA", "AAA", "AAA"};
        System.out.println(maxArea(cells1)); // 9

        String[] cells2 = {"AAA", "ABB", "AAA"};
        System.out.println(maxArea(cells2)); // 7

        String[] cells3 = {"BAA", "ABB", "ABB", "AAA"};
        System.out.println(maxArea(cells3)); // -1

        String[] cells4 = {"EHB", "AAA", "ABA", "AAE"};
        System.out.println(maxArea(cells4)); // 5

        String[] cells5 = {"AABB", "BABA", "AABA", "BAAA"};
        System.out.println(maxArea(cells5)); // 7

        String[] cells6 = {"CCBBBB","CCBBBB","CCBBBB","AABBBA", "BABABA", "AABABA", "BAAAAA"};
        System.out.println(maxArea(cells6)); // 13
    }
}
