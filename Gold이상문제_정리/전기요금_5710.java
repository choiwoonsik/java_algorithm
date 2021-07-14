package Gold이상문제_정리;

import java.io.*;
import java.util.*;

/*
1100 300
35515 27615
1100 800
100000000 0
4979977 4979968
0 0
 */
public class 전기요금_5710 {
	static int[] cost = {2, 3, 5, 7};
	static int[] wattHour = {100, 9900, 990000, 1000000};
	static int totalPrice, diffCost;
	static StringBuilder answer = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] AB;
		while (true)
		{
			AB = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			if (AB[0] == 0 && AB[1] == 0) {
				System.out.print(answer);
				return;
			}

			totalPrice = AB[0];
			diffCost = AB[1];

			int totalWatt = moneyToWatt(totalPrice, 0, 0);

			binarySearch(totalWatt);
		}
	}

	private static void binarySearch(int totalWatt) {
		int l = 0;
		int r = totalWatt;
		int mid;

		while (l <= r) {
			mid = (l + r) / 2;

			int myMoney = wattToMoney(mid, 0, 0);
			int yourMoney = wattToMoney(totalWatt - mid, 0, 0);

			if (yourMoney - myMoney < diffCost)
				r = mid - 1;
			else if (yourMoney - myMoney > diffCost)
				l = mid + 1;
			else {
				answer.append(myMoney).append("\n");
				break;
			}
		}
	}

	private static int wattToMoney(int watt, int stage, int money) {
		int tmp = watt;
		tmp -= wattHour[stage];
		if (tmp <= 0 || stage == 3)
			return money + watt * cost[stage];
		else return wattToMoney(watt - wattHour[stage], stage + 1, money + wattHour[stage] * cost[stage]);
	}

	private static int moneyToWatt(int totalPrice, int stage, int watt) {
		int tmp = totalPrice;
		tmp -= cost[stage] * wattHour[stage];
		if (tmp <= 0 || stage == 3)
			return watt + totalPrice / cost[stage];
		else return moneyToWatt(tmp, stage + 1, watt + wattHour[stage]);
	}
}
