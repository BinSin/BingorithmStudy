/*
파일명 정렬
https://programmers.co.kr/learn/courses/30/lessons/17686
 */

package practice.kakao.level2;

import java.util.*;

public class Solution17 {

    public static String[] split(String s) {
        String[] sArr = new String[2];

        int len = s.length();
        int i = 0;
        for (; i<len; i++) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') break;
        }
        sArr[0] = s.substring(0, i).toLowerCase();

        int j = i;
        for (; j<len; j++) {
            if (s.charAt(j) < '0' || s.charAt(j) > '9') break;
        }
        sArr[1] = s.substring(i, j);
        return sArr;
    }

    public static String[] solution(String[] files) {
        Arrays.sort(files, (o1, o2) -> {
            String[] split1 = split(o1);
            String head1 = split1[0];
            int number1 = Integer.parseInt(split1[1]);

            String[] split2 = split(o2);
            String head2 = split2[0];
            int number2 = Integer.parseInt(split2[1]);

           if (head1.compareTo(head2) != 0) {
               return head1.compareTo(head2);
           } else {
               return number1 - number2;
           }
        });
        return files;
    }

    public static void main(String[] args) {
        String[] files1 = {"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"};
        System.out.println(Arrays.toString(solution(files1)));

        String[] files2 = {"F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat"};
        System.out.println(Arrays.toString((solution(files2))));
    }
}