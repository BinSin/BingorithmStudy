package exam.kakao_2022_05;

import java.util.ArrayList;
import java.util.List;

public class Solution05 {

    // 연산 최소화 시키는 함수
    private static List<int[]> minimizationOperations(int row, int col, String[] operations) {
        List<int[]> list = new ArrayList<>();
        int maxRotate = (row - 1) * 2 + (col - 1) * 2;

        String prev = operations[0];
        int count = 0;
        for (String operation : operations) {
            if (prev.equals(operation)) {
                count++;
            } else {
                if (prev.equals("Rotate")) {
                    int shiftCount = count % row;
                    list.add(new int[] {1, shiftCount});
                } else {
                    int rotateCount = count % maxRotate;
                    list.add(new int[] {2, rotateCount});
                }
                count = 1;
            }
            prev = operation;
        }

        // 남은 연산 처리
        if (prev.equals("Rotate")) {
            int rotateCount = count % maxRotate;
            list.add(new int[] {1, rotateCount});
        } else {
            int shiftCount = count % row;
            list.add(new int[] {2, shiftCount});
        }

        // TODO: 시간 초과 되는 케이스가 무엇인지 찾기

        return list;
    }

    private static void rotate(int row, int col, int[][] rc, int count) {
        List<Integer> list = new ArrayList<>(); // 회전시 변경되는 리스트 추출
        for (int i=0; i<col-1; i++) list.add(rc[0][i]);
        for (int i=0; i<row-1; i++) list.add(rc[i][col-1]);
        for (int i=col-1; i>=1; i--) list.add(rc[row-1][i]);
        for (int i=row-1; i>=1; i--) list.add(rc[i][0]);

        List<Integer> tmpList = new ArrayList<>();
        int listSize = list.size();
        for (int i=0; i<listSize; i++) {
            tmpList.add(list.get((i + listSize - count) % listSize));
        }

        int index = 0;
        for (int i=0; i<col-1; i++) rc[0][i] = tmpList.get(index++);
        for (int i=0; i<row-1; i++) rc[i][col-1] = tmpList.get(index++);
        for (int i=col-1; i>=1; i--) rc[row-1][i] = tmpList.get(index++);
        for (int i=row-1; i>=1; i--) rc[i][0] = tmpList.get(index++);
    }

    private static int[][] shiftRow(int row, int col, int[][] rc, int count) {
        int[][] tmpArr = new int[row][col];
        for (int i=0; i<row; i++) {
            tmpArr[(i + count) % row] = rc[i];
        }
        return tmpArr;
    }

    public static int[][] solution(int[][] rc, String[] operations) {
        int row = rc.length;
        int col = rc[0].length;
        List<int[]> minimizedOperations = minimizationOperations(row, col, operations);
        for (int[] values : minimizedOperations) {
            int type = values[0];
            int count = values[1];
            switch (type) {
                case 1 -> rotate(row, col, rc, count); // 1일 때는 회전
                case 2 -> rc = shiftRow(row, col, rc, count); // 2일 때는 내리기
            }
        }
        return rc;
    }

    private static void printSolution(int[][] solution) {
        for (int[] ints : solution) {
            for (int j = 0; j < solution[0].length; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        int[][] rc1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        String[] operations1 = {"Rotate","ShiftRow"};
        printSolution(solution(rc1, operations1));

        int[][] rc2 = {{8, 6, 3}, {3, 3, 7}, {8, 4, 9}};
        String[] operations2 = {"Rotate", "ShiftRow", "ShiftRow"};
        printSolution(solution(rc2, operations2));

        int[][] rc3 = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        String[] operations3 = {"ShiftRow", "Rotate", "ShiftRow", "Rotate"};
        printSolution(solution(rc3, operations3));

        // 시간 초과 3케이스에서 발생 무엇이지..
    }
}
