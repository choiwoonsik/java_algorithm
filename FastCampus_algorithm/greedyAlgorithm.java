package FastCampus_algorithm;

import javax.swing.text.html.HTMLDocument;
import java.util.*;

public class greedyAlgorithm {
	public static void main(String[] args) {
		List<Integer> coins = new ArrayList<>();
		coins.add(1);
		coins.add(50);
		coins.add(100);
		coins.add(500);
		Collections.reverse(coins);
		System.out.println(coin_Count(coins, 4720));

		System.out.println("knapsack problem");

		WVclass wv1 = new WVclass(20, 10);
		WVclass wv2 = new WVclass(10, 10);
		WVclass wv3 = new WVclass(25, 8);
		WVclass wv4 = new WVclass(30, 5);
		WVclass wv5 = new WVclass(15, 12);
		WVclass[] wVarr = {wv1, wv2, wv3, wv4, wv5};
		System.out.println(knapsack(wVarr, 30));
	}
	private static double knapsack(WVclass[] arr, int capacity)
	{
		Arrays.sort(arr, (o1, o2) -> {
			int first = o1.V * 100 / o1.W;
			int second = o2.V * 100 / o2.W;
			return second - first;
		});

		double total_value = 0;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arr.length; i++){
			if (capacity - arr[i].W >= 0) {
				capacity -= arr[i].W;
				total_value += arr[i].V;
				sb.append(arr[i].W +" "+arr[i].V+" .. ");
			}
			else
			{
				double fraction = ((double)capacity / (double)arr[i].W);
				capacity -= (double)arr[i].W * fraction;
				total_value += (double)arr[i].V * fraction;
				sb.append(arr[i].W +" "+arr[i].V+" .. ");
			}
		}
		System.out.println(sb);
		return total_value;
	}

	private static int coin_Count(List<Integer> coins, int money) {
		int total_coin = 0;
		Dictionary<Integer, Integer> each_coin_count = new Hashtable<>();

		while (coins.size() > 0) {
			int count = money / coins.get(0);
			each_coin_count.put(coins.get(0), count);
			money -= count * coins.get(0);
			total_coin += count;
			coins.remove(0);
		}
		Iterator<Integer> iter = each_coin_count.keys().asIterator();
		while (iter.hasNext()) {
			int coin = iter.next();
			System.out.println(coin + " :" + each_coin_count.get(coin));
		}
		return total_coin;
	}
}
class WVclass
{
	int W;
	int V;
	WVclass (int W, int V) {
		this.W = W;
		this.V = V;
	}
}
