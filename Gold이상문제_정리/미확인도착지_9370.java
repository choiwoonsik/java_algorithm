package Gold이상문제_정리;

import java.util.*;
import java.io.*;

public class 미확인도착지_9370 {
	static int T, N, E, candidate_cnt;
	static int start, g, h, gh_cost;
	static int[] dp;
	static int[] candidate;
	static StringBuilder str = new StringBuilder();
	static ArrayList<Integer> real_candidate = new ArrayList<>();
	static ArrayList<Dot>[] adj;
	static PriorityQueue<Dot> pq = new PriorityQueue<>(Comparator.comparingInt(s -> s.cost));
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		T = Integer.parseInt(st.nextToken());
		while (T-- > 0)
		{
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			candidate_cnt = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			start = Integer.parseInt(st.nextToken());
			g = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());

			real_candidate.clear();
			candidate = new int[candidate_cnt];
			adj = new ArrayList[N+1];
			for (int n = 0; n < N+1; n++)
				adj[n] = new ArrayList<>();

			for (int e = 0; e < E; e++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				adj[a].add(new Dot(b, cost));
				adj[b].add(new Dot(a, cost));
				if (a == g && b == h || a == h && b == g)
					gh_cost = cost;
			}

			for (int c = 0; c < candidate_cnt; c++) {
				st = new StringTokenizer(br.readLine());
				candidate[c] = Integer.parseInt(st.nextToken());
			}

			boolean cost_s_g_h_can = true;
			boolean cost_s_h_g_can = true;

			int[] dp_g = new int[N+1];
			int[] dp_h = new int[N+1];
			Dijkstra(g);
			System.arraycopy(dp, 0, dp_g, 0, N+1);
			Dijkstra(h);
			System.arraycopy(dp, 0, dp_h, 0, N+1);
			Dijkstra(start);
			for (int can = 0; can < candidate_cnt; can++) {
				int cost_s_to_c = dp[candidate[can]];
				int cost_s_to_g = dp[g];
				int cost_s_to_h = dp[h];
				int cost_g_to_c = dp_g[candidate[can]];
				int cost_h_to_c = dp_h[candidate[can]];

				if (cost_s_to_g == Integer.MAX_VALUE || cost_h_to_c == Integer.MAX_VALUE)
					cost_s_g_h_can = false;
				if (cost_s_to_h == Integer.MAX_VALUE || cost_g_to_c == Integer.MAX_VALUE)
					cost_s_h_g_can = false;

				if (!cost_s_g_h_can && !cost_s_h_g_can)
					continue;
				int min_cost_to_can = Math.min((cost_s_to_g + gh_cost + cost_h_to_c), (cost_s_to_h + gh_cost + cost_g_to_c));
				if (min_cost_to_can > cost_s_to_c)
					continue;
				real_candidate.add(candidate[can]);
			}

			Collections.sort(real_candidate);
			for (int c : real_candidate)
				str.append(c).append(" ");
			str.append("\n");
		}
		System.out.print(str);
	}

	private static void Dijkstra(int start)
	{
		pq.clear();
		pq.add(new Dot(start, 0));
		dp = new int[N+1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[start] = 0;

		while (!pq.isEmpty())
		{
			Dot now = pq.poll();

			for (Dot next : adj[now.dest]) {
				if (dp[next.dest] > dp[now.dest] + next.cost) {
					dp[next.dest] = dp[now.dest] + next.cost;
					pq.add(next);
				}
			}
		}
	}

	private static class Dot {
		int dest, cost;

		public Dot(int dest, int cost) {
			this.dest = dest;
			this.cost = cost;
		}
	}
}
