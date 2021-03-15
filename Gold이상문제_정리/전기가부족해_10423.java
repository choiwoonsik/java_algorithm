package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 전기가부족해_10423 {
	static int N, M, K;
	static int[] parents;
	static int cost;
	static PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(s -> s.w));
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		parents = new int[N+1];
		for (int i = 0; i < N+1; i++) {
			parents[i] = i;
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			int k = Integer.parseInt(st.nextToken());
			parents[k] = 0;
		}

		for (int e = 0; e < M; e++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			pq.add(new Edge(u, v, w));
		}

		int selected = 0;
		// 발전소들의 부모 0번 노드까지해서 N+1개의 정점, N개의 간선이 필요
		// K개의 발전소에대한 간선은 이미 있으니 빼고 N-K개가 되면 끝
		while (selected < N - K && !pq.isEmpty())
		{
			Edge edge = pq.poll();

			if (find(edge.u) != find(edge.v)) {
				union(edge.u, edge.v);
				cost += edge.w;
				selected++;
			}
		}
		System.out.println(cost);
	}

	private static void union(int u, int v) {
		u = parents[u];
		v = parents[v];
		parents[u] = v;
	}

	private static int find(int u) {
		if (parents[u] == u) return u;
		return parents[u] = find(parents[u]);
	}

	private static class Edge
	{
		int u, v, w;

		public Edge(int u, int v, int w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}
	}
}
