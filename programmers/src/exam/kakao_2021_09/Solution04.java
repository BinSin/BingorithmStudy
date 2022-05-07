package exam.kakao_2021_09;

import java.util.stream.IntStream;

public class Solution04 {

    public static int[] solution(int n, int[] info) {
        int[] answer = {-1};



        return answer;
    }

    public static void main(String[] args) {
        int n1 = 5;
        int[] info1 = new int[] {2,1,1,1,0,0,0,0,0,0,0};
        IntStream.of(solution(n1, info1)).forEach(System.out::print);

        int n2 = 1;
        int[] info2 = new int[] {1,0,0,0,0,0,0,0,0,0,0};
        IntStream.of(solution(n2, info2)).forEach(System.out::print);

        int n3 = 9;
        int[] info3 = new int[] {0,0,1,2,0,1,1,1,1,1,1};
        IntStream.of(solution(n3, info3)).forEach(System.out::print);

        int n4 = 10;
        int[] info4 = new int[] {0,0,0,0,0,0,0,0,3,4,3};
        IntStream.of(solution(n4, info4)).forEach(System.out::print);
    }
}
