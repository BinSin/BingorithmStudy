/*
BinSin
https://school.programmers.co.kr/learn/courses/30/lessons/77486
분류: Map
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

    public static int[] solution(String[] enrollArr, String[] referralArr, String[] sellerArr, int[] amountArr) {
        Map<String, Node> enrollMap = new HashMap<>();
        for (int i=0; i<enrollArr.length; i++) {
            String enroll = enrollArr[i];
            String referral = referralArr[i];
            enrollMap.put(enroll, new Node(referral, 0));
        }

        for (int i=0; i<sellerArr.length; i++) {
            String seller = sellerArr[i];
            int amount = amountArr[i] * 100;

            Node node = enrollMap.get(seller);

            while(true) {
                int parentProfit = amount / 10;
                int myProfit = amount - parentProfit;
                amount = parentProfit;

                node.profit += myProfit;
                if("-".equals(node.parent)) break; // 최상위 부모면 스톱
                if (parentProfit == 0) break; // 부모에 더이상 전달한 이익이 없다면 스톱

                node = enrollMap.get(node.parent); // 부모가 있다면 이익 전달
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
