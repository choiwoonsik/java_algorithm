package 이분탐색;

import java.util.*;

public class 순위검색_카카오2021 {
	public static void main(String[] args) {
		Solution sol = new Solution();
		int[] solution = sol.solution(new String[]{"java backend junior pizza 150", "python frontend senior chicken 210", "python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80", "python backend senior chicken 50"}
				, new String[]{
						"java and backend and junior and pizza 100",
						"python and frontend and senior and chicken 200",
						"cpp and - and senior and pizza 250",
						"- and backend and senior and - 150",
						"- and - and - and chicken 100",
						"- and - and - and - 150",
						"- and - and senior and chicken 100"
		});

		int[] answer = new int[]{1, 1, 1, 1, 2, 4, 2};
		for (int i = 0; i < answer.length; i++) {
			System.out.println(answer[i] == solution[i]);
			System.out.println(solution[i]);
		}
	}

	private static class Solution {
		static HashSet<String> keySet = new HashSet<>();
		static HashMap<String, List<Integer>> conditionPointsMap = new HashMap<>();
		static int[] answer;

		public int[] solution(String[] info, String[] query) {

			answer = new int[query.length];
			makeQuerySet(info);
			countMatchingQuery(query);

			return answer;
		}

		private void countMatchingQuery(String[] query) {

			int idx = 0;

			for (String q : query) {
				StringBuilder queryKey = new StringBuilder();
				String[] q_str = q.split(" and ");

				String[] foodPoint = q_str[3].split(" ");
				queryKey.append(q_str[0]).append(q_str[1]).append(q_str[2]).append(foodPoint[0]);

				int maxPoint = Integer.parseInt(foodPoint[1]);

				int count = binarySearch(queryKey.toString(), maxPoint);
				answer[idx++] = count;
			}
		}

		private int binarySearch(String key, int maxPoint) {
			if (!conditionPointsMap.containsKey(key)) {
				return 0;
			}

			List<Integer> allPoints = conditionPointsMap.get(key);
			int left = 0;
			int right = allPoints.size();

			while (left < right) {
				int mid = (left + right) / 2;

				if (allPoints.get(mid) >= maxPoint) {
					right = mid;
				} else {
					left = mid + 1;
				}
			}

			return allPoints.size() - right;
		}

		private void makeQuerySet(String[] info) {
			for (String info_str : info) {
				combination(0, new String[4], new boolean[4], info_str.split(" "));
			}

			for (String key : keySet) {
				Collections.sort(conditionPointsMap.get(key));
			}
		}

		private void combination(int depth, String[] select, boolean[] visited, String[] infos) {
			if (depth == 4) {
				StringBuilder comb = new StringBuilder();

				for (String cond : select) {
					comb.append(cond);
				}

				String condition = comb.toString();
				if (conditionPointsMap.containsKey(condition)) {
					conditionPointsMap.get(condition).add(Integer.parseInt(infos[4]));
				} else {
					List<Integer> list = new ArrayList<>();
					list.add(Integer.parseInt(infos[4]));
					conditionPointsMap.put(condition, list);
					keySet.add(condition);
				}

				return;
			}

			for (int cur = depth; cur < 4; cur++) {
				if (!visited[cur]) {
					visited[cur] = true;
					select[depth] = infos[depth];
					combination(depth + 1, select, visited, infos);
					select[depth] = "-";
					combination(depth + 1, select, visited, infos);
					visited[cur] = false;
				}
			}
		}
	}
}
