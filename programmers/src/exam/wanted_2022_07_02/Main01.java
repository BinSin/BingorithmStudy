package exam.wanted_2022_07_02;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main01 {

    private static class Town {
        Integer monsterOfAttack;
        Integer numberOfResidents;

        public Town(Integer monsterOfAttack, Integer numberOfResidents) {
            this.monsterOfAttack = monsterOfAttack;
            this.numberOfResidents = numberOfResidents;
        }

        public Integer getMonsterOfAttack() {
            return monsterOfAttack;
        }

        public Integer getNumberOfResidents() {
            return numberOfResidents;
        }

    }

    public static void main(String[] args)  throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 몬스터의 수
        int K = Integer.parseInt(st.nextToken()); // 시루의 초기 체력

        List<Integer> A = Arrays.stream(br.readLine().split(" ")). // 몬스터의 공격력
                map(Integer::parseInt)
                .collect(Collectors.toList());

        List<Integer> P = Arrays.stream(br.readLine().split(" ")). // 주민의 수
                map(Integer::parseInt)
                .collect(Collectors.toList());

        List<Town> towns = new ArrayList<>();
        for (int i=0; i<N; i++) {
            if (A.get(i) > K) break; // 몬스터의 공격력이 시루의 초기 체력보다 높으면 리스트 넣지 않음
            towns.add(new Town(A.get(i), P.get(i)));
        }
        towns.sort((o1, o2) -> {
            if (!o1.getMonsterOfAttack().equals(o2.getMonsterOfAttack())) {
                return o1.getMonsterOfAttack() - o2.getMonsterOfAttack();
            } else {
                return o2.getNumberOfResidents() - o1.getNumberOfResidents();
            }
        });

        int answer = 0;
        int townsSize = towns.size();
        int[][] dp = new int[K + 1][K + 1]; // 공격력 / 누적 공격력
        for (int i=0; i<townsSize; i++) {
            Town town = towns.get(i);
            int monsterOfAttack = town.getMonsterOfAttack();
            int numberOfResidents = town.getNumberOfResidents();

            for (int  j=0; j<=K; j++) {
                int sumResidents = dp[monsterOfAttack][j];
                if (j+1 > K) break;
                dp[monsterOfAttack][j+1] = sumResidents + numberOfResidents;
                answer = Math.max(dp[monsterOfAttack][j+1], answer);
            }

        }

        bw.write(answer + "");
        bw.flush();
        bw.close();
        br.close();
    }
}

/*
5 5
6 6 1 5 6
1 1 100 200 1
 */