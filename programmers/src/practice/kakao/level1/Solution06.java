package practice.kakao.level1;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Solution06 { // [1차] 비밀지도

    public static String convertFormat(int n, Long number) {
        return String.format("%0" + n + "d", number);
    }

    public static String convertNumber(String str) {
        str = str.replaceAll("0", " ");
        str = str.replaceAll("1", "#");
        return str;
    }

    public static String[] solution(int n, int[] arr1, int[] arr2) {
        Long[] arr = new Long[n];
        for(int i=0; i<n; i++) {
            arr[i] = (long) (arr1[i] | arr2[i]);
        }
        return Arrays.stream(arr)
            .map(Long::toBinaryString)
            .map(Long::parseLong)
            .map(number -> convertFormat(n, number))
            .map(Solution06::convertNumber) // 프로그래머스에는 str -> convertNumber(str) 로 변경 필요
            .collect(Collectors.toList()).toArray(String[]::new);
    }

    public static void main(String[] args) {
        int n1 = 5;
        int[] arr11 = new int[] {9, 20, 28, 18, 11};
        int[] arr21 = new int[] {30, 1, 21, 17, 28};
        Arrays.stream(solution(n1, arr11, arr21)).forEach(System.out::println);
        System.out.println();

        int n2 = 6;
        int[] arr12 = new int[] {46, 33, 33 ,22, 31, 50};
        int[] arr22 = new int[] {27 ,56, 19, 14, 14, 10};
        Arrays.stream(solution(n2, arr12, arr22)).forEach(System.out::println);
        System.out.println();
    }

}
