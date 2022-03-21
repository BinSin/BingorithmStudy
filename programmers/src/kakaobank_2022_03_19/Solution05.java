package kakaobank_2022_03_19;

import java.util.List;
import java.util.Stack;

public class Solution05 {

    private static final List<Character> openBracket = List.of('[', '{', '(');
    private static final List<Character> closeBracket = List.of(']', '}', ')');

    private static boolean check(String s) {
        Stack<Character> stack = new Stack<>();
        int len = s.length();
        for (int i=0; i<len; i++) {
            char c = s.charAt(i);
            if (openBracket.contains(c)) {
                stack.add(c);
            } else {
                if(stack.isEmpty()) return false;
                char open = stack.pop();
                char openClose = openBracket.get(closeBracket.indexOf(c));
                if (openClose != open) {
                    return false;
                }
            }
        }
        return true;
    }

    public static int solution(String s) {
        int[] count = new int[3];

        int len = s.length();
        for (int i=0; i<len; i++) {
            char c = s.charAt(i);
            if (openBracket.contains(c)) {
                count[openBracket.indexOf(c)]++;
            } else {
                count[closeBracket.indexOf(c)]--;
            }
        }

        char remainC = 0;
        for (int i=0; i<3; i++) {
            if (count[i] > 0) {
                remainC = closeBracket.get(i);
            } else if (count[i] < 0){
                remainC = openBracket.get(i);
            }
        }

        int answer = 0;
        for (int i=0; i<s.length()+1; i++) {
            String str = s.substring(0, i) + remainC + s.substring(i);
            if(check(str)) answer++;
        }

        return answer;
    }

    public static void main(String[] args) {
        String s1 = "[]([[]){}";
        System.out.println(solution(s1));

        String s2 = "{([()]))}";
        System.out.println(solution(s2));

        String s3 = "(()()()";
        System.out.println(solution(s3));
    }

}
