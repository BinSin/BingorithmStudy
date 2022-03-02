package practice.kakao.level1;

import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class Solution05 { // 숫자 문자열과 영단어

    public static Map<String, Integer> numberStringMap = Map.of("zero", 0, "one", 1, "two", 2, "three", 3, "four", 4, "five", 5, "six", 6, "seven", 7, "eight", 8, "nine", 9);

    public static int solution(String s) {
        AtomicReference<String> answer = new AtomicReference<>(s);
        numberStringMap.forEach((k, v) -> {
            if (answer.get().contains(k)) {
                answer.set(answer.get().replaceAll(k, String.valueOf(v)));
            }
        });
        return Integer.parseInt(answer.get());
    }

     public static void main(String[] args) {
        String s1 = "one4seveneight";
        System.out.println(solution(s1));

        String s2 = "23four5six7";
        System.out.println(solution(s2));

        String s3 = "2three45sixseven";
        System.out.println(solution(s3));

        String s4 = "123";
        System.out.println(solution(s4));

     }
}
