package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 가장큰정사각형_1915 {
	static int N, M;
	static int max;
	static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N+1][M+1];

		for (int i = 1; i <= N; i++) {
			String s = br.readLine();
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(String.valueOf(s.charAt(j - 1)));
			}
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (map[i][j] != 0) {
					int size = Math.min(map[i-1][j], Math.min(map[i-1][j-1], map[i][j-1]));
					map[i][j] = size + 1;
					if (max < map[i][j]) max = map[i][j];
				}
			}
		}
		System.out.println(max*max);
	}
}