package 코테.카카오_2022_05;

import java.util.*;

public class sol4 {
	public static void main(String[] args) {
		Solution sol = new Solution();
		int[] solution = sol.solution(6, new int[][]
				{
						{1, 2, 3},
						{2, 3, 5},
						{2, 4, 2},
						{2, 5, 4},
						{3, 4, 4},
						{4, 5, 3},
						{4, 6, 1},
						{5, 6, 1},
				}, new int[]{1, 3}, new int[]{5});

		for (int i : solution) {
			System.out.println(i);
		}

//		Solution sol = new Solution();
//		int[] solution1 = sol.solution(7,
//				new int[][]
//						{
//								{1, 4, 4},
//								{1, 6, 1},
//								{1, 7, 3},
//								{2, 4, 2},
//								{3, 7, 4},
//								{5, 6, 6},
//						}, new int[]{1}, new int[]{2, 3, 5});
//		for (int i : solution1) {
//			System.out.println(i);
//		}
	}

	private static class Solution {
		static ArrayList<Info>[] adj;
		static int N;
		static int[] dp;
		static int Min = Integer.MAX_VALUE;
		static int INF = Integer.MAX_VALUE;
		static HashSet<Integer> gateSet = new HashSet<>();
		static HashSet<Integer> summitSet = new HashSet<>();
		static PriorityQueue<int[]> answerQ = new PriorityQueue<>(Comparator.comparingInt(i -> i[0]));

		public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
			N = n;
			adj = new ArrayList[N + 1];
			dp = new int[N + 1];
			for (int gate : gates) {
				gateSet.add(gate);
			}
			for (int summit : summits) {
				summitSet.add(summit);
			}

			for (int i = 0; i < N + 1; i++) {
				adj[i] = new ArrayList<>();
			}

			Arrays.fill(dp, INF);

			for (int[] path : paths) {
				int from = path[0];
				int to = path[1];
				int cost = path[2];
				adj[from].add(new Info(to, cost, cost));
				adj[to].add(new Info(from, cost, cost));
			}

			for (int gate : gates) {
				for (int summit : summits) {
//					System.out.println("\nSTART GATE : " + gate);
					bfs(gate, summit);
					System.out.println(gate + " - " + summit + " = " + dp[summit]);
					answerQ.add(new int[]{summit, dp[summit]});
				}
			}

			return answerQ.poll();
		}

		private void bfs(int gate, int summit) {
			PriorityQueue<Info> pq = new PriorityQueue<>(Comparator.comparingInt(i -> i.beforeMax));
			pq.add(new Info(gate, 0, 0));

			while (!pq.isEmpty()) {
				Info cur = pq.poll();
//				System.out.println(cur.to + " : " + cur.beforeMax);

				if (cur.to == summit) {
//					System.out.println("봉우리");
					if (dp[summit] > cur.beforeMax) {
						dp[cur.to] = cur.beforeMax;
						Min = summit;
					}
					continue;
				}

				if (summitSet.contains(cur.to)) {
					continue;
				}

				if (cur.to != gate && gateSet.contains(cur.to)) {
					continue;
				}

				for (Info next : adj[cur.to]) {
					next.beforeMax = Math.max(next.intensity, cur.beforeMax);
					if (dp[next.to] > next.beforeMax) {
//						System.out.println("next : " + next.to);
						dp[next.to] = next.beforeMax;
						pq.add(new Info(next.to, next.intensity, next.beforeMax));
					}
				}
			}
		}

		private static class Info {
			int to;
			int intensity;
			int beforeMax;

			public Info(int to, int intensity, int beforeMax) {
				this.to = to;
				this.intensity = intensity;
				this.beforeMax = beforeMax;
			}
		}
	}
}
