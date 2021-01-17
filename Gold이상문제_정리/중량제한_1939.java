package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 중량제한_1939 {
	static int N, M;
	static int S, E, MAX, ANS;
	static ArrayList<pair>[] adj_list;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		MAX =  1;
		ANS = Integer.MAX_VALUE;

		adj_list = new ArrayList[N+1];
		for (int i = 1; i <= N; i++)
			adj_list[i] = new ArrayList<>();
		visited = new boolean[N+1];

		for (int j = 0; j < M; j++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int max = Integer.parseInt(st.nextToken());
			MAX = Math.max(MAX, max);
			adj_list[from].add(new pair(to, max));
			adj_list[to].add(new pair(from, max));
		}

		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		find(1, MAX+1);
	}

	private static void find(int start, int end)
	{
		while (start <= end) {
			int mid = (start + end) / 2;
			if (DFS(S, mid)) {
				ANS = mid;
				start = mid + 1;
			}
			else
				end = mid - 1;
			Arrays.fill(visited, false);
		}
		System.out.println(ANS);
	}

	private static boolean DFS(int s, int mid) {
		visited[s] = true;

		if (s == E)
			return true;
		for (pair next : adj_list[s]) {
			if (!visited[next.target] && mid <= next.weight) {
				if (DFS(next.target, mid))
					return true;
			}
		}
		return false;
	}

	private static class pair
	{
		int target;
		int weight;

		public pair(int target, int weight) {
			this.target = target;
			this.weight = weight;
		}
	}
}

/*
9 9
1 4 11
1 5 2
4 5 4
4 3 10
4 2 7
5 2 10
5 6 13
3 2 9
2 6 8
1 6
 */