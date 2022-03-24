package kakaobank_2022_03_19;

import java.util.*;

public class Solution01 {

    private static final int finalDay = 365;
    private static final Calendar lastDate = new GregorianCalendar(2022, Calendar.DECEMBER, 31); // GregorianCalendar 룰 사용하여 날짜 관리

    // 결제 정보 클래스
    private static class TransInfo {
        Calendar date;
        int interestRate, amount;

        public TransInfo(Calendar date, int interestRate, int amount) {
            this.date = date;
            this.interestRate = interestRate;
            this.amount = amount;
        }
    }

    // 날짜를 일 단위로 바꿔주는 메서드
    private static Calendar convertToDay(String dateStr) {
        String[] dateSplit = dateStr.split("/");
        return new GregorianCalendar(2022, Integer.parseInt(dateSplit[0]) - 1, Integer.parseInt(dateSplit[1]));
    }

    // 이자 구하는 메서드
    private static int getWithdrawalAmount(Calendar sDate, Calendar eDate, int calAmount, int lastInterestRate) {
        int diffDays = (int) (((eDate.getTimeInMillis() - sDate.getTimeInMillis()) / 1000) / (24*60*60)); // GregorianCalendar 를 사용하여 편하게 일 단위로 계산
        return (calAmount * lastInterestRate) * diffDays / finalDay / 100;
    }


    public static int solution(String[] ledgers) {
        int answer = 0;

        // 가장 나중에 들어온 입금 내역부터 처리하기 때문에 후입선출인 스택 사용
        Stack<TransInfo> s = new Stack<>();
        for (String ledger : ledgers) {
            StringTokenizer st = new StringTokenizer(ledger);
            // 변수 정리
            Calendar date = convertToDay(st.nextToken());
            int interestRate = Integer.parseInt(st.nextToken());
            int amount = Integer.parseInt(st.nextToken());

            if (amount > 0) { // 입금이면 stack에 추가
                
                TransInfo transInfo = new TransInfo(date, interestRate, amount);
                s.add(transInfo);
            } else { // 출금이면 이자 계산 시작
                while (amount < 0) { // 모두 출금될 때까지 반복 
                    TransInfo lastTrans = s.pop();
                    Calendar lastDate = lastTrans.date;
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
