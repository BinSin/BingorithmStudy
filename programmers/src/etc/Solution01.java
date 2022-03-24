package etc;

public class Solution01 {

    private static int value(char c) {
        return c == '.' ? 0 : 1;
    }

    public static int solution(String L1, String L2) {
        int n = 2, m = L1.length();
        char[] arr1 = ("." + L1).toCharArray();
        char[] arr2 = ("." + L2).toCharArray();
        char[][] map = new char[n][m];
        map[0] = arr1;
        map[1] = arr2;

        int[][] dp = new int[2][m+1];

        for (int i=0; i<m; i++) {
            int s = 0;
            if (map[0][i] == 'x' && map[1][i] == 'x') s = 1;
            for (int j=0; j<2; j++) {
                dp[j][i+1] = Math.min(value(map[j][i]), value(map[(j+1)%2][i+1]) + dp[(j+1)%2][i]) + s;
            }
        }
        return Math.min(dp[0][m], dp[1][m]);
    }

    public static void main(String[] args) {
        String L11 = "..xx.x.";
        String L21 = "x.x.x..";
        System.out.println(solution(L11, L21));

        String L12 = ".xxx...x";
        String L22 = "..x.xxxx";
        System.out.println(solution(L12, L22) == 4);

        String L13 = "11111";
        String L23 = "01001";
        System.out.println(solution(L13, L23) == 3);

        String L14 = "10001";
        String L24 = "00100";
        System.out.println(solution(L14, L24) == 3);
    }

}