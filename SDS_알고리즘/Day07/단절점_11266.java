package SDS_알고리즘.Day07;

import java.io.*;
import java.util.*;

/*
7 7
1 4
4 5
5 1
1 6
6 7
2 7
7 3
 */
public class 단절점_11266 {
	static int V;
	static int E;
	static int order;
	static int[] searchOrder;
	static ArrayList<Integer>[] adj;
	static boolean[] isCutPoint;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		searchOrder = new int[V + 1];
		adj = new ArrayList[V + 1];
		isCutPoint = new boolean[V + 1];
		for (int i = 0; i < V + 1; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			adj[u].add(v);
			adj[v].add(u);
		}

		for (int i = 1; i < V + 1; i++) {
			if (searchOrder[i] == 0) {
				dfs(i, true);
			}
		}

		ArrayList<Integer> cutList = new ArrayList<>();
		for (int i = 1; i < V+1; i++) {
			if (isCutPoint[i]) {
				cutList.add(i);
			}
		}
		cutList.sort(null);
		System.out.println(cutList.size());
		for (Integer idx : cutList) {
			System.out.print(idx+" ");
		}
	}

	private static int dfs(int cur, boolean isRoot) {
		order++;
		searchOrder[cur] = order;
		int min = order;
		int child = 0;

		for (Integer next : adj[cur]) {
			if (searchOrder[next] == 0) {
				child++;

				int low = dfs(next, false);

				if (!isRoot && low >= searchOrder[cur]) {
					isCutPoint[cur] = true;
				}

				min = Math.min(min, low);
			} else {
				min = Math.min(min, searchOrder[next]);
			}
		}

		if (isRoot && child >= 2) isCutPoint[cur] = true;

		return min;
	}
}
