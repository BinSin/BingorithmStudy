package sk_2020_03;

public class Solution01 {

    private static final int[] moneyUnit = {1, 5, 10, 50, 100, 500};

    public static int solution(int money, int[] costs) {
        int[] check = new int[6]; // 최소 비용이 될 수 있는 동전 체크
        for (int i=1; i<6; i++) {
            int n;
            if(i % 2 == 1) {
                n = 5;
            } else {
                n = 2;
            }

            if(costs[i] > costs[i-1] * n) {
                costs[i] = costs[i-1] * n;
                check[i] = i - 1;
            } else {
                check[i] = i;
            }
        }

        int[] count = new int[6];
        for(int i=0; i<6; i+=2) {
            int n = money % 10;
            money /= 10;
            count[check[i]] += n%5 * (moneyUnit[i] / moneyUnit[check[i]]);
            count[check[i+1]] += n/5 * (moneyUnit[i+1] / moneyUnit[check[i+1]]);

        }

        if(money != 0) {
            count[check[5]] += money * 2 * (moneyUnit[5] / moneyUnit[check[5]]);
        }

        int answer = 0;
        for (int i=0; i<6; i++) {
            answer += costs[i] * count[i];
        }

        return answer;
    }

    public static void main(String[] args) {
        int money1 = 4578;
        int[] costs1 = {1, 4, 99, 35, 50, 1000};
        System.out.println(solution(money1, costs1));

        int money2 = 1999;
        int[] costs2 = {2, 11, 20, 100, 200, 600};
        System.out.println(solution(money2, costs2));

        int money3 = 7;
        int[] costs3 = {1, 1, 20, 100, 200, 1};
        System.out.println(solution(money3, costs3));
    }
}
