package exam.nhn_2022_05_29;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Solution01 {

    public static int solution(int[][] cards1, int[][] cards2) {
        int answer = 0;
        int gameCount = cards1.length;

        List<Integer> prevCardList1 = new ArrayList<>();
        List<Integer> prevCardList2 = new ArrayList<>();
        for (int i=0; i<gameCount; i++) {
            int[] playerCard1 = cards1[i];
            int[] playerCard2 = cards2[i];
            List<Integer> currCardList1 = Arrays.stream(playerCard1).boxed().collect(Collectors.toList());
            List<Integer> currCardList2 = Arrays.stream(playerCard2).boxed().collect(Collectors.toList());

            boolean isCheckDone = false;
            // 이전 카드더미와 중복되는 번호가 2개 이상 있는 경우
            if (!currCardList1.isEmpty()) { // 첫번째는 스킵
                long count1 = currCardList1.stream().filter(prevCardList1::contains).count();
                long count2 = currCardList2.stream().filter(prevCardList2::contains).count();
                if (count1 >= 2 || count2 >= 2) {
                    answer++;
                    isCheckDone = true;
                }
            }
            prevCardList1 = currCardList1; // 이전 카드 더미 리스트 저장
            prevCardList2 = currCardList2;

            if (isCheckDone) continue; // 위의 체크가 끝났을 경우

            // 한 라운드에서 두 플레이어가 받은 카드 10장 중에서 중복되는 번호가 있는 경우
            Set<Integer> set = new HashSet(currCardList1);
            set.addAll(currCardList2);
            if (set.size() != 10) {
                answer++;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        int[][] card11 = {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}};
        int[][] card21 = {{5, 7, 9, 11, 13}, {11, 13, 15, 17, 19}};
        System.out.println(solution(card11, card21) == 2);

        int[][] card12 = {{13, 21, 24, 29, 50}, {1, 12, 20, 21, 32}, {16, 26, 34, 46, 52}, {9, 11, 16, 16, 21}, {3, 8, 10, 16, 20}};
        int[][] card22 = {{5, 10, 15, 41, 49}, {6, 14, 15, 19, 46}, {5, 42, 43, 51, 52}, {5, 6, 11, 13, 45}, {5, 9, 11, 13, 45}};
        System.out.println(solution(card12, card22) == 3);
    }

}
