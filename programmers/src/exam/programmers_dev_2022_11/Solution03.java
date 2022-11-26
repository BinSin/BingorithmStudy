package exam.programmers_dev_2022_11;

import java.util.*;

public class Solution03 {

    public static int solution(String[] subwayArr, int start, int end) {
        Map<Integer, List<Integer>> stationMap = new HashMap<>(); // 각 호선에 있는 역 저장
        int startLine = 0, endLine = 0;
        for (int line=0; line< subwayArr.length; line++) {
            StringTokenizer st = new StringTokenizer(subwayArr[line]);
            while(st.hasMoreTokens()) {
                int station = Integer.parseInt(st.nextToken());
                if (station == start) startLine = line;
                if (station == end) endLine = line;

                stationMap.computeIfAbsent(line, k -> new ArrayList<>());
                stationMap.get(line).add(station);
            }
        }

        // startLine -> endLine 으로 갈 수 있는 최소값 구하기
        Queue<Integer> q = new LinkedList<>();
        boolean[] isVisited = new boolean[stationMap.size()];
        int[] value = new int[stationMap.size()];
        q.add(startLine);
        isVisited[startLine] = true;
        while(!q.isEmpty()) {
            int next = q.remove();
            List<Integer> lineList = stationMap.get(next);
            if (lineList == null) continue;
            for (int line : lineList) {
                if (isVisited[line]) continue;
                q.add(line);
                isVisited[line] = true;
                value[line]++;
                if (line == endLine) return value[line];
            }
        }

        return value[endLine];
    }

    public static void main(String[] args) {
        String[] subway = {"1 2 3 4 5 6 7 8", "2 11", "0 11 10", "3 17 19 12 13 9 14 15 10", "20 2 21"};
        int start = 1, end = 9;
        System.out.println(solution(subway, start, end));

        String[] subway2 = {"1 2 3 4 5 6 7 8", "8 9", "0 11 10"};
        int start2 = 1, end2 =  10;
        System.out.println(solution(subway2, start2, end2));
    }
}
