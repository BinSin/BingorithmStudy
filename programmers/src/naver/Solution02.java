package naver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Solution02 {

    public static String[] solution(String s) {
        String rs = new StringBuilder(s).reverse().toString();
        List<String> list = new ArrayList<>();

        int sLen = s.length();
        int index = 0;
        while(index != sLen) {
            for (int i = index; i < sLen; i++) {
                StringBuilder sb = new StringBuilder(s.substring(index, i + 1));
                StringBuilder rsb = new StringBuilder(rs.substring(index, i + 1));
                rsb.reverse();
                if (sb.toString().equals(rsb.toString())) {
                    list.add(sb.toString());
                    index = i + 1;
                    break;
                }
            }
        }

        return list.toArray(new String[list.size()]);
    }

    public static void main(String[] args) {
        String s1 = "abcxyasdfasdfxyabc";
        String s2 = "abcxyqwertyxyabc";
        String s3 = "abcabcabcabc";
        String s4 = "llttaattll";
        String s5 = "zzzzzz";
        String s6 = "abcdef";
        String s7 = "abcdaddbcdabc";
        String s8 = "a";

        System.out.println(Arrays.stream(solution(s1)).collect(Collectors.joining(",")));
        System.out.println(Arrays.stream(solution(s2)).collect(Collectors.joining(",")));
        System.out.println(Arrays.stream(solution(s3)).collect(Collectors.joining(",")));
        System.out.println(Arrays.stream(solution(s4)).collect(Collectors.joining(",")));
        System.out.println(Arrays.stream(solution(s5)).collect(Collectors.joining(",")));
        System.out.println(Arrays.stream(solution(s6)).collect(Collectors.joining(",")));
        System.out.println(Arrays.stream(solution(s7)).collect(Collectors.joining(",")));
        System.out.println(Arrays.stream(solution(s8)).collect(Collectors.joining(",")));
    }
}
