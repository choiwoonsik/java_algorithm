package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class two_dots_16929 {
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, 1, -1};
	static int N, M;
	static boolean isOk = false;
	static char[][] map = new char[51][51];
	static boolean[][] visited = new boolean[51][51];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visited[i][j]) {
					visited[i][j] = true;
					if (DFS(new Node(i, j), map[i][j], i, j, 1))
					{
						System.out.println("Yes");
						return;
					}
				}
				visited = new boolean[N][M];
			}
		}

		System.out.println("No");
	}

	private static boolean DFS(Node node, char now, int I, int J, int t) {

		for (int d = 0; d < 4; d++) {
			int ny = node.i + dy[d];
			int nx = node.j + dx[d];

			if (t >= 4 && ny == I && nx == J) isOk = true;

			if (ny < 0 || nx < 0 || ny >= N || nx >= M) continue;
			if (map[ny][nx] == now && !visited[ny][nx]) {
				visited[ny][nx] = true;
				DFS(new Node(ny, nx), now, I, J, t + 1);
			}
		}
		return isOk;
	}

	private static class Node
	{
		int i, j;

		public Node(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
}
