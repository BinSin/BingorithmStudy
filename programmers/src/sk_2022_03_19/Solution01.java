package sk_2022_03_19;

import java.util.*;
import java.util.stream.Collectors;

public class Solution01 {

    private static boolean check(String str, List<String> productList) {
        for (String product : productList) {
            if (product.contains(str)) return false;
        }
        return true;
    }


    private static List<String> getSubStr(String s, int n) {
        List<String> list = new ArrayList<>();
        for (int i=0; i+n<=s.length(); i++) {
            list.add(s.substring(i, i+n));
        }
        return list;
    }

    public static String[] solution(String[] goods) {
        int len = goods.length;
        String[] answer = new String[len];
        Set<String> set = new HashSet<>();

        for (int i=0; i<len; i++) {
            Set<String> wordSet = new TreeSet<>(String::compareTo);
            String good = goods[i];
            int gLen = good.length();
            List<String> productList = Arrays.stream(goods).filter(v -> !v.equals(good)).collect(Collectors.toList());


            for (int j=1; j<=gLen; j++) { // 글자수
                List<String> subStrList = getSubStr(good, j);
                for (String subStr : subStrList) {
                    if (set.add(subStr)) {
                        if (check(subStr, productList)) {
                            wordSet.add(subStr);
                        }
                    }
                }

                if(wordSet.size() != 0) break;
            }

            if (wordSet.size() == 0) wordSet.add("None");
            answer[i] = String.join(" ", wordSet);
        }

        return answer;
    }

    public static void main(String[] args) {
        String[] goods1 = {"pencil","cilicon","contrabase","picturelist"};
        System.out.println(Arrays.toString(solution(goods1)));

        String[] goods2 = {"abcdeabcd","cdabe","abce","bcdeab"};
        System.out.println(Arrays.toString(solution(goods2)));

        String[] goods3 = {"ab", "cde", "dceba"};
        System.out.println(Arrays.toString(solution(goods3)));
    }

}
