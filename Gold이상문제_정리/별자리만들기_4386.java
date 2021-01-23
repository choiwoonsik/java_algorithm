package Gold이상문제_정리;

import java.util.*;
import java.io.*;

public class 별자리만들기_4386 {
	static int N;
	static Dot[] star;
	static PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingDouble(s -> s.span));
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		star = new Dot[N];

		for (int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			Double s, e;
			s = Double.parseDouble(st.nextToken());
			e = Double.parseDouble(st.nextToken());
			star[i] = new Dot(s, e, i);
		}

		// 모든 간선 정보를 별끼리 만들어서 우선순위 큐에 넣어준다
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				Dot start = star[i];
				Dot end = star[j];
				double span = Math.sqrt(Math.pow((start.y - end.y), 2) + Math.pow((start.x - end.x), 2));
				pq.add(new Edge(start, end, span));
			}
		}

		int selected = 0;
		Double total_span = 0.0;

		while (selected < N -1 && !pq.isEmpty())
		{
			Edge edge = pq.poll();

			if (find(edge.start) != find(edge.end))
			{
				union(edge.start, edge.end);
				selected++;
				total_span += edge.span;
			}
		}
		System.out.println(String.format("%.2f", total_span));
	}

	private static void union(Dot start, Dot end) {
		star[star[start.index].index] = star[end.index];
	}

	private static Dot find(Dot dot) {
		if (star[dot.index] == dot) return dot;
		return star[dot.index] = find(star[dot.index]);
	}

	private static class Dot
	{
		double y, x;
		int index;

		public Dot(double y, double x, int index) {
			this.y = y;
			this.x = x;
			this.index = index;
		}
	}

	private static class Edge
	{
		Dot start;
		Dot end;
		double span;

		public Edge(Dot s, Dot e, double span) {
			this.start = s;
			this.end = e;
			this.span = span;
		}
	}
}
