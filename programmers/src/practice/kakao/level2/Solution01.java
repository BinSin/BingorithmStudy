/*
https://programmers.co.kr/learn/courses/30/lessons/60057?language=java
 */

package practice.kakao.level2;

import java.util.stream.Stream;

public class Solution01 {

    public static int solution(String s) {
        int answer = s.length();

        for(int i=1; i<=s.length()/2; i++) {
            int num = s.length();

            String curS = s.substring(0, i);
            boolean flag = false;
            int count = 1;
            for(int j=i; j+i<=s.length();) {
                String nextS = s.substring(j, j+i);

                if(curS.equals(nextS)) {
                    if(flag) {
                        num -= i;
                    } else {
                        num -= (i - 1);
                        flag = true;
                    }
                    count++;
                } else {
                    flag = false;
                    num += (int) Math.log10(count);
                    count = 1;
                }
                j += i;

                curS = nextS;
            }
            num += (int) Math.log10(count);
            answer = Math.min(answer, num);
        }
        return answer;
    }

    public static void main(String[] args) {
        Stream<String> stream = Stream.of("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxy", "ababcdcdababcdcd", "abcabcdede", "abcabcabcabcdededededede", "xababcdcdababcdcd");
        stream.forEach(s -> System.out.println(solution(s)));
    }
}
