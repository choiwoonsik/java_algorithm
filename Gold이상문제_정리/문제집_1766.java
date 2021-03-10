package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 문제집_1766 {
	static int N, M;
	static int[] degree;
	static PriorityQueue<Integer> pq = new PriorityQueue<>();
	static ArrayList<Integer>[] adj;
	static StringBuilder str = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		adj = new ArrayList[N+1];
		degree = new int[N+1];
		for (int i = 0; i < N+1; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int first = Integer.parseInt(st.nextToken());
			int next = Integer.parseInt(st.nextToken());
			adj[first].add(next);
			degree[next]++;
		}

		for (int i = 1; i < N+1; i++) {
			if (degree[i] == 0) {
				pq.add(i);
			}
		}
		while (!pq.isEmpty())
		{
			int now = pq.poll();
			str.append(now).append(" ");

			for(int next : adj[now]) {
				degree[next]--;
				if (degree[next] == 0) {
					pq.add(next);
				}
			}
		}
		System.out.println(str);
	}
}
