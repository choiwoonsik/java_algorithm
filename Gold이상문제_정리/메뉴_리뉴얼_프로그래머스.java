package Gold이상문제_정리;

import java.lang.reflect.Array;
import java.util.*;

public class 메뉴_리뉴얼_프로그래머스 {
	public static void main(String[] args) {
//		Solution.solution(new String[]{"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"}, new int[]{2, 3, 4});
		Solution.solution(new String[]{"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"}, new int[]{2, 3, 5});
//		Solution.solution(new String[]{"XYZ", "XWY", "WXA"}, new int[]{2, 3, 4});
	}
}

class Solution {
	static HashMap<String, Integer> hashMap = new HashMap<>();
	static HashMap<Integer, Integer> len_count_map = new HashMap<>();
	public static String[] solution(String[] orders, int[] course) {

		for (String order : orders)
		{
			ArrayList<Character> menu = new ArrayList<>();
			for (int i = 0; i < order.length(); i++)
				menu.add(order.charAt(i));

			Collections.sort(menu);

			for (int len : course) {
				boolean[] selected = new boolean[menu.size()];
				Arrays.fill(selected, false);
				combination(menu, 0, 0, len, selected);
			}
		}

		List<String> list = new ArrayList<>();
		for (int len : course)
		{
			int max_count;
			if (len_count_map.containsKey(len)) {
				max_count = len_count_map.get(len);

				hashMap.forEach((s1, s2) -> {
					if (s1.length() == len && s2 == max_count)
						list.add(s1);
				});
			}
		}
		Collections.sort(list);

		String[] answer = new String[list.size()];
		for (int i = 0; i < list.size(); i++)
			answer[i] = list.get(i);

		return answer;
	}

	private static void combination(ArrayList<Character> menu, int start, int select, int max, boolean[] selected) {
		if (select == max)
		{
			// n개 고름
			StringBuilder menu_comb = new StringBuilder();
			for (int i = 0; i < menu.size(); i++) {
				if (selected[i]) {
					menu_comb.append(menu.get(i));
				}
			}
			String menu_name = menu_comb.toString();
			if (hashMap.containsKey(menu_name)) {
				int count = hashMap.get(menu_name) + 1;
				hashMap.put(menu_name, count);

				if (len_count_map.containsKey(max)) {
					if (len_count_map.get(max) < count)
						len_count_map.put(max, count);
				} else len_count_map.put(max, count);
			} else {
				hashMap.put(menu_name, 1);
			}
			return;
		}

		for (int s = start; s < menu.size(); s++) {
			if (!selected[s])
			{
				selected[s] = true;
				combination(menu, s + 1, select + 1, max, selected);
				selected[s] = false;
			}
		}
	}
}
