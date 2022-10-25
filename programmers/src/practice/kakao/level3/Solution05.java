/*
BinSin
https://school.programmers.co.kr/learn/courses/30/lessons/64064
분류: 수열, 정규식
 */

package practice.kakao.level3;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution05 {

    private static int count;
    private static Set<String> set;

    private static boolean canEquals(String[] user_id, String[] banned_id) {
        // 불량 아이디 판별
        for (int i=0; i< banned_id.length; i++) {
            String user = user_id[i];
            String ban = banned_id[i];

            // 성능은 좋지 않지만 가독성이 높아진다.
            String reg = banned_id[i].replace("*", "[\\w\\d]");
            if (!user_id[i].matches(reg)) return false;

            // 하기 방식이 정석..
            /*
            if (user.length() != ban.length()) return false;

            for (int j = 0; j < ban.length(); j++) {
                if (ban.charAt(j) == '*') continue;
                if (ban.charAt(j) != user.charAt(j)) return false;
            }
            */
        }

        // 이미 등록된 목록인지 체크
        StringBuilder sb = new StringBuilder();
        String[] userIdArr = user_id.clone();
        Arrays.sort(userIdArr);
        for (String userId : userIdArr) sb.append(userId);

        return set.add(sb.toString()); // 이미 등록된 목록이면 계산하지 않음
    }

    // 순서 없이 n 개중에서 r 개를 뽑는 경우
    private static void permutation(String[] user_id, String[] compare_user_id, String[] banned_id, boolean[] visited, int depth, int n, int r) {
        if (depth == r) {
            if(canEquals(compare_user_id, banned_id)) count++;
            return;
        }

        for (int i=0; i<n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                compare_user_id[depth] = user_id[i];
                permutation(user_id, compare_user_id, banned_id, visited, depth + 1, n, r);
                visited[i] = false;
            }
        }
    }

    public static int solution(String[] user_id, String[] banned_id) {
        count = 0;
        set = new HashSet<>();
        String[] compare_user_id = new String[banned_id.length];
        boolean[] visited = new boolean[user_id.length];

        permutation(user_id, compare_user_id, banned_id, visited, 0, user_id.length, banned_id.length);

        return count;
    }

    public static void main(String[] args) {
        String[][] user_id = {{"frodo", "fradi", "crodo", "abc123", "frodoc"}, {"frodo", "fradi", "crodo", "abc123", "frodoc"}, {"frodo", "fradi", "crodo", "abc123", "frodoc"}};
        String[][] banned_id = {{"fr*d*", "abc1**"}, {"*rodo", "*rodo", "******"}, {"fr*d*", "*rodo", "******", "******"}};
        for (int i=0; i<3; i++) System.out.println(solution(user_id[i], banned_id[i]));
    }
}
