package exam.wanted_2022_07_02;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main02 {

    private static int maxGroupCount = 1;

    private static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static boolean isMapEquals(int[][] m, int[][] cloneM) {
        for (int i=0; i<7; i++) {
            for (int j=0; j<7; j++) {
                if (m[i][j] != cloneM[i][j]) return false;
            }
        }
        return true;
    }

    private static List<Pair> getBoomGroupPairs(int[][] map, List<Pair> locationOfBalls) {
        List<Pair> boomGroupNumbers = new ArrayList<>();
        for (Pair locationOfBall : locationOfBalls) {
            int x = locationOfBall.x;
            int y = locationOfBall.y;
            int groupNumber = map[x][y];

            int rowCount = -1;
            for (int i=x; i<7; i++) {
                if(map[i][y] == 0) break;
                rowCount++;
            }
            for (int i=x; i>=0; i--) {
                if(map[i][y] == 0) break;
                rowCount++;
            }
            if (rowCount == groupNumber) {
                boomGroupNumbers.add(locationOfBall);
                continue;
            }

            int colCount = -1;
            for (int i=y; i<7; i++) {
                if(map[x][i] == 0) break;
                colCount++;
            }
            for (int i=y; i>=0; i--) {
                if(map[x][i] == 0) break;
                colCount++;
            }
            if (colCount == groupNumber) {
                boomGroupNumbers.add(locationOfBall);
            }
        }
        return boomGroupNumbers;
    }

    private static void startMapDown(int[][] map) {
        for (int i=0; i<7; i++) {
            int location = 6;
            for (int j=6; j>=0 ;j--) {
                if (map[i][j] == 0) continue;
                map[location][i] = map[j][i];
                map[j][i] = 0;
                location--;
            }
        }
    }

    private static void startBoom(int[][] map, List<Pair> locationOfBalls) {
        List<Pair> boomGroupPairs = getBoomGroupPairs(map, locationOfBalls);

        for(Pair boomGroupPair : boomGroupPairs) {
            int x = boomGroupPair.x;
            int y = boomGroupPair.y;
            map[x][y] = 0;
        }

        startMapDown(map);
    }

    private static int[][] depthClone(int[][] map) {
        int[][] newMap = new int[7][7];
        for (int i=0; i<7; i++) {
            System.arraycopy(map[i], 0, newMap[i], 0, 7);
        }
        return newMap;
    }

    private static int getNumberOfBallsRemaining(int i, int n, int[][] map, List<Pair> locationOfBalls) {
        int location = 0;
        for (int j=0; j<7; j++) {
            if (map[j][i] == 0) continue;
            location = j - 1;
        }
        if (location == -1) return maxGroupCount;


        int[][] m = depthClone(map);
        m[location][i] = n;

        while (true) {
            int[][] cloneM = depthClone(m);

            startBoom(cloneM, locationOfBalls);

            if (isMapEquals(m, cloneM)) {
                break;
            }
        }

        int answer = 0;
        for (int j=0; j<7; j++) {
            answer += Arrays.stream(map[j]).filter(value -> value > 0).count();
        }
        return answer;
    }

    public static void main(String[] args)  throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        int[][] map = new int[7][7];
        List<Pair> locationOfBalls = new ArrayList<>();
        for (int i=0; i<7; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0; j<7; j++) {
                int groupNumber = Integer.parseInt(st.nextToken());
                map[i][j] = groupNumber;
                if (groupNumber > 0) {
                    maxGroupCount++;
                    locationOfBalls.add(new Pair(i, j));
                }
            }
        }
        int n = Integer.parseInt(br.readLine());

        int answer = 49;
        for (int i=0; i<7; i++) {
            answer = Math.min(answer, getNumberOfBallsRemaining(i, n, map, locationOfBalls));
        }

        bw.write(answer + "");
        bw.flush();
        bw.close();
        br.close();
    }

}
