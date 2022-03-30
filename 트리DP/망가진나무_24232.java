package 트리DP;

import java.util.*;
import java.io.*;

/*
5
2 4
2 3
3 1
5 1
 */
public class 망가진나무_24232 {
	static int N;
	static ArrayList<Node> adj[];
	static int[] dp;
	static boolean[] visited;
	static int IN = 1, OUT = 0;
	static int[] reverseArr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		dp = new int[N + 1];
		adj = new ArrayList[N + 1];
		visited = new boolean[N + 1];
		for (int i = 0; i < N + 1; i++) {
			adj[i] = new ArrayList<>();
		}
		reverseArr = new int[N + 1];

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			adj[u].add(new Node(v, OUT, i + 1));
			adj[v].add(new Node(u, IN, i + 1));
		}

		dp[1] = dfsOne(1);

		Arrays.fill(visited, false);
		visited[1] = true;
		for (Node next : adj[1]) {
			dfsOthers(1, next.here, next.dir);
		}

		int min = Integer.MAX_VALUE;
		int minNode = 0;
		for (int i = 1; i < N + 1; i++) {
			int cost = dp[i];
			if (cost < min) {
				min = cost;
				minNode = i;
			}
		}

		Arrays.fill(visited, false);
		dfsMin(minNode);

		StringBuilder ret = new StringBuilder();
		for (int i = 1; i < N; i++) {
			ret.append(reverseArr[i]);
		}
		System.out.println(ret);
	}

	private static void dfsMin(int root) {
		visited[root] = true;

		for (Node next : adj[root]) {
			if (visited[next.here]) continue;

			dfsMin(next.here);

			reverseArr[next.num] = next.dir;
		}
	}

	private static void dfsOthers(int from, int to, int dir) {
		visited[to] = true;

		if (dir == IN) {
			dp[to] += dp[from] - 1;	// to 에서는 바로 갈 수 있으므로 비용 - 1
		} else if (dir == OUT) {
			dp[to] += dp[from] + 1; // to 에서는 역으로 뒤집는 비용 + 1
		}
		for (Node next : adj[to]) {
			if (!visited[next.here]) {
				dfsOthers(to, next.here, next.dir);
			}
		}
	}

	private static int dfsOne(int root) {
		visited[root] = true;
		int ret = 0;

		for (Node next : adj[root]) {
			if (!visited[next.here]) {
				ret += dfsOne(next.here) + next.dir; // 역방향은 비용 + 1
				// 예를들어 1 -> 3은 역방향이므로 (3이 루트인 서브트리에서의 최대 비용) + (1 <- 3 비용 1)
			}
		}
		return ret;
	}

	private static class Node {
		int here;
		int dir;
		int num;

		public Node(int here, int dir, int num) {
			this.here = here;
			this.dir = dir;
			this.num = num;
		}
	}
}
