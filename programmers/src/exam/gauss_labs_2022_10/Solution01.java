/*
분류: 순열
 */
package exam.gauss_labs_2022_10;

import java.util.*;

public class Solution01 {

    private static int maxArea;

    private static class Shape {
        int width, height;

        Shape(int width, int height) {
            this.width = width;
            this.height = height;
        }
    }

    // 순서 없이 n 개중에서 r 개를 뽑는 경우
    private static void permutation(List<Shape> shapeList, Shape[] selectArr, boolean[] visited, int depth, int n, int r) {
        if (depth == r) {
            int area = Math.abs((selectArr[1].width + selectArr[0].width) * (selectArr[1].height - selectArr[0].height));
            maxArea = Math.max(maxArea, area);
            return;
        }

        for (int i=0; i<n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                selectArr[depth] = shapeList.get(i);
                permutation(shapeList, selectArr, visited, depth + 1, n, r);
                visited[i] = false;
            }
        }
    }

    public static int solution(int[] x, int[] y) {
        maxArea = 0;

        Map<Integer, Set<Integer>> map = new TreeMap<>();
        for (int i=0; i<x.length; i++) {
            map.computeIfAbsent(y[i], k -> new TreeSet<>());
            map.get(y[i]).add(x[i]);
        }

        List<Integer> keyList = new ArrayList<>(map.keySet());

        List<Shape> shapeList = new ArrayList<>();
        for (int i=0; i<keyList.size(); i++) {
            int nextHeight = keyList.get(i);
            Set<Integer> set = map.get(nextHeight);
            if (set.size() < 2) continue;

            Object[] arr = set.toArray();
            shapeList.add(new Shape((int) arr[arr.length - 1] - (int) arr[0], nextHeight));
        }

        if (shapeList.size() < 2) return 0;

        Shape[] selectShape = new Shape[2];
        boolean[] visited = new boolean[shapeList.size()];
        permutation(shapeList, selectShape, visited, 0, shapeList.size(), 2);

        return maxArea;
    }

    public static void main(String[] args) {
        int[] x1 = {2,3,4,4,7,6,3,9,9,6,5,8,6,4};
        int[] y1 = {5,9,5,1,3,1,3,3,8,7,10,9,9,8};
        System.out.println(solution(x1, y1));

        int[] x2 = {5,5,6,3,4,1,1,8,7,7};
        int[] y2 = {9,4,6,10,4,1,5,6,6,2};
        System.out.println(solution(x2, y2));
    }

}
