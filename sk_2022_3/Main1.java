package sk_2022_3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Main1 {
	public static void main(String[] args) {

		Solution sol = new Solution();
		int solution = sol.solution(4578, new int[]{1, 4, 99, 35, 50, 1000});
		System.out.println(solution);
	}

	private static class Solution {

		static int totalCost;

		public int solution(int money, int[] costs) {

			PriorityQueue<Money> moneyPq = new PriorityQueue<>(Comparator.comparingDouble(M -> -M.rate));

			moneyPq.add(new Money(1, costs[0], (double) 1 / costs[0]));
			moneyPq.add(new Money(5, costs[1], (double) 5 / costs[1]));
			moneyPq.add(new Money(10, costs[2], (double) 10 / costs[2]));
			moneyPq.add(new Money(50, costs[3], (double) 50 / costs[3]));
			moneyPq.add(new Money(100, costs[4], (double) 100 / costs[4]));
			moneyPq.add(new Money(500, costs[5], (double) 500 / costs[5]));

			while (!moneyPq.isEmpty()) {
				Money curMoney = moneyPq.poll();
				int count = money / curMoney.realPrice;
				totalCost += count * curMoney.makePrice;
				money -= count * curMoney.realPrice;

				if (money == 0) break;
			}

			System.out.println(totalCost);
			return totalCost;
		}
	}

	private static class Money {
		int realPrice;
		int makePrice;
		double rate;

		public Money(int realPrice, int makePrice, double rate) {
			this.realPrice = realPrice;
			this.makePrice = makePrice;
			this.rate = rate;
		}
	}
}
