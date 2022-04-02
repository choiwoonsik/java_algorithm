package 트리;

import java.util.*;
import java.io.*;

/*
4 1000000000
1 2
1 3
1 4
 */
public class 나무위의빗물_17073 {
	static int N;
	static double W;
	static ArrayList<Integer>[] adj;
	static boolean[] visited;
	static int leaf;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		W = Double.parseDouble(st.nextToken());

		visited = new boolean[N + 1];
		adj = new ArrayList[N + 1];
		for (int i = 0; i < N + 1; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			adj[u].add(v);
			adj[v].add(u);
		}

		solution();

		System.out.format("%.10f", W / leaf);
	}

	private static void solution() {
		for (int i = 2; i < N + 1; i++) {
			if (adj[i].size() < 2) leaf++;
		}
	}
}
