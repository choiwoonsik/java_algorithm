package 트리DP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
6
-10 5 20 15 -5 10
1 1 2 2 1
 */
public class 신년파티_1623 {
	static int N;
	static int[][] dp;
	static int YES = 0;
	static int NO = 1;
	static ArrayList<Integer> adj[];
	static ArrayList<Integer> trace;
	static boolean[] check;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		dp = new int[2][N + 1];
		adj = new ArrayList[N + 1];
		check = new boolean[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < N + 1; i++) {
			adj[i] = new ArrayList<>();
			dp[YES][i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 2; i < N + 1; i++) {
			int boss = Integer.parseInt(st.nextToken());
			adj[boss].add(i);
		}

		StringBuilder answer = new StringBuilder();
		trace = new ArrayList<>();

		dfs(1);
		answer.append(dp[YES][1]).append(" ").append(dp[NO][1]).append("\n");

		traceDfs(1, YES);
		trace.sort(null);
		for (Integer t : trace) {
			answer.append(t).append(" ");
		}
		answer.append("-1").append("\n");

		trace.clear();
		traceDfs(1, NO);
		trace.sort(null);
		for (Integer t : trace) {
			answer.append(t).append(" ");
		}
		answer.append("-1");

		System.out.println(answer);
	}

	private static void dfs(int root) {
		for (Integer child : adj[root]) {
			dfs(child);
			dp[YES][root] += dp[NO][child];
			check[root] = true;

			if (dp[YES][child] >= dp[NO][child]) {
				check[child] = true;
				dp[NO][root] += dp[YES][child];
			} else {
				check[child] = false;
				dp[NO][root] += dp[NO][child];
			}
		}
	}

	private static void traceDfs(int root, int isCome) {
		if (isCome == YES) {
			trace.add(root);
		}

		for (Integer child : adj[root]) {
			if (isCome == YES) {
				traceDfs(child, NO);
			} else {
				if (check[child]) traceDfs(child, YES);
				else traceDfs(child, NO);
			}
		}
	}
}
