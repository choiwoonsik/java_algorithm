package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 사이클게임_20040 {
	static int N, M;
	static int[] parent;

	private static int find(int n) {
		if (n == parent[n]) return n;
		return parent[n] = find(parent[n]);
	}

	private static void union(int u, int v) {
		u = parent[u];
		v = parent[v];
		parent[u] = v;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		parent = new int[N];

		for (int i = 0; i < N; i++)
			parent[i] = i;

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			if (find(u) != find(v))
				union(u, v);
			else {
				System.out.println(i+1);
				return;
			}
		}
		System.out.println(0);
	}
}
