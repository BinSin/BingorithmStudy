package exam.lgcns_2022_11;

public class Solution03 {

    public static int solution(String reference, String track) {
        int length = reference.length();
        int answer = length;
        for (int end=length; end>0; end--) {
            for (int start=0; start+end<=length; start++) {
                String str = reference.substring(start, start + end);
                if (!track.contains(str)) continue;
                track = track.replaceAll(str, "");
                answer = Math.min(answer, str.length());
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        String reference1 = "abc", track1 = "bcab";
        System.out.println(solution(reference1, track1));

        String reference2 = "vxrvip", track2 = "xrviprvipvxrv";
        System.out.println(solution(reference2, track2));

        String reference3 = "abc", track3 = "abcabcabcbcabac";
        System.out.println(solution(reference3, track3));
    }
}
