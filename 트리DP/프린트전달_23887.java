package 트리DP;

import java.util.*;
import java.io.*;

public class 프린트전달_23887 {
	static int N;
	static int M;
	static int K;
	static ArrayList<Integer>[] adj;
	static int[][] map;
	static int[] dp;
	static int R;
	static boolean[] visited;
	static int childCount = 0;
	static StringBuilder answer = new StringBuilder();
	static HashMap<Integer, Dot> hashMap = new HashMap<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N + 1][M + 1];
		adj = new ArrayList[K + 1];
		for (int i = 0; i < K + 1; i++) {
			adj[i] = new ArrayList<>();
		}
		visited = new boolean[N * M + 1];
		dp = new int[K + 1];

		for (int i = 1; i <= K; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			map[y][x] = i;
			hashMap.put(i, new Dot(i, y, x));
		}
		R = Integer.parseInt(br.readLine());

		makeTree(hashMap.get(R).number);

		if (childCount == K - 1) {
			dfs(R);
			for (int i = 1; i < K + 1; i++) answer.append(dp[i]).append(" ");
		} else {
			answer.append("-1");
		}

		System.out.print(answer);
	}

	private static void dfs(int root) {
		dp[root] = 1;

		for (int child : adj[root]) {
			dfs(child);
			dp[root] += dp[child];
		}
	}

	private static void makeTree(int number) {
		int[] dy = {-1, 1, 0, 0, 1, 1, -1, -1};
		int[] dx = {0, 0, 1, -1, -1, 1, -1, 1};

		Queue<Integer> queue = new LinkedList<>();
		queue.add(number);
		visited[number] = true;

		while (!queue.isEmpty()) {
			int SIZE = queue.size();
			PriorityQueue<Integer> childPq = new PriorityQueue<>();

			for (int i = 0; i < SIZE; i++) {
				Dot cur = hashMap.get(queue.poll());
				int cy = cur.y;
				int cx = cur.x;
				int cn = cur.number;

				for (int d = 0; d < 8; d++) {
					int ny = cy + dy[d];
					int nx = cx + dx[d];

					if (ny <= 0 || nx <= 0 || ny > N || nx > M) continue;
					if (visited[map[ny][nx]]) continue;
					if (map[ny][nx] > 0) {
						visited[map[ny][nx]] = true;
						childCount++;
						adj[cn].add(map[ny][nx]);
						childPq.add(map[ny][nx]);
					}
				}
			}
			if (!childPq.isEmpty()) {
				while (!childPq.isEmpty()) {
					queue.add(childPq.poll());
				}
			}
		}
	}

	private static class Dot {
		int number;
		int y;
		int x;

		public Dot(int number, int y, int x) {
			this.number = number;
			this.y = y;
			this.x = x;
		}
	}
}
