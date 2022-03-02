package practice.kakao.level1;

import java.util.ArrayList;
import java.util.List;

public class Solution07 { // 다트 게임

    public static int solution(String dartResult) {
        List<Integer> list = new ArrayList<>();
        int number = 0;
        int index = 0;
        for (int i=0; i<dartResult.length(); i++) {
            char c = dartResult.charAt(i);
            if (c >= '0' && c <= '9') {
                number = c - '0';
                if (number == 1) {
                    if(dartResult.charAt(i + 1) == '0') {
                        number = 10;
                        i++;
                    }
                }
            } else if (c == 'S') {
                index++;
                list.add(number);
            } else if (c == 'D') {
                index++;
                list.add((int) Math.pow(number, 2));
            } else if (c == 'T') {
                index++;
                list.add((int) Math.pow(number, 3));
            } else if (c == '*') {
                list.set(index - 1, list.get(index - 1) * 2);
                if(index > 1) {
                    list.set(index - 2, list.get(index - 2) * 2);
                }
            } else if (c == '#') {
                list.set(index - 1, list.get(index - 1) * (-1));
            }
        }
        return list.stream().mapToInt(Integer::valueOf).sum();
    }

    public static void main(String[] args) {
        String dartResult1 = "1S2D*3T";
        System.out.println(solution(dartResult1));

        String dartResult2 = "1D2S#10S";
        System.out.println(solution(dartResult2));

        String dartResult3 = "1D2S0T";
        System.out.println(solution(dartResult3));

        String dartResult4 = "1S*2T*3S";
        System.out.println(solution(dartResult4));

        String dartResult5 = "1D#2S*3S";
        System.out.println(solution(dartResult5));

        String dartResult6 = "1T2D3D#";
        System.out.println(solution(dartResult6));

        String dartResult7 = "1D2S3T*";
        System.out.println(solution(dartResult7));

    }
}
