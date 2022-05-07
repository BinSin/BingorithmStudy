package exam.kakao_2022_05;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Solution04 {

    public static int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = {};
        return answer;
    }

    public static void main(String[] args) {
        int n1 = 6;
        int[][] paths1 = {{1, 2, 3}, {2, 3, 5}, {2, 4, 2}, {2, 5, 4}, {3, 4, 4}, {4, 5, 3}, {4, 6, 1}, {5, 6, 1}};
        int[] gates1 = {1, 3};
        int[] summits1 = {5};
        System.out.println(Arrays.stream(solution(n1, paths1, gates1, summits1)).mapToObj(String::valueOf).collect(Collectors.joining(","))); // {5, 3}

        int n2 = 7;
        int[][] paths2 = {{1, 4, 4}, {1, 6, 1}, {1, 7, 3}, {2, 5, 2}, {3, 7, 4}, {5, 6, 6}};
        int[] gates2 = {1};
        int[] summits2 = {2, 3, 4};
        System.out.println(Arrays.stream(solution(n2, paths2, gates2, summits2)).mapToObj(String::valueOf).collect(Collectors.joining(","))); // {3, 4}

        int n3 = 7;
        int[][] paths3 = {{1, 2, 5}, {1, 4, 1}, {2, 3, 1}, {2, 6, 7}, {4, 5, 1}, {5, 6, 1}, {6, 7, 1}};
        int[] gates3 = {1, 3};
        int[] summits3 = {5};
        System.out.println(Arrays.stream(solution(n3, paths3, gates3, summits3)).mapToObj(String::valueOf).collect(Collectors.joining(","))); // {5, 1}

        int n4 = 5;
        int[][] paths4 = {{1, 3, 10}, {1, 4, 20}, {2, 3, 4}, {2, 4, 6}, {3, 5, 20}, {4, 5, 6}};
        int[] gates4 = {1, 2};
        int[] summits4 = {5};
        System.out.println(Arrays.stream(solution(n4, paths4, gates4, summits4)).mapToObj(String::valueOf).collect(Collectors.joining(","))); // {5, 6}
    }

}
