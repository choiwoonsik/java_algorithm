package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 우주신과의교감_1774 {
	static int N, M;
	static double DIST;
	static int[] parent;
	static Dot[] gods;
	static PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingDouble(s -> s.dist));
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		parent = new int[N];
		gods = new Dot[N];
		for (int i = 0; i < N; i++) {
			parent[i] = i;
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			gods[i] = new Dot(x, y);
		}

		// 신 끼리 모든 거리 측정
		for (int i = 0; i < N - 1; i++) {
			for (int j = i+1; j < N; j++) {
				pq.add(new Edge(i, j, dist(gods[i], gods[j])));
			}
		}

		// 이미 연결된 신 연결
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int god1 = Integer.parseInt(st.nextToken()) - 1;
			int god2 = Integer.parseInt(st.nextToken()) - 1;
			union(god1, god2);
		}

		// 나머지 연결
		while (!pq.isEmpty()) {
			Edge edge = pq.poll();

			if (find(edge.god_1) != find(edge.god_2)) {
				DIST += edge.dist;
				union(edge.god_1, edge.god_2);
			}
		}

		System.out.printf("%.2f", DIST);
	}

	private static int find(int idx) {
		if (parent[idx] == idx) return idx;
		return parent[idx] = find(parent[idx]);
	}

	private static void union(int god1, int god2) {
		god1 = find(god1);
		god2 = find(god2);
		if (god1 < god2)
			parent[god1] = parent[parent[god2]];
		else
			parent[god2] = parent[parent[god1]];
	}

	private static double dist(Dot a, Dot b) {
		return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
	}
	private static class Dot
	{
		int x, y;

		public Dot(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	private static class Edge
	{
		int god_1, god_2;
		double dist;

		public Edge(int god_1, int god_2, double dist) {
			this.god_1 = god_1;
			this.god_2 = god_2;
			this.dist = dist;
		}
	}
}