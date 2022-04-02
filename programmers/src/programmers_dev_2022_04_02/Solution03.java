package programmers_dev_2022_04_02;

public class Solution03 {

    public static int solution(int n, int[][] edges, int k, int a, int b) {
        int answer = -1;
        return answer;
    }

    public static void main(String[] args) {
        int n = 8;
        int[][] edges = {{0,1},{1,2},{2,3},{4,0},{5,1},{6,1},{7,2},{7,3},{4,5},{5,6},{6,7}};
        int k = 4;
        int a = 0;
        int b = 3;
        System.out.println(solution(n, edges, k, a, b) == 7);
    }
}
