/*
BinSin
https://school.programmers.co.kr/learn/courses/30/lessons/150370
 */

package practice.kakao.level1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution11 {

  private static DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("yyyy.MM.dd");

  public static int[] solution(String todayStr, String[] terms, String[] privacies) {
    List<Integer> answer = new ArrayList<>();

    LocalDate today = changeStringToLocalDate(todayStr);

    Map<String, Integer> termMap = initTermMap(terms);

    for (int i=0; i<privacies.length; i++) {
      String privacy = privacies[i];
      String[] privacySplit = privacy.split(" ");
      String dateStr = privacySplit[0];
      LocalDate date = changeStringToLocalDate(dateStr);

      String type = privacySplit[1];
      int months = termMap.get(type);

      LocalDate nextDate = date.plusMonths(months);

      if (!nextDate.isAfter(today)) {
        answer.add(i + 1);
      }
    }

    return answer.stream().mapToInt(x -> x).toArray();
  }

  private static LocalDate changeStringToLocalDate(String today) {
    int[] todaySplit = Arrays.stream(today.split("\\.")).mapToInt(Integer::parseInt).toArray();
    return LocalDate.of(todaySplit[0], todaySplit[1], todaySplit[2]);
  }

  // 내부적으로는 문자열을 분석하고, 해당 문자열이 유효한 날짜 형식을 따르는지 검증하는 과정을 거쳐 오버헤드가 더 발생함
  private static LocalDate changeStringToLocalDate2(String today) {
    return LocalDate.parse(today, formatter);
  }

  private static Map<String, Integer> initTermMap(String[] terms) {
    Map<String, Integer> termMap = new HashMap<>();
    for (String term : terms) {
      String[] termSplit = term.split(" ");
      String type = termSplit[0];
      int months = Integer.parseInt(termSplit[1]);
      termMap.put(type, months);
    }
    return termMap;
  }

  public static void main(String[] args) {
    String today1 = "2022.05.19";
    String[] terms1 = {"A 6", "B 12", "C 3"};
    String[] privacies1 = {"2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"};
    Arrays.stream(solution(today1, terms1, privacies1)).forEach(System.out::println); // 1, 3

    String today2 = "2020.01.01";
    String[] terms2 = {"Z 3", "D 5"};
    String[] privacies2 = {"2019.01.01 D", "2019.11.15 Z", "2019.08.02 D", "2019.07.01 D", "2018.12.28 Z"};
    Arrays.stream(solution(today2, terms2, privacies2)).forEach(System.out::println); // 1, 4, 5
  }

}
