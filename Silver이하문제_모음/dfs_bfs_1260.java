package Silver이하문제_모음;

import java.util.*;

public class dfs_bfs_1260 {
	static int	N;	//정점의 개수
	static int	M;	//간선의 개수
	static int	V;	//start node
	static int	D;	//탐색 깊
	static boolean[]	visited;	//방문한 좌표 체크
	static int[][] 		graphNN;
	static ArrayList<Integer> list = new ArrayList<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		V = sc.nextInt();
		D = 0;
		graphNN = new int[N + 1][N + 1];
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= N; j++) {
				graphNN[i][j] = 0;
			}
		}
		int	L = 0;
		while (L < M) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			graphNN[x][y] = 1;
			graphNN[y][x] = 1;
			L++;
		}

		visited = new boolean[N + 1];
		list.add(V);
		dfs(V);
		for (int l : list)
			System.out.print(l + " ");

		visited = new boolean[N + 1];
		list.clear();
		System.out.println();

		BFS();
		for (int l : list)
			System.out.print(l + " ");

	}

	static void dfs(int start)
	{
		visited[start] = true;
		if (!list.contains(start))
			list.add(start);
		for (int n = 1; n <= N; n++) {
			if (graphNN[start][n] == 1 && !visited[n]) {
				visited[n] = true;
				dfs(n);
			}
		}
	}

	static void BFS()
	{
		Queue<Integer> que = new LinkedList<>();
		que.add(V);
		visited[V] = true;
		while (!que.isEmpty())
		{
			int now = que.poll();
			if (!list.contains(now))
				list.add(now);
			for (int i = 1; i <= N; i++)
			{
				if (graphNN[now][i] == 1 && !visited[i]) {
					que.add(i);
					visited[i] = true;
				}
			}
		}
	}
}