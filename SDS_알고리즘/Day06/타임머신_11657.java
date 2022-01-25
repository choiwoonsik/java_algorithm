package SDS_알고리즘.Day06;

import java.io.*;
import java.util.*;

public class 타임머신_11657 {
	static int N;
	static int M;
	static int INF = 987654321;
	static long[] vertexTable;
	static long[] lastTable;
	static ArrayList<Edge> edgeTable = new ArrayList<>();
	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		vertexTable = new long[N + 1];
		Arrays.fill(vertexTable, INF);
		vertexTable[1] = 0;

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			edgeTable.add(new Edge(from, to, cost));
		}

		for (int i = 1; i <= N; i++) {

			for (Edge cur : edgeTable) {
				int from = cur.from;
				int to = cur.to;
				int cost = cur.cost;

				if (vertexTable[from] == INF) continue;
				if (vertexTable[to] > vertexTable[from] + cost) {
					vertexTable[to] = vertexTable[from] + cost;
				}
			}
			if (i == N - 1) {
				lastTable = vertexTable.clone();
			}
		}

		boolean isLoop = false;
		for (int i = 1; i <= N; i++) {
			if (lastTable[i] != vertexTable[i]) {
				isLoop = true;
				break;
			}
		}

		if (isLoop) answer.append(-1);
		else {
			for (int i = 2; i <= N; i++) {
				long cost = lastTable[i];
				if (cost == INF) answer.append(-1).append("\n");
				else answer.append(cost).append("\n");
			}
		}
		System.out.print(answer);
	}

	private static class Edge {
		int from;
		int to;
		int cost;

		public Edge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
	}
}
