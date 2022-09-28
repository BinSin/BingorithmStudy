package exam.kakao_2022_09;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Solution01 {

    private static LocalDate convertStringToLocalDate(String date) {
        int[] dateArr = Arrays.stream(date.split("\\.")).mapToInt(Integer::parseInt).toArray();
        return LocalDate.of(dateArr[0], dateArr[1], dateArr[2]);

    }

    private static int[] solution(String today, String[] terms, String[] privacies) {
        LocalDate lastDate = convertStringToLocalDate(today);

        Map<Character, Integer> termMap = new HashMap<>();
        for (String term : terms) {
            StringTokenizer st = new StringTokenizer(term);
            termMap.put(st.nextToken().charAt(0), Integer.parseInt(st.nextToken()));
        }

        List<Integer> list = new ArrayList<>();
        for (int i=0; i< privacies.length; i++) {
            String privacy = privacies[i];
            StringTokenizer st = new StringTokenizer(privacy);
            LocalDate currentDate = convertStringToLocalDate(st.nextToken());
            char kind = st.nextToken().charAt(0);
            int month = termMap.get(kind);

            if (lastDate.isAfter(ChronoUnit.MONTHS.addTo(currentDate, month).minusDays(1))) {
                list.add(i + 1);
            }
        }

        return list.stream().mapToInt(v -> v).toArray();
    }

    public static void main(String[] args) {
        String today1 = "2022.05.19";
        String[] terms1 = {"A 6", "B 12", "C 3"};
        String[] privacies1 = {"2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"};
        System.out.println(Arrays.toString(solution(today1, terms1, privacies1)));

        String today2 = "2020.01.01";
        String[] terms2 = {"Z 3", "D 5"};
        String[] privacies2 = {"2019.01.01 D", "2019.11.15 Z", "2019.08.02 D", "2019.07.01 D", "2018.12.28 Z"};
        System.out.println(Arrays.toString(solution(today2, terms2, privacies2)));
    }
}
