package kakaobank_2022_03_19;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution04 {

    private class Directory {
        String name;
        List<String> child;

        public Directory(String name, List<String> child) {
            this.name = name;
            this.child = child;
        }
    }

    public static String[] solution(String[] directory, String[] command) {
        List<String> nameList = new ArrayList<>();

        /*for (String d : directory) {
            String[] split = d.split("/");
            for (String s : split) {
                if (!nameList.contains(s)) {
                    nameList.add(s);
                }
            }
            if(nameList.contains())
             = d.substring(0, d.length()).split("/")
        }*/



        String[] answer = {};
        return answer;
    }

    public static void main(String[] args) {
        String[] directory1 = {"/",
                "/hello",
                "/hello/tmp",
                "/root",
                "/root/abcd",
                "/root/abcd/etc",
                "/root/abcd/hello"};
        String[] command1 = {"mkdir /root/tmp",
                "cp /hello /root/tmp",
                "rm /hello"};
        System.out.println(Arrays.toString(solution(directory1, command1)));
        /*
        [
"/",
"/root",
"/root/abcd",
"/root/abcd/etc",
"/root/abcd/hello",
"/root/tmp",
"/root/tmp/hello",
"/root/tmp/hello/tmp"
]
         */

        String[] directory2 = {"/"};
        String[] command2 = {"mkdir /a",
                "mkdir /a/b",
                "mkdir /a/b/c",
                "cp /a/b /",
                "rm /a/b/c"};
        System.out.println(Arrays.toString(solution(directory2, command2)));
        /*
        [
"mkdir /a",
"mkdir /a/b",
"mkdir /a/b/c",
"cp /a/b /",
"rm /a/b/c"
]
         */
    }

}
