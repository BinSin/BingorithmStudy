package exam.nhn_2022_05_29;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class Solution02 {

    static class Goods {
        int userId;
        int balance;

        public Goods(int userId, int balance) {
            this.userId = userId;
            this.balance = balance;
        }
    }

    public static int[] solution(int[] balance, int[][] transaction, int[] abnormal) {
        List<Stack<Goods>> userGoodsList = new ArrayList<>();
        for (int i=0; i<balance.length; i++) {
            Stack<Goods> stack = new Stack<>();
            stack.add(new Goods(i, balance[i]));
            userGoodsList.add(stack);
        }

        for (int i=0; i<transaction.length; i++) {
            int fromUserId = transaction[i][0] - 1;
            int toUserId = transaction[i][1] - 1;
            int totalBalance = transaction[i][2];

            Stack<Goods> fromUserStack = userGoodsList.get(fromUserId);
            Stack<Goods> toUserStack = userGoodsList.get(toUserId);
            while (totalBalance != 0) {
                Goods fromUserGoods = fromUserStack.pop();
                int curUserId = fromUserGoods.userId;
                int curBalance = fromUserGoods.balance;
                Goods moveGoods;

                if (totalBalance >= curBalance) { // curBalance 만큼만 옮김
                    moveGoods = new Goods(curUserId, curBalance);
                    totalBalance -= curBalance;
                } else { // 나머지 부분을 다시 원래 stack 에 쌓음
                    moveGoods = new Goods(curUserId, totalBalance);
                    curBalance -= totalBalance;
                    totalBalance = 0;
                    fromUserStack.add(new Goods(curUserId, curBalance));
                }

                if (!toUserStack.isEmpty()) {
                    if (toUserStack.peek().userId == curUserId) {
                        Goods prevUserStack = toUserStack.pop();
                        toUserStack.add(new Goods(curUserId, prevUserStack.balance + moveGoods.balance));
                    } else {
                        toUserStack.add(moveGoods);
                    }
                } else {
                    toUserStack.add(moveGoods);
                }
            }
        }

        int[] answer = new int[userGoodsList.size()];
        List<Integer> abnormalUserIdList = Arrays.stream(abnormal).boxed().map(e -> e-1).collect(Collectors.toList());
        for (int i=0; i<balance.length; i++) {
            int totalBalance = 0;
            Stack<Goods> stack = userGoodsList.get(i);
            for (Goods g : stack) {
                if (!abnormalUserIdList.contains(g.userId)) {
                    totalBalance += g.balance;
                }
            }
            answer[i] = totalBalance;
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] balance1 = {30, 30, 30, 30};
        int[][] transaction1 = {{1, 2, 10}, {2, 3, 20}, {3, 4, 5}, {3, 4, 30}};
        int[] abnormal1 = {1};
        System.out.println(Arrays.stream(solution(balance1, transaction1, abnormal1)).boxed().collect(Collectors.toList()));

        int[] balance2 = {30, 30, 30, 30};
        int[][] transaction2 = {{1, 2, 10}, {2, 3, 20}, {3, 4, 5}, {3, 4, 30}};
        int[] abnormal2 = {2, 3};
        System.out.println(Arrays.stream(solution(balance2, transaction2, abnormal2)).boxed().collect(Collectors.toList()));

        int[] balance3 = {40, 30, 50};
        int[][] transaction3 = {{1, 3, 20}, {2, 1, 10}, {3, 1, 45}, {2, 3, 10}, {1, 3, 35}, {2, 1, 5}, {3, 1, 10},{3, 2, 5}};
        int[] abnormal3 = {2};
        System.out.println(Arrays.stream(solution(balance3, transaction3, abnormal3)).boxed().collect(Collectors.toList()));

        int[] balance4 = {100, 1, 1, 1, 1};
        int[][] transaction4 = {{1, 2, 100}, {2, 3, 101}, {3, 4, 102}, {4, 5, 103},{5, 1, 104}};
        int[] abnormal4 = {1};
        System.out.println(Arrays.stream(solution(balance4, transaction4, abnormal4)).boxed().collect(Collectors.toList()));
    }

}
