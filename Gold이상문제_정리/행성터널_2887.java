package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 행성터널_2887 {
	static Dot[] p;
	static Dot[] planet;
	static int N;
	static PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(s -> s.span));
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());

		p = new Dot[N];
		planet = new Dot[N];
		for (int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			p[i] = new Dot(x, y, z, i);
			planet[i] = p[i];
		}

		// 각 행성 마다 터널의 비용은 x, y, z중 최소값이다 => 각 좌표 기준 정렬 후 붙어있는 애들끼리 정렬
		Arrays.sort(planet, Comparator.comparingInt(s -> s.x));
		for (int i = 0; i < N - 1; i++)
			pq.add(new Edge(planet[i], planet[i+1],  Math.abs(planet[i+1].x - planet[i].x)));

		Arrays.sort(planet, Comparator.comparingLong(s -> s.y));
		for (int i = 0; i < N - 1; i++)
			pq.add(new Edge(planet[i], planet[i + 1], Math.abs(planet[i].y - planet[i + 1].y)));

		Arrays.sort(planet, Comparator.comparingLong(s -> s.z));
		for (int i = 0; i < N - 1; i++)
			pq.add(new Edge(planet[i], planet[i + 1], Math.abs(planet[i].z - planet[i + 1].z)));

		// pq는 최소 거리가 담긴 우선순위 큐
		int selected = 0;
		int total_span = 0;
		while (selected < N -1 && !pq.isEmpty())
		{
			Edge edge = pq.poll();

			if (find(edge.s) != find(edge.e))
			{
				union(edge.s, edge.e);
				selected++;
				total_span += edge.span;
			}
		}
		System.out.println(total_span);
	}

	private static Dot find(Dot s) {
		if (p[s.index] == s) return s;
		return p[s.index] = find(p[s.index]);
	}

	private static void union(Dot s, Dot e)
	{
		p[p[s.index].index] = p[e.index];
	}

	private static class Dot
	{
		int x, y, z, index;

		public Dot(int x, int y, int z, int index) {
			this.x = x;
			this.y = y;
			this.z = z;
			this.index = index;
		}
	}

	private static class Edge {
		Dot s;
		Dot e;
		int span;

		public Edge(Dot s, Dot e, int span) {
			this.s = s;
			this.e = e;
			this.span = span;
		}
	}
}
