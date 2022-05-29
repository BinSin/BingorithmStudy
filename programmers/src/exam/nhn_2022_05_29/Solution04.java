package exam.nhn_2022_05_29;

public class Solution04 {
    
    private static boolean possibleWin(long[] players, int s, int e, int curPower) {
        if (e >= players.length) return false;
        int count = 1;
        for (int i=s; i<=e; i++) {
            if (players[i] > curPower) return false;
            curPower += count++;
        }
        return true;
    }

    public static long solution(long[] players, int power, int k) {
        // 현재 위치에서 n * 2 까지 연속해서 이길 수 있다면 이기는게 이득 -> 이후 이길 수 있을때가지 승리
        // 그 반대라면 계속 지는게 이득
        boolean canWin = false;
        long accumulatePower = 1;
        for (int i=0; i<players.length; i++) {
            if (canWin) {
                if (players[i] <= power + accumulatePower) {
                    power += accumulatePower;
                } else {
                    canWin = false;
                    accumulatePower = 1;
                }
                continue;
            }
            canWin = possibleWin(players, i, i + (k - 1) * 2, power);
            if (canWin) {
                i += (k - 1) * 2;
                accumulatePower += k * 2L + 1;
                power += (k * (k + 1)) / 2 + 1;
            } else {
                power += k;
            }
        }
        return power;
    }

    public static void main(String[] args) {
        long[] players1 = {10, 11, 15, 14, 16, 18, 19, 20};
        int  power1 = 10;
        int k1 = 2;
        System.out.println(solution(players1, power1, k1) == 31);

        long[] players2 = {1, 2, 4, 7};
        int  power2 = 1;
        int k2 = 2;
        System.out.println(solution(players2, power2, k2) == 11);

        long[] players3 = {1, 2, 1, 2, 1};
        int  power3 = 2;
        int k3 = 100;
        System.out.println(solution(players3, power3, k3) == 502);
    }

}
