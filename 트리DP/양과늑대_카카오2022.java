package 트리DP;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class 양과늑대_카카오2022 {
	public static void main(String[] args) {
		Sol sol = new Sol();
		sol.solution(new int[]{0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1}, new int[][]{{0, 1}, {1, 2}, {1, 4}, {0, 8}, {8, 7}, {9, 10}, {9, 11}, {4, 3}, {6, 5}, {4, 6}, {8, 9}});
	}

	private static class Sol {
		static ArrayList<Integer>[] adj;
		static int[] infos;
		static int L;
		static boolean[][] visited;
		static int SHEEP = 0;
		static int WOLF = 1;
		static int max = 0;

		private int solution(int[] info, int[][] edges) {
			infos = info.clone();
			L = infos.length;
			adj = new ArrayList[L];
			visited = new boolean[L][1 << L];
			for (int i = 0; i < L; i++) {
				adj[i] = new ArrayList<>();
			}

			for (int[] edge : edges) {
				int u = edge[0];
				int v = edge[1];
				adj[u].add(v);
				adj[v].add(u);
			}

			visited[0][1] = true;
			bfs(new Node(0, 1, 0, 1));

			System.out.println(max);

			return max;
		}

		private void bfs(Node node) {
			Queue<Node> queue = new LinkedList<>();
			queue.add(node);

			while (!queue.isEmpty()) {
				Node cur = queue.poll();

				max = Math.max(cur.sheep, max);

				for (Integer next : adj[cur.cur]) {
					int sheep = cur.sheep;
					int wolf = cur.wolf;
					int visit = cur.visited;

					if (infos[next] == SHEEP && (cur.visited & 1 << next) == 0) {
						sheep += 1;
					}
					if (infos[next] == WOLF && (cur.visited & 1 << next) == 0) {
						wolf += 1;
					}

					if (visited[next][cur.visited]) continue;
					if (wolf >= sheep) continue;

					visited[next][cur.visited] = true;
					visit |= 1 << next;
					queue.add(new Node(next, sheep, wolf, visit));
				}
			}
		}

		private static class Node {
			int cur;
			int sheep;
			int wolf;
			int visited;

			public Node(int cur, int sheep, int wolf, int visited) {
				this.cur = cur;
				this.sheep = sheep;
				this.wolf = wolf;
				this.visited = visited;
			}
		}
	}
}
