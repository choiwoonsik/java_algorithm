package 힣_코테.여기어때_2024_04;

public class test1 {
    public static void main(String[] args) {
        Solution_test1 solution = new Solution_test1();
        int[] income = {10, 20, 0, 30, 70, 10};
        int[] outlay = {20, 25, 15, 5, 15, 10};
        int cash = 100;
        System.out.println(solution.solution(income, outlay, cash));
    }
}

/**
 * 1. 0원 이하로 잔고가 내려가면 대출이 불가능하다.
 */
class Solution_test1 {
    public int solution(int[] income, int[] outlay, int cash) {
        Bank bank = new Bank(cash);

        for (int day = 0; day < income.length; day++) {
            bank.incomeMoney(income[day]);
            bank.outlayMoney(day + 1, outlay[day]);
        }

        MinBalanceInfo minBalanceInfo = bank.minBalanceInfo;

        if (minBalanceInfo.balance <= 0) return 0;
        else return minBalanceInfo.balance - 1 / minBalanceInfo.day;
    }

    private static class Bank {
        private int currentCash;
        MinBalanceInfo minBalanceInfo;

        public Bank(int currentCash) {
            this.currentCash = currentCash;
            this.minBalanceInfo = new MinBalanceInfo(1, Integer.MAX_VALUE);
        }

        public void incomeMoney(int money) {
            this.currentCash += money;
        }

        public void outlayMoney(int day, int money) {
            this.currentCash -= money;
            if (this.currentCash < 0)
                minBalanceInfo = new MinBalanceInfo(day, this.currentCash);
            else if (this.currentCash < this.minBalanceInfo.balance)
                minBalanceInfo = new MinBalanceInfo(day, this.currentCash);
        }
    }

    private static class MinBalanceInfo {
        int day;
        int balance;

        public MinBalanceInfo(int day, int balance) {
            this.day = day;
            this.balance = balance;
        }
    }
}

