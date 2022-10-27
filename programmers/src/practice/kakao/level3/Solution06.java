/*
BinSin
https://school.programmers.co.kr/learn/courses/30/lessons/92344
분류: 누적합
풀이: https://tech.kakao.com/2022/01/14/2022-kakao-recruitment-round-1/
 */

package practice.kakao.level3;

public class Solution06 {

    private static int countOfLive(int[][] board, int[][] cumulativeSumArray) {
        int count = 0;
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board[0].length; j++) {
                if (board[i][j] + cumulativeSumArray[i][j] > 0) count++;
            }
        }
        return count;
    }

    private static void makeCumulativeSumArray(int[][] cumulativeSumArray) {
        for (int i=0; i<cumulativeSumArray.length; i++) {
            for(int j=1; j<cumulativeSumArray[0].length; j++) {
                cumulativeSumArray[i][j] += cumulativeSumArray[i][j-1];
            }
        }

        for (int i=0; i<cumulativeSumArray[0].length; i++) {
            for(int j=1; j<cumulativeSumArray.length; j++) {
                cumulativeSumArray[j][i] += cumulativeSumArray[j-1][i];
            }
        }
    }

    public static int solution(int[][] board, int[][] skill) {
        int[][] cumulativeSumArray = new int[board.length+1][board[0].length+1];

        for (int[] skillRow : skill) {
            int type = skillRow[0];
            int startX = skillRow[1];
            int startY = skillRow[2];
            int endX = skillRow[3] + 1;
            int endY = skillRow[4] + 1;
            int power = skillRow[5];
            if (type == 1) power *= -1;

            cumulativeSumArray[startX][startY] += power;
            cumulativeSumArray[endX][startY] -= power;
            cumulativeSumArray[startX][endY] -= power;
            cumulativeSumArray[endX][endY] += power;
        }

        makeCumulativeSumArray(cumulativeSumArray);

        return countOfLive(board, cumulativeSumArray);
    }

    public static void main(String[] args) {
        int[][] board1 = {{5,5,5,5,5},{5,5,5,5,5},{5,5,5,5,5},{5,5,5,5,5}};
        int[][] skill1 = {{1,0,0,3,4,4},{1,2,0,2,3,2},{2,1,0,3,1,2},{1,0,1,3,3,1}};
        System.out.println(solution(board1, skill1));

        int[][] board2 = {{1,2,3},{4,5,6},{7,8,9}};
        int[][] skill2 = {{1,1,1,2,2,4},{1,0,0,1,1,2},{2,2,0,2,0,100}};
        System.out.println(solution(board2, skill2));
    }

}