package exam.programmers_dev_2022_11;

public class Solution01 {

    public static int solution(int low, int high, String[] imageArr) {
        int answer = 0;

        int colLength = imageArr.length;
        int rowLength = imageArr[0].length();

        char[][] map = new char[colLength][rowLength];
        for (int i=0; i<imageArr.length; i++) {
            map[i] = imageArr[i].toCharArray();
        }

        for (int sx=0; sx<colLength-2; sx++) {
            for (int sy=0; sy<rowLength-2; sy++) {
                for (int ex=sx+2, ey=sy+2, n=3; ex<colLength && ey<rowLength; ex++, ey++, n++) {
                    if (!isAllBridgeBlack(map, sx, sy, ex, ey)) continue;
                    if (!isQRCode(map, sx+1, sy+1, ex-1, ey-1, low, high, n)) continue;
                    answer++;
                }
            }
        }

        return answer;
    }

    // 가장자리가 모두 검은색 칸인지 구하는 함수
    private static boolean isAllBridgeBlack(char[][] map, int sx, int sy, int ex, int ey) {
        for (int x=sx; x<=ex; x++) {
            if (map[x][sy] == '.') return false;
            if (map[x][ey] == '.') return false;
        }

        for (int y=sy; y<=ey; y++) {
            if (map[sx][y] == '.') return false;
            if (map[ex][y] == '.') return false;
        }

        return true;
    }

    // QR코드로 인식될 수 있는지 구하인 함수
    private static boolean isQRCode(char[][] map, int sx, int sy, int ex, int ey, int low, int high, int n) {
        int k = 0;
        for (int x=sx; x<=ex; x++) {
            for (int y=sy; y<=ey; y++) {
                if (map[x][y] == '#') k++;
            }
        }

        System.out.print(low * (n - 2) * (n - 2) + "  ");
        System.out.print(k * 100 + "  ");
        System.out.println(high * (n - 2) * (n - 2));
        return low * (n - 2) * (n - 2) <= k * 100 && k * 100 < high * (n - 2) * (n - 2);
    }

    public static void main(String[] args) {
        int low = 21, high = 51;
        String[] imageArr = {".########......", ".####...#......", ".#.####.#.#####", ".#.#..#.#.#..##", ".#.##.#.#.#...#", ".#.####.#.#...#", ".#....###.#####", ".########......"};
        System.out.println(solution(low, high, imageArr));
    }
}
