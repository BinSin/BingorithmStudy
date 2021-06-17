/*
https://programmers.co.kr/learn/courses/30/lessons/64061?language=java
 */
package kakao;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Solution01 {

    public static int solution(int[][] board, int[] moves) {
        int answer = 0;
        int r = board.length;
        int c = board[0].length;
        Queue<Integer>[] queues = new LinkedList[c];
        for(int i=0; i<c; i++) queues[i] = new LinkedList<>();
        for(int i=0; i<r; i++) {
            for(int j=0; j<c; j++) {
                int doll = board[i][j];
                if(doll != 0) queues[j].add(doll);
            }
        }

        Stack<Integer> bucket = new Stack<>();
        int s =  queues[moves[0] - 1].poll();
        bucket.add(s);
        int moveLen = moves.length;
        for(int i=1; i<moveLen; i++) {
            int move = moves[i] - 1;
            if(queues[move].isEmpty()) continue;
            int doll = queues[move].poll();

            if(bucket.isEmpty()) {
                bucket.push(doll);
                continue;
            }

            if(bucket.peek() == doll) {
                bucket.pop();
                answer += 2;
            }
            else bucket.add(doll);
        }
        return answer;
    }

    public static void main(String[] args) {
        int[][] board = {{0,0,0,0,0},{0,0,1,0,3},{0,2,5,0,1},{4,2,4,4,2},{3,5,1,3,1}};
        int[] moves = {1,5,3,5,1,2,1,4};
        System.out.println(solution(board, moves));
    }

}
