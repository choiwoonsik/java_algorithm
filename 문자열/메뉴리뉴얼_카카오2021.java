package 문자열;

import java.util.*;

//"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"
public class 메뉴리뉴얼_카카오2021 {
	public static void main(String[] args) {
		Sol sol = new Sol();
		String[] solution = sol.solution(new String[]{"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"}, new int[]{2, 3, 4});
		String[] answer = new String[]{"AC", "ACDE", "BCFG", "CDE"};
		for (int i = 0; i < solution.length; i++) {
			if (!answer[i].equals(solution[i])) {
				System.out.println("FALSE");
				break;
			}
		}
	}

	private static class Sol {
		static ArrayList<Character>[] orderMenuList;
		static HashMap<String, Integer> menuCountMap = new HashMap<>();
		static HashMap<Integer, Integer> menuLenMaxCountMap = new HashMap<>();
		static PriorityQueue<String> totals = new PriorityQueue<>();

		public String[] solution(String[] orders, int[] course) {

			orderMenuList = new ArrayList[orders.length];

			for (int i = 0; i < orders.length; i++) {
				orderMenuList[i] = new ArrayList<>();
				for (int j = 0; j < orders[i].length(); j++) {
					orderMenuList[i].add(orders[i].charAt(j));
				}
				Collections.sort(orderMenuList[i]);
			}

			for (ArrayList<Character> menu : orderMenuList) {
				for (int courseLen : course) {
					if (menu.size() >= courseLen) {
						boolean[] visited = new boolean[menu.size()];
						Arrays.fill(visited, false);
						combination(0, 0, courseLen, menu, visited);
					}
				}
			}

			for (int courseLen : course) {
				if (!menuLenMaxCountMap.containsKey(courseLen)) continue;

				int maxC = menuLenMaxCountMap.get(courseLen);
				if (maxC < 2) continue;

				for (String menu : menuCountMap.keySet()) {
					if (menu.length() == courseLen && maxC == menuCountMap.get(menu)) {
						totals.add(menu);
					}
				}
			}

			String[] answer = new String[totals.size()];
			int idx = 0;
			while (!totals.isEmpty()) {
				answer[idx] = totals.poll();
				idx++;
			}

			return answer;
		}

		private void combination(int depth, int select, int max, ArrayList<Character> menu, boolean[] visited) {
			if (select == max) {

				StringBuilder courseMenu = new StringBuilder();
				for (int i = 0; i < menu.size(); i++) {
					if (visited[i]) {
						courseMenu.append(menu.get(i));
					}
				}

				String courseName = courseMenu.toString();
				if (menuCountMap.containsKey(courseName)) {
					int thisCount = menuCountMap.get(courseName) + 1;
					menuCountMap.put(courseName, thisCount);

					if (!menuLenMaxCountMap.containsKey(max)) {
						menuLenMaxCountMap.put(max, thisCount);
					} else if (thisCount > menuLenMaxCountMap.get(max)) {
						menuLenMaxCountMap.put(max, thisCount);
					}
				} else {
					menuCountMap.put(courseName, 1);
				}

				return;
			}

			for (int cur = depth; cur < menu.size(); cur++) {
				if (!visited[cur]) {
					visited[cur] = true;
					combination(cur + 1, select + 1,  max, menu, visited);
					visited[cur] = false;
				}
			}
		}
	}
}
