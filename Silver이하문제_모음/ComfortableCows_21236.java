package Silver이하문제_모음;

import java.io.*;
import java.util.*;

/*
8
0 1
1 0
1 1
1 2
2 1
2 2
3 1
3 2
 */

public class ComfortableCows_21236 {
	static int N;
	static int comfort_cnt;
	static boolean[][] cows = new boolean[1001][1001];
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static StringBuilder str = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			check(x, y);
		}
		System.out.print(str);
	}

	private static void check(int x, int y) {
		int count;
		if (cows[x][y]) return;

		for (int i = 0; i < 4; i++)
		{
			int nx = dx[i] + x;
			int ny = dy[i] + y;
			if (nx < 0 || ny < 0 || nx > N || ny > N) continue;
			if (!cows[nx][ny]) continue;
			count = 0;
			for (int d = 0; d < 4; d++)
			{
				int nnx = dx[d] + nx;
				int nny = dy[d] + ny;
				if (nny < 0 || nnx < 0 || nny > N || nnx > N) continue;
				if (cows[nnx][nny]) count++;
			}
			if (count == 3) comfort_cnt--;
			else if (count == 2) comfort_cnt++;
		}
		cows[x][y] = true;
		str.append(comfort_cnt).append("\n");
	}
}
