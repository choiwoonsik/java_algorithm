package 힣_코테.케이뱅크_2024_06;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Stack;

public class Sol1 {
    static Date LAST_DATE = new Date(12, 31);

    public int solution(String[] ledgers) {
        Bank bank = new Bank();
        for (String ledger : ledgers) {
            String[] ledgerInfo = ledger.split(" ");
            String[] dateInfo = ledgerInfo[0].split("/");

            Date date = new Date(Integer.parseInt(dateInfo[0]), Integer.parseInt(dateInfo[1]));
            int interest = Integer.parseInt(ledgerInfo[1]);
            int balance = Integer.parseInt(ledgerInfo[2]);

            if (balance < 0) {
                Money outMoney = new Money(date, balance * -1, interest);
                bank.outlayMoney(outMoney);
            } else {
                Money money = new Money(date, balance, interest);
                bank.incomeMoney(money);
            }
        }

        bank.outlayMoney(null);

        return bank.getTotalInterest();
    }

    public class Bank {
        private int totalInterest = 0;
        private final Stack<Money> moneyStack = new Stack<>();

        public void incomeMoney(Money money) {
            moneyStack.push(money);
        }

        public void outlayMoney(Money money) {
            if (money == null) {
                while (!moneyStack.isEmpty()) {
                    Money currentMoney = moneyStack.pop();

                    int diffDays = currentMoney.date.calculateDiffDays(LAST_DATE);
                    totalInterest += currentMoney.balance * currentMoney.interestRate * diffDays / 100 / 365;
                }
                return;
            }

            while (!moneyStack.isEmpty() && money.balance > 0) {
                Money currentMoney = moneyStack.pop();
                if (currentMoney.balance < money.balance) {
                    int diffDays = currentMoney.date.calculateDiffDays(money.date);
                    totalInterest += currentMoney.balance * currentMoney.interestRate * diffDays / 100 / 365;

                    money.balance -= currentMoney.balance;
                } else {
                    currentMoney.balance -= money.balance;
                    int diffDays = currentMoney.date.calculateDiffDays(money.date);
                    totalInterest += money.balance * currentMoney.interestRate * diffDays / 100 / 365;
                    moneyStack.push(currentMoney);

                    money.balance = 0;
                }
            }
        }

        public int getTotalInterest() {
            return totalInterest;
        }
    }

    public class Money {
        public Date date;
        public int balance;
        public int interestRate;

        public Money(Date date, int balance, int interestRate) {
            this.date = date;
            this.balance = balance;
            this.interestRate = interestRate;
        }
    }

    public static class Date {
        public int month;
        public int day;

        public Date(int month, int day) {
            this.month = month;
            this.day = day;
        }

        public int calculateDiffDays(Date date) {
            OffsetDateTime start = OffsetDateTime.of(2024, this.month, this.day, 0, 0, 0, 0, ZoneOffset.UTC);
            OffsetDateTime end = OffsetDateTime.of(2024, date.month, date.day, 0, 0, 0, 0, ZoneOffset.UTC);

            return (int) start.until(end, ChronoUnit.DAYS);
        }
    }
}
