package 트리DP;

import java.util.*;
import java.io.*;
/*
9
1 2
1 3
2 4
3 5
3 6
4 7
4 8
4 9

dp : 자신을 포함한 하위 얼리어답터의 수
경우의 수 : 얼리어답터 vs 얼리어답터 아님

부모가 얼리어답터 -> 자식은 얼리어답터 or 얼리어답터 아님
부모가 얼리어답터 아님 -> 자식은 얼리어답터
 */
public class 사회망서비스_2533 {
	static int N;
	static int[][] dp;
	static boolean[] visit;
	static ArrayList<Integer>[] adj;
	static int EARLY = 0;
	static int NOT_EARLY = 1;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		adj = new ArrayList[N + 1];
		dp = new int[N + 1][2];
		visit = new boolean[N + 1];

		for (int i = 0; i < N + 1; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			adj[u].add(v);
			adj[v].add(u);
		}

		dfs(1);
		System.out.println(Math.min(dp[1][NOT_EARLY], dp[1][EARLY]));
	}

	private static void dfs(int root) {
		visit[root] = true;
		dp[root][EARLY] += 1;
		for (Integer child : adj[root]) {
			if (visit[child]) continue;
			dfs(child);
			dp[root][EARLY] += Math.min(dp[child][EARLY], dp[child][NOT_EARLY]);
			dp[root][NOT_EARLY] += dp[child][EARLY];
		}
	}
}
