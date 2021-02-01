package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 내리막길_1520 {
	static int[][] map;
	static int[][] DP;
	static boolean[][] visited;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static int N, M;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		map = new int[M][N];
		DP = new int[M][N];
		visited = new boolean[M][N];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				DP[i][j] = -1;
			}
		}

		int ans = DFS(0, 0);
		System.out.println(ans);
	}
	private static int DFS(int i, int j)
	{
		if (i == M-1 && j == N-1)
			return 1;

//		System.out.println("\n************");
//		for (int k = 0; k < M; k++) {
//			for (int l = 0; l < N; l++) {
//				if (DP[k][l] < 0)
//					System.out.print(DP[k][l]+" ");
//				else
//					System.out.print(" "+DP[k][l]+" ");
//			}
//			System.out.println();
//		}

		if (DP[i][j] >= 0) return DP[i][j];

		DP[i][j] = 0;
		for (int d = 0; d < 4; d++) {
			int ny = i + dy[d];
			int nx = j + dx[d];
			if (ny >= 0 && ny < M && nx >= 0 && nx < N) {
				if (map[ny][nx] < map[i][j] && !visited[ny][nx]) {
					visited[ny][nx] = true;
					DP[i][j] += DFS(ny, nx);
					visited[ny][nx] = false;
				}
			}
		}
		return DP[i][j];
	}
}
