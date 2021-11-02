/*
https://programmers.co.kr/learn/courses/30/lessons/67256
 */

package practice.kakao.level1;

public class Solution01 {

    static class Edge {
        int x, y;

        Edge(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static String solution(int[] numbers, String hand) {
        StringBuilder answer = new StringBuilder();
        Edge leftLoc = new Edge(3, 0);
        Edge rightLoc = new Edge(3, 2);
        for(int number : numbers) {
            switch (number) {
                case 1, 4, 7 -> {
                    leftLoc = new Edge(number / 3, 0);
                    answer.append("L");
                }
                case 3, 6, 9 -> {
                    rightLoc = new Edge(number / 3 - 1, 2);
                    answer.append("R");
                }
                default -> {
                    if (number == 0) number = 10;
                    int leftDiff = Math.abs(leftLoc.x - (number / 3)) + Math.abs(leftLoc.y - 1);
                    int rightDiff = Math.abs(rightLoc.x - (number / 3)) + Math.abs(rightLoc.y - 1);
                    if (leftDiff > rightDiff) {
                        rightLoc = new Edge(number / 3, 1);
                        answer.append("R");
                    } else if (leftDiff < rightDiff) {
                        leftLoc = new Edge(number / 3, 1);
                        answer.append("L");
                    } else {
                        if (hand.equals("left")) {
                            leftLoc = new Edge(number / 3, 1);
                            answer.append("L");
                        } else {
                            rightLoc = new Edge(number / 3, 1);
                            answer.append("R");
                        }
                    }
                }
            }
        }
        return answer.toString();
    }

    public static void main(String[] args) {
        int[] numbers1 = {1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5};
        String hand1 = "right";
        System.out.println(solution(numbers1, hand1));

        int[] numbers2 = {7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2};
        String hand2 = "left";
        System.out.println(solution(numbers2, hand2));

        int[] numbers3 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        String hand3 = "right";
        System.out.println(solution(numbers3, hand3));
    }
}
