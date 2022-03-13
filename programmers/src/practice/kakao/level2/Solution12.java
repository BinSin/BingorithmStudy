/*
후보키
https://programmers.co.kr/learn/courses/30/lessons/42890
 */

package practice.kakao.level2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution12 {

    // 최소성을 만족하는지 검사
    private static boolean check(List<Integer> candidateKeyList, int b) {
        for (Integer key : candidateKeyList) {
            if ((key & b) == key) return false;
        }
        return true;
    }

    public static int solution(String[][] relation) {
        int n = relation.length;
        int m = relation[0].length;

        List<Integer> candidateKeyList = new ArrayList<>();
        for (int b=0; b<(1<<m); b++) {
            if (!check(candidateKeyList, b)) continue;

            Set<String> set = new HashSet<>();
            for (int i=0; i<n; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j=0; j<m; j++) {
                    if (((1<<j) & b) > 0) sb.append(relation[i][j]).append(" ");
                }
                if(!set.add(sb.toString())) break;
            }

            if(set.size() == n) candidateKeyList.add(b);
        }
        return candidateKeyList.size();
    }

    public static void main(String[] args) {
        String[][] relation = {{"100","ryan","music","2"},{"200","apeach","math","2"},{"300","tube","computer","3"},{"400","con","computer","4"},{"500","muzi","music","3"},{"600","apeach","music","2"}};
        System.out.println(solution(relation));
    }

}
