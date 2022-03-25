package kakaobank_2022_03_19;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Solution01 {

    private static final int finalDay = 365;

    // 오래된 Date와 Calendar 클래스에는 생각보다 많은 문제점이 있다.
    // 자바 버전업이 되면서 Date 클래스의 많은 메서드가 Deprecate 되었다.
    // 가장 혼동스러운건 월 표기를 위한 상수 값을 확인하면 1월이 0 이어서 월 값에 1을 빼야한다.
    // 이 때문에 가독성이 나빠진다.
    // 그리고 Calendar 혹은 Date 클래스는 불변 객체가 아니어서
    // 생성된 객체가 다른 코드에서도 공유되기 때문에 변경될 수 있다.
    // JDK 1.8 버전에서 LocalDateTime 과 타임존 개념까지 포함할 수 있는 ZonedDateTime 이 추가되었고
    // LocalDate 혹은 LocalDateTime 으로 이전보다 안전하고 편하게 날짜를 계산할 수 있게 되었다.
    private static final LocalDate lastDate = LocalDate.of(2022, 12, 31);


    // 결제 정보 클래스
    private static class TransInfo {
        LocalDate date;
        int interestRate, amount;

        public TransInfo(LocalDate date, int interestRate, int amount) {
            this.date = date;
            this.interestRate = interestRate;
            this.amount = amount;
        }
    }

    // 날짜를 GregorianCalendar로 바꿔주는 메서드
    private static LocalDate convertToLocalDate(String dateStr) {
        String[] dateSplit = dateStr.split("/");
        return LocalDate.of(2022, Integer.parseInt(dateSplit[0]), Integer.parseInt(dateSplit[1]));
    }

    // 이자 구하는 메서드
    private static int getWithdrawalAmount(LocalDate sDate, LocalDate eDate, int calAmount, int lastInterestRate) {
        long diffDays = ChronoUnit.DAYS.between(sDate, eDate); // ChronoUnit 으로 두 날짜 사이의 간격을 구할 수 있다.
        return (int) ((calAmount * lastInterestRate) * diffDays / finalDay / 100);
    }


    public static int solution(String[] ledgers) {
        int answer = 0;

        // 가장 나중에 들어온 입금 내역부터 처리하기 때문에 후입선출인 스택 사용
        Stack<TransInfo> s = new Stack<>();
        for (String ledger : ledgers) {
            StringTokenizer st = new StringTokenizer(ledger);
            // 변수 정리
            LocalDate date = convertToLocalDate(st.nextToken());
            int interestRate = Integer.parseInt(st.nextToken());
            int amount = Integer.parseInt(st.nextToken());

            if (amount > 0) { // 입금이면 stack에 추가
                TransInfo transInfo = new TransInfo(date, interestRate, amount);
                s.add(transInfo);
            } else { // 출금이면 이자 계산 시작
                while (amount < 0) { // 모두 출금될 때까지 반복 
                    TransInfo lastTrans = s.pop();
                    LocalDate lastDate = lastTrans.date;
                    int lastInterestRate = lastTrans.interestRate;
                    int lastAmount = lastTrans.amount;

                    int calAmount = 0;
                    if (lastAmount + amount <= 0) { // 아직 출금할 내역이 남아 있으므로 
                        calAmount = lastAmount;
                    } else { // 모두 출금 되었으므로 남은 금액 다시 stack에 넣기
                        calAmount = Math.abs(amount);
                        s.add(new TransInfo(lastDate, lastInterestRate, lastAmount + amount));
                    }

                    answer += getWithdrawalAmount(lastDate, date, calAmount, lastInterestRate); // 출금 이자 계산

                    amount += calAmount;
                }
            }
        }

        while(!s.isEmpty()) { // stack 에 남은 내역들 마지막(12/31)날로 이자 계산 
            TransInfo trans = s.pop();
            answer += getWithdrawalAmount(trans.date, lastDate, trans.amount, trans.interestRate);
        }

        return answer;
    }

    public static void main(String[] args) {
        String[] ledgers1 = {"01/01 4 50000","01/11 6 3555","02/01 0 -23555","02/25 5 5000","03/25 0 -15000","06/09 8 43951","12/30 9 99999"};
        System.out.println(solution(ledgers1) == 2983);

        String[] ledgers2 = {"04/01 1 40000","05/01 5 20000","08/31 4 10000","11/11 0 -45000"};
        System.out.println(solution(ledgers2) == 888);
    }

}
