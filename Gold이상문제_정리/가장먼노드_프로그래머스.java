package Gold이상문제_정리;

import java.util.*;

public class 가장먼노드_프로그래머스 {

	public static void main(String[] args) {
		solution(6, new int[][]{{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}});
	}

	static int[] dist;
	static ArrayList<Integer>[] adj;
	static int N;
	public static int solution(int n, int[][] edge) {
		int answer = 0;
		N = n;

		adj = new ArrayList[n + 1];
		for (int i = 0; i < n + 1; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < edge.length; i++) {
			int[] e = edge[i];
			adj[e[0]].add(e[1]);
			adj[e[1]].add(e[0]);
		}

		Dijkstra();

		int Max = 0;
		for (int i = 1; i <= N; i++) {
			Max = Math.max(dist[i], Max);
		}
		for (int i = 0; i <= N; i++) {
			if (Max == dist[i]) answer++;
		}

		System.out.println(answer);
		return answer;
	}

	private static void Dijkstra()
	{
		Queue<Integer> queue = new LinkedList<>();
		dist = new int[N+1];

		Arrays.fill(dist, Integer.MAX_VALUE);
		queue.add(1);
		dist[1] = 0;

		while (!queue.isEmpty())
		{
			int node = queue.poll();

			for (int next : adj[node])
			{
				if (dist[next] > dist[node] + 1) {
					dist[next] = dist[node] + 1;
					queue.add(next);
				}
			}
		}
	}

}
