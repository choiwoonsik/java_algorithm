package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 빵집_3109 {
	static int[] dy = {-1, 0, 1};
	static int dx = 1;
	static boolean[][] map;
	static boolean[][] dp;
	static int r, c;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		dp = new boolean[r][c];
		map = new boolean[r][c];
		for (int i = 0; i < r; i++) {
			String s = br.readLine();
			for (int j = 0; j < c; j++) {
				if (s.charAt(j) == '.')
					map[i][j] = true;
			}
		}

		int cnt = 0;
		for (int row = 0; row < r; row++) {
			if (find_road(row, 0))
				cnt++;
		}
		System.out.println(cnt);
	}

	private static boolean find_road(int row, int col) {
		if (col == c - 1) {
			dp[row][col] = true;
			return true;
		}

		if (dp[row][col]) return false;

		dp[row][col] = true;
		for (int d = 0; d < 3; d++) {
			int ny = dy[d] + row;
			int nx = dx + col;
			if (ny < 0 || nx < 0 || ny >= r || nx >= c) continue;
			if (!map[ny][nx]) continue;
			if (find_road(ny, nx))
				return true;
		}
		return false;
	}
}
