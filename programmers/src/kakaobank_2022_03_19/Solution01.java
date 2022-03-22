package kakaobank_2022_03_19;

import java.util.*;

public class Solution01 {

    private static final int finalDay = 365;
    private static final Calendar lastDate = new GregorianCalendar(2022, Calendar.DECEMBER, 31);

    // 거래 정보에 대한 클래스 구현
    private static class TransInfo {
        Calendar date;
        int interestRate, amount;

        public TransInfo(Calendar date, int interestRate, int amount) {
            this.date = date;
            this.interestRate = interestRate;
            this.amount = amount;
        }
    }

    // string 형태의 날짜를 Calendar 형으로 변환해주는 메서드
    private static Calendar convertToDay(String dateStr) {
        String[] dateSplit = dateStr.split("/");
        return new GregorianCalendar(2022, Integer.parseInt(dateSplit[0]) - 1, Integer.parseInt(dateSplit[1]));
    }
    
    // 이자를 구하는 메서드
    // 출금하는 날짜와 입금했던 날자와의 차이를 일 단위로 구한 뒤 이자 계산
    // 소숫점으로 인해 값이 변환되지 않도록 나머지는 나중에 계산
    private static int getWithdrawalAmount(Calendar sDate, Calendar eDate, int calAmount, int lastInterestRate) {
        int diffDays = (int) ((eDate.getTimeInMillis() - sDate.getTimeInMillis()) / 1000 / (24*60*60)); // 밀리세컨즈 이므로 1000 을 나눠 초 단위로 바꾸고 시,분,초 단위를 나눠 일 단위로 변경
        return (calAmount * lastInterestRate) * diffDays / finalDay / 100;
    }


    public static int solution(String[] ledgers) {
        int answer = 0;

        // 먼저 거래 정보에 대한 클래스를 구현하였다. (거래 날짜, 이자율, 금액), 날짜는 Calendar를 사용하였다.
        // 출금할 때 가장 최근에 입금한 건을 빼서 이자를 계산하므로 후입선출인 Stack을 이용하였다.
        Stack<TransInfo> s = new Stack<>();
        for (String ledger : ledgers) {
            StringTokenizer st = new StringTokenizer(ledger);
            // 변수 정리
            Calendar date = convertToDay(st.nextToken());
            int interestRate = Integer.parseInt(st.nextToken());
            int amount = Integer.parseInt(st.nextToken());

            if (amount > 0) { // amount 가 양수일 때는 입금이기 때문에 거래 정보를 stack 에 넣는다.
                TransInfo transInfo = new TransInfo(date, interestRate, amount);
                s.add(transInfo);
            } else { // amount 가 음수일 때는 출금이기 때문에 이자를 계산한다.
                while (amount < 0) { // 출금이 다 될때까지 반복
                    TransInfo lastTrans = s.pop();
                    Calendar lastDate = lastTrans.date;
                    int lastInterestRate = lastTrans.interestRate;
                    int lastAmount = lastTrans.amount;

                    int calAmount = 0; // 이자를 계산할 금액
                    // stack 에서 꺼낸 값이 출금하는 값보다 작으면 전부 출금
                    if (lastAmount + amount <= 0) {
                        calAmount = lastAmount;
                    } else { // 그 반대라면, 출금하고도 그 날짜에 입금했던 금액이 남음으로 남은 금액 다시 stack에 넣기
                        calAmount = Math.abs(amount);
                        s.add(new TransInfo(lastDate, lastInterestRate, lastAmount + amount));
                    }

                    answer += getWithdrawalAmount(lastDate, date, calAmount, lastInterestRate); // 이자 계산하여 더해주기

                    amount += calAmount; // 출금된 금액을 더해주기
                }
            }
        }

        // stack에 남은 건들은 모두 12월 31일로 이자를 계산하면 되므로 전부 꺼내서 계산
        while(!s.isEmpty()) {
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
