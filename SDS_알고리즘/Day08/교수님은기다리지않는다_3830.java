package SDS_알고리즘.Day08;

import java.io.*;
import java.util.*;

/*
2 2
! 1 2 1
? 1 2
2 2
! 1 2 1
? 2 1
4 7
! 1 2 100
? 2 3
! 2 3 100
? 2 3
? 1 3
! 4 3 150
? 4 1
0 0

 */

public class 교수님은기다리지않는다_3830 {
	static int N;
	static int M;
	static int[] parent;
	static long[] rootCost;
	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		while (true) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			if (N == 0 && M == 0) break;

			parent = new int[N + 1];
			rootCost = new long[N + 1];
			for (int i = 1; i < N + 1; i++) {
				parent[i] = i;
			}

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());

				String query = st.nextToken();
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());

				if (query.equals("!")) {
					int diff = Integer.parseInt(st.nextToken());
					union(u, v, diff);
				} else {
					long diff;
					if (find(u) != find(v))
						answer.append("UNKNOWN");
					else {
						diff = rootCost[v] - rootCost[u];
						answer.append(diff);
					}
					answer.append("\n");
				}
			}
		}

		System.out.print(answer);
	}

	private static void union(int u, int v, int diff) {
		int pU = find(u);
		int pV = find(v);

		if (pU == pV) return;

		rootCost[pV] = -rootCost[v] + rootCost[u] + diff;
		parent[parent[pV]] = parent[pU];
	}

	private static int find(int node) {
		if (parent[node] == node) return node;
		else {
			int root = find(parent[node]);
			rootCost[node] += rootCost[parent[node]];
			return parent[node] = root;
		}
	}
}
