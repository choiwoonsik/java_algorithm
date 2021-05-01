package Gold이상문제_정리;

import java.io.*;
import java.util.*;

/*
7
1 6
6 3
3 5
4 1
2 4
4 7
 */

public class 트리의부모찾기_11725 {
	static ArrayList<Integer>[] adj;
	static int[] parent;
	static StringBuilder answer = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		adj = new ArrayList[N+1];
		parent = new int[N+1];
		for (int i = 1; i < N + 1; i++) adj[i] = new ArrayList<>();



		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			adj[n1].add(n2);
			adj[n2].add(n1);
		}

		find(1);
		for (int i = 2; i <= N; i++)
			answer.append(parent[i]).append("\n");
		System.out.println(answer);
	}

	private static void find(int root) {
		for (Integer n : adj[root]) {
			if (parent[n] == 0) {
				parent[n] = root;
				find(n);
			}
		}
	}
}
