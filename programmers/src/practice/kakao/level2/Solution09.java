package practice.kakao.level2;

import java.util.*;

public class Solution09 {

    public static long max = 0;
    public static List<Long> numberList;
    public static List<Character> operatorList;

    private static void operation(char[] answer, List<Long> nList, List<Character> oList) {
        for (char a : answer) {
            while(oList.contains(a)) {
                int index = oList.indexOf(a);
                long n1 = nList.get(index);
                long n2 = nList.get(index + 1);
                long oper = 0;

                switch (a) {
                    case '+' -> oper = n1 + n2;
                    case '-' -> oper = n1 - n2;
                    case '*' -> oper = n1 * n2;
                }
                nList.remove(index + 1);
                nList.remove(index);
                nList.add(index, oper);
                oList.remove(index);
            }
        }
        max = Math.max(max, Math.abs(nList.get(0)));
    }

    public static void max(Character[] operator, int size, int depth, boolean[] visited, char[] answer, long max) {
        if(depth == size) {
            operation(answer, new ArrayList<>(numberList), new ArrayList<>(operatorList));
            return;
        }
        for (int i=0; i<size; i++) {
            if(!visited[i]) {
                answer[depth] = operator[i];
                visited[i] = true;
                max(operator, size, depth + 1, visited, answer, max);
                visited[i] = false;
            }
        }
    }

    public static long solution(String expression) {
        numberList = new ArrayList<>();
        operatorList = new ArrayList<>();
        Set<Character> operatorSet = new HashSet<>();

        StringBuilder sb = new StringBuilder();
        for (int i=0; i<expression.length(); i++) {
            char c = expression.charAt(i);
            if (c < '0' || c > '9') {
                numberList.add(Long.parseLong(sb.toString()));
                operatorList.add(c);
                operatorSet.add(c);
                sb = new StringBuilder();
            }
            else sb.append(c);
        }
        numberList.add(Long.parseLong(sb.toString()));

        int size = operatorSet.size();
        boolean[] visited = new boolean[size];
        char[] answer = new char[size];
        max(operatorSet.toArray(new Character[0]), size, 0, visited, answer, 0);

        return max;
    }

    public static void main(String[] args) {
        String expression1 = "100-200*300-500+20";
        System.out.println(solution(expression1) == 60420);

        max = 0;
        numberList = new ArrayList<>();
        operatorList = new ArrayList<>();
        String expression2 = "50*6-3*2";
        System.out.println(solution(expression2) == 300);
    }
}
