package 트리;

import java.io.*;
import java.util.*;

public class 나무위의구슬_14570 {
	static int N;
	static long K;
	static ArrayList<Integer[]>[] adj;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		adj = new ArrayList[N + 1];
		for (int i = 0; i < N + 1; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			adj[i].add(new Integer[]{u, v});
		}
		K = Long.parseLong(br.readLine());

		go(1, K);
	}

	private static void go(int node, long count) {
		for (Integer[] child : adj[node]) {
			int left = child[0];
			int right = child[1];

			if (left == -1 && right == -1) {
				System.out.println(node);
				return;
			} else if (left != -1 && right != -1) {
				if (count % 2 == 1) {
					go(left, count / 2 + 1);
				} else {
					go(right, count / 2);
				}
			} else if (left != -1) {
				go(left, count);
			} else if (right != -1) {
				go(right, count);
			}
		}
	}
}
