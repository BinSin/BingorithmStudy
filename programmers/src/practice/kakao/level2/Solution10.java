/*
튜플
https://programmers.co.kr/learn/courses/30/lessons/64065
 */

package practice.kakao.level2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution10 {

    public static int[] solution(String s) {
        Set<String> list = new HashSet<>();
        String[] arr = s.replaceAll("[{]", " ").replaceAll("[}]", " ").trim().split(" , ");
        Arrays.sort(arr, Comparator.comparingInt(String::length));

        int[] answer = new int[arr.length];
        for (int i=0; i<arr.length; i++) {
            String a = arr[i];
            StringTokenizer st = new StringTokenizer(a, ",");
            while(st.hasMoreTokens()) {
                String next = st.nextToken();
                if(list.add(next)) answer[i] = Integer.parseInt(next);
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        String s1 = "{{2},{2,1},{2,1,3},{2,1,3,4}}";
        Arrays.stream(solution(s1)).mapToObj(v -> v + " ").forEach(System.out::print);
        System.out.println();

        String s2 = "{{1,2,3},{2,1},{1,2,4,3},{2}}";
        Arrays.stream(solution(s2)).mapToObj(v -> v + " ").forEach(System.out::print);
        System.out.println();

        String s3 = "{{20,111},{111}}";
        Arrays.stream(solution(s3)).mapToObj(v -> v + " ").forEach(System.out::print);
        System.out.println();

        String s4 = "{{123}}";
        Arrays.stream(solution(s4)).mapToObj(v -> v + " ").forEach(System.out::print);
        System.out.println();

        String s5 = "{{4,2,3},{3},{2,3,4,1},{2,3}}";
        Arrays.stream(solution(s5)).mapToObj(v -> v + " ").forEach(System.out::print);
        System.out.println();
    }

}
