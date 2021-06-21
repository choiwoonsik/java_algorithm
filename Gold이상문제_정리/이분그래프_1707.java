package Gold이상문제_정리;

import java.util.*;
import java.io.*;

public class 이분그래프_1707 {
	static int T, V, E;
	static boolean[] isAdj;
	static int[] visited;
	static ArrayList<Integer>[] adj;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		while (T-- > 0)
		{
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());

			adj = new ArrayList[V+1];
			isAdj = new boolean[V+1];
			visited = new int[V+1];
			for (int i = 0; i < V + 1; i++)
				adj[i] = new ArrayList<>();

			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				adj[s].add(e);
				adj[e].add(s);
			}

			boolean result = BFS();
			if (result) sb.append("YES\n");
			else sb.append("NO\n");
		}

		System.out.println(sb);
	}

	private static boolean BFS() {
		Queue<Integer> queue = new LinkedList<>();
		boolean answer = true;

		for (int i = 1; i < V + 1; i++) {
			for (int a : adj[i]) {
				if (visited[a] == 0) {
					visited[a] = 1;
					queue.add(a);
				}
			}

			while (!queue.isEmpty())
			{
				int now = queue.poll();

				for (int next : adj[now]) {
					if (visited[next] == 0) queue.add(next);

					if (visited[next] == visited[now]) {
						answer = false;
						break;
					}

					if (visited[next] == 0 && visited[now] == 1)
						visited[next] = 2;
					else if (visited[next] == 0 && visited[now] == 2)
						visited[next] = 1;
				}
				if (!answer) break;
			}
		}

		return answer;
	}
}
