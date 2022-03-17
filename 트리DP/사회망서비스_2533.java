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
경우의 수 : 루트가 얼리어답터 vs 루트가 얼리어답터 아님
부모가 얼리어답터? 자기는 아니여도됨
부모가 얼리어답터가 아니다? 자기가 얼리어답터
 */
public class 사회망서비스_2533 {
	static int N;
	static int[] dp;
	static boolean[] isEarly;
	static boolean[] visit;
	static ArrayList<Integer>[] adj;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		adj = new ArrayList[N + 1];
		dp = new int[N + 1];
		isEarly = new boolean[N + 1];
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

		dfsNotEarly(1);
		System.out.println(dp[1]);
		for (int i = 1; i <= N; i++) {
			System.out.println(i + " : " + isEarly[i]);
		}
		Arrays.fill(dp, 0);
		Arrays.fill(isEarly, false);
		Arrays.fill(visit, false);

		isEarly[1] = true;
		dfsEarly(1);
		System.out.println(dp[1]);
		for (int i = 1; i <= N; i++) {
			System.out.println(i + " : " + isEarly[i]);
		}
	}

	private static void dfsEarly(int root) {
		visit[root] = true;
		if (isEarly[root]) dp[root] += 1;

		for (Integer child : adj[root]) {
			if (visit[child]) continue;
			visit[child] = true;
			if (!isEarly[root]) {
				isEarly[child] = true;
			}
			dfsNotEarly(child);
			dp[root] += dp[child];
		}
	}

	private static void dfsNotEarly(int root) {
		visit[root] = true;
		if (isEarly[root]) dp[root] += 1;

		for (Integer child : adj[root]) {
			if (visit[child]) continue;
			visit[child] = true;
			if (!isEarly[root]) {
				isEarly[child] = true;
			}
			dfsNotEarly(child);
			dp[root] += dp[child];
		}
	}
}
