package Gold이상문제_정리.코딩테스트;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class coding_test_3 {
	static int[] money_calc;
	static ArrayList<pair>[] adj;
	static Map<String, Integer> name_id_map = new HashMap<>();
	static Map<String, String> child_parent_map = new HashMap<>();

	public static void main(String[] args) {
		String[] e = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
		String[] r = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};
		String[] s = {"young", "john", "tod", "emily", "mary"};
		int[] a = {12, 4, 2, 5, 10};
		solution(e, r, s, a);
	}

	public static int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
		int[] answer = {};
		money_calc = new int[enroll.length+1];

		// map
		for (int i = 0; i < enroll.length; i++)
			name_id_map.put(enroll[i], i+1);

		// init tree
		adj = new ArrayList[enroll.length+1];
		for (int i = 0; i < enroll.length + 1; i++) {
			adj[i] = new ArrayList<>();
		}

		// make tree
		for (int i = 1; i <= enroll.length; i++) {
			// for root
			if (referral[i - 1].equals("-")) {
				adj[0].add(new pair(i, enroll[i - 1]));
				child_parent_map.put(enroll[i - 1], "center");
			} else { // other
				int refer_id = name_id_map.get(referral[i - 1]);
				adj[refer_id].add(new pair(i, enroll[i - 1]));
				child_parent_map.put(enroll[i - 1], referral[i - 1]);
			}
		}
		// calc_each_person
		for (int i = 0; i < seller.length; i++) {
			String s = seller[i];
			int earn_money = amount[i] * 100;
			calc_seller(s, earn_money);
		}

		answer = money_calc.clone();
		Arrays.stream(answer).forEach(s -> System.out.print(s+" "));

		return answer;
	}

	private static void calc_seller(String seller, int earn_money) {
		String my_name = seller;
		int money = earn_money;
		while (true)
		{
			// my turn
			int my_id = name_id_map.get(my_name);
			int parent_money = money / 10;
			if (parent_money < 1) {
				money_calc[my_id] += money;
				break;
			} else {
				money -= parent_money;
				money_calc[my_id] += money;
			}
			// parent turn
			String parent = child_parent_map.get(my_name);
			my_name = parent;
			money = parent_money;
			if (parent.equals("center")) break;
		}
	}

	private static class pair
	{
		int id;
		String name;

		public pair(int id, String name) {
			this.id = id;
			this.name = name;
		}
	}
}
