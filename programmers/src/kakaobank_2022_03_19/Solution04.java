package kakaobank_2022_03_19;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.concurrent.atomic.LongAccumulator;

public class Solution04 {

    private static class TreeNode {
        String name;
        TreeNode parent;
        List<TreeNode> child;

        public TreeNode(String name) {
            this.name = name;
            this.parent = null;
            this.child = new ArrayList<>();
        }

        public void setParent(TreeNode treeNode) {
            this.parent = treeNode;
        }

        public TreeNode addChild(String childName) {
            TreeNode child = new TreeNode(childName);
            child.setParent(this);
            this.child.add(child);
            return child;
        }

        public TreeNode addChilds(String childName, List<TreeNode> childNode) {
            TreeNode child = new TreeNode(childName);
            child.setParent(this);
            this.child.addAll(childNode);
            return child;
        }

        public void remove(Map<String, TreeNode> map) {
            map.remove(this.name);

            if(this.child.isEmpty()) {
                return;
            }

            for (TreeNode childNode : this.child) {

            }

            child.clear();
        }

    }

    private static String[] getNames(String dir) {
        String parentName = dir.substring(0, dir.lastIndexOf("/"));
        parentName = parentName.isBlank() ? "/" : parentName;
        String addChildName = dir.substring(dir.lastIndexOf("/") + 1);
        return new String[] {parentName, addChildName};
    }

    private static void mkdir(Map<String, TreeNode> map, String dir) {
        String[] names = getNames(dir);
        String parentName = names[0];
        String addChildName = names[1];

        TreeNode parentNode = map.get(parentName);
        TreeNode childNode = parentNode.addChild(addChildName);
        map.put(dir, childNode);
    }

    private static void cp(Map<String, TreeNode> map, String dir, String cpDir) {
        TreeNode curNode = map.get(dir);
        List<TreeNode> childNode = curNode.child;

        String newName = cpDir + dir;
        TreeNode cpNode = map.get(cpDir);
        cpNode.addChilds(newName, childNode);

        for (TreeNode treeNode : cpNode.child) {
            map.put(cpDir + treeNode.name, treeNode);
        }
    }

    private static void rm(Map<String, TreeNode> map, String dir) {
        TreeNode curNode = map.get(dir);
        curNode.remove(map);
    }

    public static String[] solution(String[] directory, String[] command) {
        TreeNode root = new TreeNode("/");

        Map<String, TreeNode> map = new TreeMap<>(String::compareTo);
        map.put("/", root);
        for (int i=1; i<directory.length; i++) {
            mkdir(map, directory[i]);
        }

        for (String c : command) {
            StringTokenizer st = new StringTokenizer(c);
            String method = st.nextToken();
            String dir = st.nextToken();
            switch (method) {
                case "mkdir" -> mkdir(map, dir);
                case "cp" -> cp(map, dir, st.nextToken());
                case "rm" -> rm(map, dir);
            }
        }

        return map.keySet().toArray(new String[0]);
    }

    public static void main(String[] args) {
        String[] directory1 = {"/", "/hello",  "/hello/tmp", "/root", "/root/abcd", "/root/abcd/etc", "/root/abcd/hello"};
        String[] command1 = {"mkdir /root/tmp", "cp /hello /root/tmp", "rm /hello"};

        List<String> list1 = List.of("/",
            "/root",
            "/root/abcd",
            "/root/abcd/etc",
            "/root/abcd/hello",
            "/root/tmp",
            "/root/tmp/hello",
            "/root/tmp/hello/tmp");

        System.out.println(Arrays.toString(solution(directory1, command1)));

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
