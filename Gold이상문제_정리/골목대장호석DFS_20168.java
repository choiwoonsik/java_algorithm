package Gold이상문제_정리;

import java.util.*;
import java.io.*;

public class 골목대장호석DFS_20168 {
	static final int MAX = 1000000000;
	static int N, M, myS, myE, myC, answer = MAX;
	static ArrayList<Integer>[] adj;
	static int[][] cost;
	static boolean[] vst;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		myS = Integer.parseInt(st.nextToken()) - 1;
		myE = Integer.parseInt(st.nextToken()) - 1;
		myC = Integer.parseInt(st.nextToken());

		// 교차로 : 정점
		// 골목 : 간선
		cost = new int[N][N];
		vst = new boolean[N];
		adj = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken());
			cost[from][to] = c;
			cost[to][from] = c;
			adj[from].add(to);
			adj[to].add(from);
		}

		vst[myS] = true;
		dijkstraDFS(myS, myC, -1);

		if (answer == MAX) System.out.println(-1);
		else System.out.println(answer);
	}

	private static void dijkstraDFS(int now, int myMoney, int maxCost) {
		if (myMoney < 0) return;

		if (now == myE) {
			answer = Math.min(answer, maxCost);
			return;
		}

		for (int next : adj[now]) {
			if (!vst[next]) {
				vst[next] = true;
				int moveC = cost[now][next];
				dijkstraDFS(next, myMoney - moveC, Math.max(maxCost, moveC));
				vst[next] = false;
			}
		}
	}
}
