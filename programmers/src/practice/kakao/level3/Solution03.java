/*
브라이언의 고민
https://programmers.co.kr/learn/courses/30/lessons/1830?language=java
TODO: 아직 풀지 못함.. 나중에 풀어보자
 */

package practice.kakao.level3;

import java.util.HashSet;
import java.util.Set;

public class Solution03 {

    private static final String invalid = "invalid";

    public static String solution(String sentence) {
        StringBuilder sb = new StringBuilder();
        Set<Character> set = new HashSet<>(); // 이미 사용된 소문자인지 확인

        // invalid check (공백, inner, outer)
        char[] sentenceArr = sentence.toCharArray();

        char tmp;
        char type;
        if (Character.isLowerCase(sentenceArr[0])) {
            tmp = sentenceArr[0];
            type = 'O';
        } else if (Character.isLowerCase(sentenceArr[1])) {
            tmp = sentenceArr[1];
            type = 'I';
        } else {
            return invalid;
        }

        for (int i=0; i<sentence.length(); i++) {
            char c = sentenceArr[i];

            if (Character.isLowerCase(c)) { // 소문자일 경우
                if (set.contains(c)) return invalid;

                if (type == 'O') { // outer 의 경우
                    if (tmp == c) {
                        if (!set.add((tmp))) return invalid;
                        if(i != sentence.length() - 1) sb.append(" ");

                        if (i+1 < sentenceArr.length) {
                            if (Character.isUpperCase(sentenceArr[i + 1])) {
                                type = 'I';
                            }
                        }
                    }
                } else { // inner 의 경우
                    if (i + 1 < sentence.length()) {
                        if (Character.isLowerCase(sentenceArr[i + 1]))
                            return invalid;
                    }
                    if (i + 2 >= sentence.length()) continue;

                    if (c != sentenceArr[i+2]) {
                        if (!set.add((tmp))) return invalid;
                        sb.append(sentenceArr[i+1]).append(" ");
                        i += 1;
                        if (Character.isLowerCase(sentenceArr[i+1])) {
                            type = 'O';
                        }
                    }
                }
                tmp = c;
            } else if (Character.isUpperCase(c)) { // 대문자일 경우
                sb.append(c);
            } else { // 영문 소문자 대문자 그 외의 문자가 왔을 때 invalid
                return invalid;
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String sentence1 = "HaEaLaLaObWORLDb";
        System.out.println(solution(sentence1));

        String sentence2 = "SpIpGpOpNpGJqOqA";
        System.out.println(solution(sentence2));

        String sentence3 = "AxAxAxAoBoBoB";
        System.out.println(solution(sentence3));
    }

}
