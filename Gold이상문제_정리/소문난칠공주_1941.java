package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 소문난칠공주_1941 {
	static char[][] map;
	static boolean[] visited;
	static int count;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		map = new char[5][5];
		visited = new boolean[25];
		for (int i = 0; i < 5; i++) {
			String str = br.readLine();
			for (int j = 0; j < 5; j++) {
				map[i][j] = str.charAt(j);
			}
		}

		// 25개 중에서 7개 고르기 -> 붙어있고 4개이상인지 확인
		backtracking(0, 0);
		System.out.println(count);
	}

	private static boolean isSeven()
	{
		boolean[][] checked = new boolean[5][5];
		Queue<Dot> queue = new LinkedList<>();

		int som = 0, seven = 0;
		int y = 0;
		int x = 0;

		for (int i = 0; i < 25; i++) {
			if (visited[i]) {
				y = i / 5;
				x = i % 5;
				break;
			}
		}

		checked[y][x] = true;
		queue.add(new Dot(y, x));
		seven++;
		while (!queue.isEmpty())
		{
			Dot now = queue.poll();
			for (int d = 0; d < 4; d++)
			{
				int ny = now.y + dy[d];
				int nx = now.x + dx[d];
				if (ny >= 0 && ny < 5 && nx >= 0 && nx < 5) {
					if (!checked[ny][nx] && visited[ny * 5 + nx]) {
						checked[ny][nx] = true;
						queue.add(new Dot(ny, nx));
						seven++;
					}
				}
			}
		}

		for (int i = 0; i < 25; i++) {
			if (visited[i]) {
				if (map[i / 5][i % 5] == 'S')
					som++;
			}
		}
		return seven == 7 && som >= 4;
	}


	private static void backtracking(int cnt, int idx) {
		if (cnt == 7)
		{
			if (isSeven())
				count++;
			return;
		}
		for (int i = idx; i < 25; i++) {
			if (!visited[i]){
				visited[i] = true;
				backtracking(cnt + 1, i + 1);
				visited[i] = false;
			}
		}
	}

	private static class Dot
	{
		int y, x;

		public Dot(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
