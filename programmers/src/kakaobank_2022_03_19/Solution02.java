package kakaobank_2022_03_19;

import java.util.Arrays;

public class Solution02 {

    public static int[] solution(int[] black_caps) {
        int len = black_caps.length;
        int[] answer = new int[len];

        // 먼저 확실한 사실을 구하기
        // 맨 끝 검사
        answer[1] = black_caps[0] == 0 ? 2 : 1;
        answer[len - 2] = black_caps[len - 1] == 0 ? 2 : 1;

        // 0과 2 검사
        for (int i = 1; i < len - 1; i++) {
            if (black_caps[i] == 0) { // 양 옆이 흰색 모자
                answer[i - 1] = answer[i + 1] = 2;
            } else if (black_caps[i] == 2) { // 양 옆이 검은색 모자
                answer[i - 1] = answer[i + 1] = 1;
            }
        }

        // 확실한 사실을 통해 나머지 추론
        // 1 검사
        for (int i = 1; i < len - 1; i++) {
            if (black_caps[i] == 1) {
                if (answer[i - 1] == 1) {
                    answer[i + 1] = 2;
                } else if (answer[i - 1] == 2) {
                    answer[i + 1] = 1;
                }
                if (answer[i + 1] == 1) {
                    answer[i - 1] = 2;
                } else if (answer[i + 1] == 2) {
                    answer[i - 1] = 1;
                }
            }
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
