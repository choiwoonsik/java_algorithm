package SDS_알고리즘.Day07;

import java.io.*;
import java.util.*;
/*
5 10 2
1 2 2
1 3 7
1 4 5
1 5 6
2 4 2
2 3 4
3 4 6
3 5 8
5 2 4
5 4 1

6 8 3
1 5 1
1 2 2
1 4 1
1 6 1
2 3 3
3 4 4
5 4 100
6 4 100
 */
public class K번째_최단경로_찾기_1854 {
	static int N;
	static int M;
	static int K;
	static int S = 1;
	static PriorityQueue<Integer>[] maxHeapQ;
	static ArrayList<Edge>[] adj;
	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		maxHeapQ = new PriorityQueue[N + 1];
		adj = new ArrayList[N + 1];
		for (int i = 1; i < N + 1; i++) {
			maxHeapQ[i] = new PriorityQueue<>(Comparator.comparing(c -> -c));
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			adj[u].add(new Edge(v, c));
		}

		kDijkstra();

		for (int i = 1; i < N + 1; i++) {
			if (maxHeapQ[i].size() == K) answer.append(maxHeapQ[i].poll());
			else answer.append("-1");
			answer.append("\n");
		}
		System.out.print(answer);
	}

	private static void kDijkstra() {
		PriorityQueue<Edge> queue = new PriorityQueue<>(Comparator.comparing(E -> E.cost));
		queue.add(new Edge(S, 0));
		maxHeapQ[S].add(0);

		while (!queue.isEmpty()) {
			Edge cur = queue.poll();

			// 더 크면 갱신하지 않음
			if (cur.cost > maxHeapQ[cur.node].peek())
				continue;

			for (Edge next : adj[cur.node]) {
				// 비어있다면 추가
				if (maxHeapQ[next.node].size() < K) {
					maxHeapQ[next.node].add(cur.cost + next.cost);
					queue.add(new Edge(next.node, cur.cost + next.cost));
				} else if (maxHeapQ[next.node].peek() > cur.cost + next.cost) {
					// 차있다면 k번째를 유지하기위해 k번째 보다 더 작은 경우 갱신한다
					maxHeapQ[next.node].poll();
					maxHeapQ[next.node].add(cur.cost + next.cost);
					queue.add(new Edge(next.node, cur.cost + next.cost));
				}
			}
		}
	}

	private static class Edge {
		int node;
		int cost;

		public Edge(int node, int cost) {
			this.node = node;
			this.cost = cost;
		}
	}
}
