package exam.naver;

public class Solution03 {

    public static int solution(String s, String t) {
        int count = 0;

        while(true) {
            if(!s.contains(t)) break;
            s = s.replaceFirst(t, "");
            count++;
        }

        return count;
    }

    public static void main(String[] args) {
        String s1 = "aabcbcd";
        String t1 = "abc";
        String s2 = "aaaaabbbbb";
        String t2 = "ab";

        String s3 = "aaaaaaaaaaaaaaaaaaaa";
        String t3 = "a/aa";
        String[] aa = t3.split("/");
        System.out.println(aa.length);

        System.out.println(solution(s1, t1));
        System.out.println(solution(s2, t2));
        System.out.println(solution(s3, t3));
    }
}
