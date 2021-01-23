package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 최소스패닝트리_1197 {
	static int V, E;
	static int[] p;
	static PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(s -> s.span));
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		p = new int[V+1];
		for (int i = 0; i < V; i++)
			p[i] = i;

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int span = Integer.parseInt(st.nextToken());
			pq.add(new Edge(s, e, span));
		}

		int selected = 0;
		int total_span = 0;
		while (selected < V - 1 && !pq.isEmpty())
		{
			Edge now = pq.poll();

			// 사이클 확인
			if (find(now.s) != find(now.e))
			{
				union(now.s, now.e);
				selected++;
				total_span += now.span;
			}
		}
		if (selected == V - 1)
			System.out.println(total_span);
	}
	private static int find(int v)
	{
		if (p[v] == v) return v;
		return p[v] = find(p[v]);
	}
	private static void union(int s, int e)
	{
		s = find(s);
		e = find(e);
		p[p[s]] = p[e];
	}

	private static class Edge
	{
		int s;
		int e;
		int span;

		public Edge(int s, int e, int span) {
			this.s = s;
			this.e = e;
			this.span = span;
		}
	}
}
