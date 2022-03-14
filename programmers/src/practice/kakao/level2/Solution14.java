/*
[1차] 캐시
https://programmers.co.kr/learn/courses/30/lessons/17680
 */

package practice.kakao.level2;

import java.util.LinkedList;
import java.util.Queue;

public class Solution14 {

    public static int solution(int cacheSize, String[] cities) {
        int answer = 0;

        if(cacheSize == 0) return cities.length * 5;

        Queue<String> cache = new LinkedList<>();
        for (String city : cities) {
            city = city.toLowerCase();

            if (cache.contains(city)) {
                cache.remove(city);
                answer += 1;
            } else {
                if (cacheSize == cache.size()) {
                    cache.poll();
                }
                answer += 5;
            }
            cache.add(city);
        }

        return answer;
    }

    public static void main(String[] args) {
        int cacheSize1 = 3;
        String[] cities1 = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
        System.out.println(solution(cacheSize1, cities1));

        int cacheSize2 = 3;
        String[] cities2 = {"Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"};
        System.out.println(solution(cacheSize2, cities2));

        int cacheSize3 = 2;
        String[] cities3 = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"};
        System.out.println(solution(cacheSize3, cities3));

        int cacheSize4 = 5;
        String[] cities4 = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"};
        System.out.println(solution(cacheSize4, cities4));

        int cacheSize5 = 2;
        String[] cities5 = {"Jeju", "Pangyo", "NewYork", "newyork"};
        System.out.println(solution(cacheSize5, cities5));

        int cacheSize6 = 0;
        String[] cities6 = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
        System.out.println(solution(cacheSize6, cities6));
    }

}
