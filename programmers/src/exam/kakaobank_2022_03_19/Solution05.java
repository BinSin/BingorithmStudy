package exam.kakaobank_2022_03_19;

import java.util.List;
import java.util.Stack;

public class Solution05 {

    // 괄호에 대하여 동일한 인덱스로 조회할 수 있도록 아래와 같이 세팅
    private static final List<Character> openBracket = List.of('[', '{', '(');
    private static final List<Character> closeBracket = List.of(']', '}', ')');

    // 올바른 괄호인지 체크
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
        // 먼저 빠진 괄호를 찾기 위해 각각의 괄호의 갯수를 count 했다.
        // 여는 괄호는 더하고 닫는 괄호는 더함으로써 어떤 괄호가 부족한지 구하였다.

        int[] count = new int[3]; // count 배열에는 대괄호는 0, 중괄호는 1, 소괄호는 2 가 오도록 정한다.
        int len = s.length();
        // 어떤 괄호가 부족한지 기록하는 배역
        // 0이 아닌 부분이
        for (int i=0; i<len; i++) {
            char c = s.charAt(i);
            if (openBracket.contains(c)) { // 여는 괄호는 +, 닫는 괄호는 - 하여 부족한 괄호를 찾는다.
                count[openBracket.indexOf(c)]++;
            } else {
                count[closeBracket.indexOf(c)]--;
            }
        }

        // 빠진 괄호 세팅
        char remainC = 0;
        for (int i=0; i<3; i++) {
            if (count[i] > 0) { // 여는 괄호가 더 있으므로 닫는 괄호가 필요함
                remainC = closeBracket.get(i);
            } else if (count[i] < 0){ // 닫는 괄호가 더 있으므로 여는 괄호가 필요함
                remainC = openBracket.get(i);
            }
        }

        int answer = 0;
        for (int i=0; i<s.length()+1; i++) { // 필요한 괄호를 사이사이 추가하면서 확인
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
