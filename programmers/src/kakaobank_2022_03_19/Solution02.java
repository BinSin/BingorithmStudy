package kakaobank_2022_03_19;

import java.util.Arrays;

public class Solution02 {

    public static int[] solution(int[] black_caps) {
        int len = black_caps.length;
        int[] answer = new int[len];

        // 맨 끝 검사
        answer[1] = black_caps[0] == 0 ? 2 : 1;
        answer[len - 2] = black_caps[len - 1] == 0 ? 2 : 1;

        boolean[] check = new boolean[len];
        while(true) {
            int count = 0;

            // 2 검사
            for (int i = 1; i < len - 1; i++) {
                if (black_caps[i] == 2 && !check[i - 1] && !check[i + 1]) {
                    answer[i - 1] = answer[i + 1] = 1;
                    check[i + 1] = true;
                    check[i - 1] = true;
                    count++;
                }
            }

            // 1 검사
            for (int i = 1; i < len - 1; i++) {
                if (black_caps[i] == 1) {
                    if (answer[i - 1] == 1 && !check[i + 1]) {
                        answer[i + 1] = 2;
                        check[i + 1] = true;
                        count++;
                    } else if (answer[i - 1] == 2 && !check[i + 1]) {
                        answer[i + 1] = 1;
                        check[i + 1] = true;
                        count++;
                    } else if (answer[i + 1] == 1 && !check[i - 1]) {
                        answer[i - 1] = 2;
                        check[i - 1] = true;
                        count++;
                    } else if (answer[i + 1] == 2 && !check[i - 1]) {
                        answer[i - 1] = 1;
                        check[i - 1] = true;
                        count++;
                    }
                }
            }
            if(count == 0) break;
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] black_caps1 = {1, 1, 2, 0};
        System.out.println(Arrays.toString(solution(black_caps1)));

        int[] black_caps2 = {1, 1, 1};
        System.out.println(Arrays.toString(solution(black_caps2)));

        int[] black_caps3 = {0, 1, 2, 1, 0};
        System.out.println(Arrays.toString(solution(black_caps3)));
    }

}
