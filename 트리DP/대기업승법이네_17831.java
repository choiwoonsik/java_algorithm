package 트리DP;

import java.util.*;
import java.io.*;

/*
3
1 2
1 2 3

4
1 2 2
3 2 1 4
 */
public class 대기업승법이네_17831 {
	static int N;
	static int[][] dp;
	static int YES = 0;
	static int NO = 1;
	static int[] w;
	static ArrayList<Integer>[] adj;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		dp = new int[2][N + 1];
		adj = new ArrayList[N + 1];
		w = new int[N + 1];

		Arrays.fill(dp[YES], -1);
		Arrays.fill(dp[NO], -1);
		for (int i = 0; i < N + 1; i++) {
			adj[i] = new ArrayList<>();
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 2; i <= N; i++) {
			int boss = Integer.parseInt(st.nextToken());
			adj[boss].add(i);
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < N + 1; i++) {
			w[i] = Integer.parseInt(st.nextToken());
		}


		int answer = Math.max(dfs(1, YES), dfs(1, NO));
//		for (int i = 1; i <= N; i++) {
//			System.out.println(dp[YES][i] + ", " + dp[NO][i]);
//		}
		System.out.println(answer);
	}

	private static int dfs(int root, int isMento) {
		int profitSum = 0;

		if (dp[isMento][root] != -1)
			return dp[isMento][root];

		// 현재 노드가 멘토라면 자식 중에서 최대 이익이 되는 경우를 찾는다.
		if (isMento == YES) {

			// leaf node 는 멘토가 될 수 없음
			if (adj[root].size() == 0)
				return 0;

			int maxDiff = Integer.MAX_VALUE;
			int mentee = 0;
			// 자신의 자식 노드 중 가장 가성비 좋은 멘티 찾기
			for (Integer child : adj[root]) {

				// 자식 노드를 멘티로 고르지 않았을 때 - 골랐을 때의 이익 차이 (이 자식을 멘티로 함으로서 발생하는 손해)
				// 이익차이 = (해당 자식이 멘토이거나 or 아니거나 일때 최대값) - (현재 노드가 멘토라서 현재노드 * 자식노드 + 자식 노드가 멘토가 아닐때의 값)
				int diff = Math.max(dfs(child, NO), dfs(child, YES)) - (w[root] * w[child] + dfs(child, NO));
				// 해당 자식을 멘티로 고르지 않았을 때 얻을 수 있는 이익을 전부 더함
				profitSum += Math.max(dfs(child, NO), dfs(child, YES));

				if (diff < maxDiff) {
					maxDiff = diff;
					mentee = child;
				}
			}

			profitSum -= Math.max(dfs(mentee, NO), dfs(mentee, YES)); // 멘티를 골랐으므로 멘티로 고르지 않은 경우 얻는 이익을 빼고
			profitSum += w[root] * w[mentee] + dfs(mentee, NO); // 멘티를 골랐을 때 얻는 이익을 더함, 멘티면 멘토일 수 없다
		}
		// 현재 노드를 멘토로 하지 않으므로 자식들의 멘토일 때 이익 or 멘토가 아닌 경우 이익 중 더 큰 이익을 더함
		else {
			for (Integer child : adj[root]) {
				profitSum += Math.max(dfs(child, YES), dfs(child, NO));
			}
		}

		dp[isMento][root] = profitSum;
		return profitSum;
	}
}
