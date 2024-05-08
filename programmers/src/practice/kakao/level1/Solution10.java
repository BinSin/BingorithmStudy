/*
BinSin
https://school.programmers.co.kr/learn/courses/30/lessons/258712
 */

package practice.kakao.level1;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Solution10 {

  public static class Person {
    private String giver;
    private String receiver;

    public Person(String giver, String receiver) {
      this.giver = giver;
      this.receiver = receiver;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Person person = (Person) o;
      return Objects.equals(giver, person.giver) && Objects.equals(receiver,
          person.receiver);
    }

    @Override
    public int hashCode() {
      return Objects.hash(giver, receiver);
    }
  }

  public static int solution(String[] friends, String[] gifts) {
    Map<Person, Integer> giftCount = new HashMap<>();
    initGiftCount(friends, giftCount);

    Map<String, Integer> giftExponent = new HashMap<>();
    initGiftExponent(friends, giftExponent);

    applyGift(gifts, giftCount, giftExponent);

    int answer = 0;
    for (String giver : friends) {
      int count = 0;
      for (String receiver : friends) {
        if (giver.equals(receiver)) continue;

        int giverCount = giftCount.get(new Person(giver, receiver));
        int receiverCount = giftCount.get(new Person(receiver, giver));

        if (giverCount > receiverCount) {
          count++;
        } else if (giverCount == receiverCount) {
          int giverExponent = giftExponent.get(giver);
          int receiverExponent = giftExponent.get(receiver);

          if (giverExponent > receiverExponent) {
            count++;
          }
        }
      }

      if (answer < count) answer = count;
    }
    return answer;
  }

  private static void initGiftCount(String[] friends, Map<Person, Integer> giftCount) {
    for (String giver : friends) {
      for (String receiver : friends) {
        if (giver.equals(receiver)) continue;

        giftCount.put(new Person(giver, receiver), 0);
      }
    }
  }

  private static void initGiftExponent(String[] friends, Map<String, Integer> giftExponent) {
    for (String friend : friends) {
      giftExponent.put(friend, 0);
    }
  }

  private static void applyGift(String[] gifts, Map<Person, Integer> giftCount, Map<String, Integer> giftExponent) {
    for (String gift : gifts) {
      String[] giftSplit = gift.split(" ");
      String giver = giftSplit[0];
      String receiver = giftSplit[1];
      Person person = new Person(giver, receiver);

      giftCount.put(person, giftCount.get(person) + 1);
      giftExponent.put(giver, giftExponent.get(giver) + 1);
      giftExponent.put(receiver, giftExponent.get(receiver) - 1);
    }
  }

  public static void main(String[] args) {
    String[] friends1 = {"muzi", "ryan", "frodo", "neo"};
    String[] gifts1 = {"muzi frodo", "muzi frodo", "ryan muzi", "ryan muzi", "ryan muzi", "frodo muzi", "frodo ryan", "neo muzi"};
    int solution1 = solution(friends1, gifts1);
    System.out.println(solution1);
    System.out.println(solution1 == 2);

    String[] friends2 = {"joy", "brad", "alessandro", "conan", "david"};
    String[] gifts2 = {"alessandro brad", "alessandro joy", "alessandro conan", "david alessandro", "alessandro david"};
    int solution2 = solution(friends2, gifts2);
    System.out.println(solution2);
    System.out.println(solution2 == 4);

    String[] friends3 = {"a", "b", "c"};
    String[] gifts3 = {"a b", "b a", "c a", "a c", "a c", "c a"};
    int solution3 = solution(friends3, gifts3);
    System.out.println(solution3);
    System.out.println(solution3 == 0);
  }
}
