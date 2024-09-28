package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
5 5 1 3 10
1 2 5
2 3 5
1 4 1
4 5 6
5 3 1
 */

public class 골목대장호석BFS_20168 {
	static final int MAX = 1000000000;
	static int N, M, myS, myE, myC, answer = MAX;
	static ArrayList<Integer>[] adj;
	static int[][] cost;
	static boolean[][] visited;
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
		visited = new boolean[N][N];
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

		dijkstra();

		if (answer == MAX) System.out.println(-1);
		else System.out.println(answer);
	}

	private static void dijkstra() {
		PriorityQueue<Ho> queue = new PriorityQueue<>(Comparator.comparingInt(h -> h.maxMoveCost));
		queue.add(new Ho(myS, 0, -1));
		visited[myS][myS] = true;

		while (!queue.isEmpty())
		{
			Ho nowHo = queue.poll();
			int nowPos = nowHo.nowPos;
			int nowCost = nowHo.nowCost;

			if (nowPos == myE) {
				answer = Math.min(nowHo.maxMoveCost, answer);
				continue;
			}

			for (int next : adj[nowPos])
			{
				if (visited[nowPos][next] || visited[next][nowPos]) continue;

				int moveCost = cost[nowPos][next];
				if (nowCost + moveCost <= myC) {
					queue.add(new Ho(next, nowCost + moveCost, Math.max(nowHo.maxMoveCost, moveCost)));
					visited[nowPos][next] = true;
					visited[next][nowPos] = true;
				}
			}
		}
	}

	private static class Ho
	{
		int nowPos;
		int nowCost;
		int maxMoveCost;

		public Ho (int pos, int cost, int maxMoveCost) {
			this.nowPos = pos;
			this.nowCost = cost;
			this.maxMoveCost = maxMoveCost;
		}
	}
}
