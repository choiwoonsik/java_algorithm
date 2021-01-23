package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 도시분활계획_1647 {
	static int N, M;
	static int[] p;
	static PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(s -> s.span));
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		p = new int[N+1];
		for (int i = 0; i < N + 1; i++)
			p[i] = i;

		for (int i = 0; i < M; i++)
		{
			st = new StringTokenizer(br.readLine());
			int s, e, span;
			s = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			span = Integer.parseInt(st.nextToken());
			pq.add(new Edge(s, e, span));
		}

		int selected = 0;
		int total_span = 0;

		// 두개의 마을로 분할활 계획 -> 우선순위 큐 이므로 마지막의 간선이 가장 비용이 큰 간선이다.
		// 그러므로 마지막 간선을 연결하지 않으면 최소 비용으로 두개의 마을을 만들수 있다(N개의 정점에서 N-2개의 간선을 가지면 2개의 분리된 트리 생성)
		while (selected < N - 2 && !pq.isEmpty()) {
			Edge edge = pq.poll();

			// check cycle
			if (find(edge.s) != find(edge.e)){
				union(edge.s, edge.e);
				selected++;
				total_span += edge.span;
			}
		}
		System.out.println(total_span);
	}

	private static void union(int s, int e)
	{
		p[p[s]] = p[e];
	}

	private static int find(int s)
	{
		if (p[s] == s) return s;
		return p[s] = find(p[s]);
	}

	private static class Edge
	{
		int s, e, span;

		public Edge(int s, int e, int span) {
			this.s = s;
			this.e = e;
			this.span = span;
		}
	}
}
