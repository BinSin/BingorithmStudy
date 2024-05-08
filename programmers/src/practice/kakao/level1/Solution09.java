/*
BinSin
https://school.programmers.co.kr/learn/courses/30/lessons/118666?language=java
 */

package practice.kakao.level1;

import java.util.HashMap;
import java.util.Map;

public class Solution09 {

  public static String solution(String[] survey, int[] choices) {
    Map<Character, Integer> typeMap = new HashMap<>();

    int length = survey.length;
    for (int i = 0; i < length; i++) {
      char type1 = survey[i].charAt(0);
      char type2 = survey[i].charAt(1);

      int value = choices[i];
      if (value <= 3) {
        typeMap.put(type1, typeMap.getOrDefault(type1, 0) + 4 - value);
      } else if (value >= 5) {
        typeMap.put(type2, typeMap.getOrDefault(type2, 0) + value - 4);
      }
    }

    return getPersonalityType(typeMap);
  }

  private static String getPersonalityType(Map<Character, Integer> typeMap) {
    StringBuilder sb = new StringBuilder();

    int valueOfR = typeMap.getOrDefault('R', 0);
    int valueOfT = typeMap.getOrDefault('T', 0);
    int valueOfC = typeMap.getOrDefault('C', 0);
    int valueOfF = typeMap.getOrDefault('F', 0);
    int valueOfJ = typeMap.getOrDefault('J', 0);
    int valueOfM = typeMap.getOrDefault('M', 0);
    int valueOfA = typeMap.getOrDefault('A', 0);
    int valueOfN = typeMap.getOrDefault('N', 0);

    if (valueOfR >= valueOfT) {
      sb.append('R');
    } else {
      sb.append('T');
    }

    if (valueOfC >= valueOfF) {
      sb.append('C');
    } else {
      sb.append('F');
    }

    if (valueOfJ >= valueOfM) {
      sb.append('J');
    } else {
      sb.append('M');
    }

    if (valueOfA >= valueOfN) {
      sb.append('A');
    } else {
      sb.append('N');
    }

    return sb.toString();
  }

  public static void main(String[] args) {
    String[] survey1 = {"AN", "CF", "MJ", "RT", "NA"};
    int[] choices1 = {5, 3, 2, 7, 5};
    System.out.println(solution(survey1, choices1));
    System.out.println(solution(survey1, choices1).equals("TCMA"));

    String[] survey2 = {"TR", "RT", "TR"};
    int[] choices2 = {7, 1, 3};
    System.out.println(solution(survey2, choices2));
    System.out.println(solution(survey2, choices2).equals("RCJA"));
  }
}
