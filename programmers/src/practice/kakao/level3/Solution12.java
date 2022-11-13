/*
BinSin
https://school.programmers.co.kr/learn/courses/30/lessons/77486
분류: 컬렉션 활용
 */

package practice.kakao.level3;

import java.util.*;
import java.util.stream.Collectors;

public class Solution12 {

    private static class Node {
        String parent;
        int profit;

        public Node(String parent, int profit) {
            this.parent = parent;
            this.profit = profit;
        }
    }

    // 1. 이름에 따른 depth 저장
    // 2. depth 가 깊은 순서대로 parent 에 이익 전달
    // 3. enroll 순서대로 answer 세팅
    public static int[] solution(String[] enrollArr, String[] referralArr, String[] sellerArr, int[] amountArr) {
        Map<String, Node> enrollMap = new HashMap<>();
        Map<Integer, List<String>> depthMap = new HashMap<>();
        for (int i=0; i<enrollArr.length; i++) {
            String enroll = enrollArr[i];
            String referral = referralArr[i];

            int depth = 1;
            enrollMap.put(enroll, new Node(referral, 0));

            depthMap.computeIfAbsent(depth, k -> new ArrayList<>());
            depthMap.get(depth).add(enroll);
        }

        for (int i=0; i<sellerArr.length; i++) {
            String seller = sellerArr[i];
            int amount = amountArr[i] * 100;
            int parentProfit = amount / 10;
            Node node = enrollMap.get(seller);

            while(true) {
                int currentProfit = amount - parentProfit;
                node.profit += currentProfit;
                if("-".equals(node.parent)) break;
                if (parentProfit == 0) break;

                amount = currentProfit;
                parentProfit = (int) Math.ceil((double) amount / 10);

                node = enrollMap.get(node.parent);
            }
        }

        int[] answer = new int[enrollArr.length];
        for (int i=0; i<enrollArr.length; i++) {
            String enroll = enrollArr[i];
            answer[i] = enrollMap.get(enroll).profit;
        }

        return answer;
    }

    public static void main(String[] args) {
        String[] enroll1 = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
        String[] referral1 = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};
        String[] seller1 = {"young", "john", "tod", "emily", "mary"};
        int[] amount1 = {12, 4, 2, 5, 10};
        System.out.println(Arrays.stream(solution(enroll1, referral1, seller1, amount1)).mapToObj(String::valueOf).collect(Collectors.joining(" ")));

        String[] enroll2 = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
        String[] referral2 = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};
        String[] seller2 = {"sam", "emily", "jaimie", "edward"};
        int[] amount2 = {2, 3, 5, 4};
        System.out.println(Arrays.stream(solution(enroll2, referral2, seller2, amount2)).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
    }
}
