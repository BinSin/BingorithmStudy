package practice.kakao.level1;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class Solution03 {

    public static String solution(String new_id) {
        String answer = new_id;
        // step 1
        answer = answer.toLowerCase();

        // step 2
        String match = "[^a-z0-9-_.]";
        answer = answer.replaceAll(match, "");

        // step 3 - 정규식 이용 가능 -> [.]{2,}
        char[] charArr = answer.toCharArray();
        StringBuilder sb = new StringBuilder();
        int index = 0;
        for(char c : charArr) {
            if(c == '.') {
                if(++index > 1) {
                    continue;
                }
            } else {
                index = 0;
            }
            sb.append(c);
        }
        answer = sb.toString();

        // step 4 - 정규식 이용 가능 -> ^[.]|[.]$
        int s = 0, e = answer.length();
        if(!answer.equals(".")) {
            if (answer.charAt(s) == '.')
                s++;
            if (answer.charAt(e - 1) == '.')
                e--;
            answer = answer.substring(s, e);
        } else {
            answer = "";
        }

        // step 5
        if(answer.isEmpty())
            answer = "a";

        // step 6
        if(answer.length() >= 16) {
            answer = answer.substring(0, 15);
            if (answer.charAt(14) == '.')
                answer = answer.substring(0, 14);
        }

        // step 7
        if(answer.length() <= 2) {
            char c = answer.charAt(answer.length() - 1);
            while (answer.length() < 3) {
                answer += c;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        String[] new_id = {"...!@BaT#*..y.abcdefghijklm", "z-+.^.", "=.=", "123_.def", "abcdefghijklmn.p"};
        Arrays.stream(new_id).map(Solution03::solution).forEach(System.out::println);
    }
}
