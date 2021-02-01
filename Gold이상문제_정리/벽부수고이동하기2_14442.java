package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 벽부수고이동하기2_14442 {
	static int Min = 100000000;
	static int N, M, K;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static String[] board = new String[1001];
	static boolean[][][] visited = new boolean[1001][1001][11];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++)
			board[i] = br.readLine();

		bfs(new Node(0, 0, 1, 0));
		if (Min == 100000000)
			System.out.println(-1);
		else
			System.out.println(Min);
	}

	private static void bfs(Node node) {
		Queue<Node> queue = new LinkedList<>();
		queue.add(node);

		while (!queue.isEmpty())
		{
			Node now = queue.poll();
			visited[now.i][now.j][now.destroy] = true;

			if (now.i == N -1 && now.j == M - 1) {
				Min = Math.min(now.dist, Min);
				return;
			}

			for (int d = 0; d < 4; d++) {
				int ny = now.i + dy[d];
				int nx = now.j + dx[d];
				if (ny >= 0 && ny < N && nx >= 0 && nx < M) {
					if (!visited[ny][nx][now.destroy] && board[ny].charAt(nx) == '0')
					{
						visited[ny][nx][now.destroy] = true;
						queue.add(new Node(ny, nx, now.dist+1, now.destroy));
					}
					else if (now.destroy < K && !visited[ny][nx][now.destroy + 1]) {
						visited[ny][nx][now.destroy + 1] = true;
						queue.add(new Node(ny, nx, now.dist + 1, now.destroy + 1));
					}
				}
			}
 		}
	}

	private static class Node
	{
		int i, j, dist, destroy;

		public Node(int i, int j, int dist, int destroy) {
			this.i = i;
			this.j = j;
			this.dist = dist;
			this.destroy = destroy;
		}
	}
}
