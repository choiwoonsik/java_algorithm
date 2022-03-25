package 트리DP;

import java.util.*;
import java.io.*;

/*
5 3
-1 1 2 3 4
2 2
3 4
5 6
 */
public class 회사문화1_14267 {
	static int N;
	static int K;
	static int[] dp;
	static int[] praise;
	static boolean[] visited;
	static ArrayList<Integer>[] adj;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		dp = new int[N + 1];
		praise = new int[N + 1];
		adj = new ArrayList[N + 1];
		for (int i = 0; i < N + 1; i++) {
			adj[i] = new ArrayList<>();
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			int boss = Integer.parseInt(st.nextToken());
			if (boss != -1) {
				adj[boss].add(i);
			}
		}

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());

			int employeeNum = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			praise[employeeNum] += w;
		}

		visited = new boolean[N + 1];
		for (int i = 2; i < N + 1; i++) {
			if (!visited[i]) {
				dfs(i);
			}
		}

		StringBuilder answer = new StringBuilder();
		for (int i = 1; i < N + 1; i++) {
			answer.append(dp[i]).append(" ");
		}

		System.out.println(answer);
	}

	private static void dfs(int employeeNum) {
		dp[employeeNum] += praise[employeeNum];
		visited[employeeNum] = true;

		for (Integer child : adj[employeeNum]) {
			if (visited[child]) continue;
			dp[child] += dp[employeeNum];
			dfs(child);
		}
	}
}
