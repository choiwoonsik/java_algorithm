package 힣_코테.케이뱅크_2024_06;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Sol2 {
    public int[] solution(int n, int amount, int[][] planList) {

        List<MoneyPlan> moneyPlans = Arrays
                .stream(planList)
                .map(plan -> new MoneyPlan(plan[0], plan[1], plan[2]))
                .collect(Collectors.toList());

        int monthlyAddMoney = 0;
        int lastBalance;

        while (true) {
            System.out.println("\n월 추가금액 = " + monthlyAddMoney);
            MinusBank minusBank = new MinusBank(amount);
            for (int month = 0; month < n; month++) {
                int currentMonth = month;
                if (month != 0) minusBank.incomeMoney(monthlyAddMoney);
                minusBank.setMinBalance();

                moneyPlans
                        .stream()
                        .filter(moneyPlan -> moneyPlan.month == currentMonth)
                        .forEach(moneyPlan -> {
                            if (moneyPlan.money > 0) minusBank.incomeMoney(moneyPlan.money);
                            else minusBank.outlayMoney(moneyPlan.money);
                        });

                minusBank.outlayThisMonthInterestFee();
            }
            minusBank.incomeMoney(monthlyAddMoney);
            if (minusBank.getBalance() >= 0) {
                lastBalance = minusBank.getBalance();
                break;
            }
            monthlyAddMoney += 10000;
        }

        return new int[]{monthlyAddMoney, lastBalance};
    }

    public static class MinusBank {
        private int balance;
        private int minMinusBalance;


        public MinusBank(int balance) {
            this.balance = balance * -1;
            this.minMinusBalance = this.balance;
        }

        public void incomeMoney(int money) {
            this.balance += money;
            System.out.println("입금 = " + money + ", 잔고: " + balance);
        }

        public void outlayMoney(int money) {
            this.balance += money;
            minMinusBalance = Math.min(minMinusBalance, balance);
            System.out.println("출금 = " + money + ", 최저 잔고: " + minMinusBalance + ", 잔고: " + balance);
        }

        public void outlayThisMonthInterestFee() {
            if (minMinusBalance >= -99) return;

            int interestFee = minMinusBalance / 100;
            balance += interestFee;
            System.out.println("이자비용 = " + interestFee + ", 잔고: " + balance);
        }

        public void setMinBalance() {
            this.minMinusBalance = balance;
        }

        public int getBalance() {
            return balance;
        }
    }

    public static class MoneyPlan {
        int month;
        int day;
        int money;

        public MoneyPlan(int month, int day, int money) {
            this.month = month;
            this.day = day;
            this.money = money;
        }
    }
}
