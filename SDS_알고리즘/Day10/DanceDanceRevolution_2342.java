package SDS_알고리즘.Day10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DanceDanceRevolution_2342 {
	static int[][][] DP;
	static int[][] W;
	static int[] directions;
	static int INF = 987654321;
	static int min = INF;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		DP = new int[100002][5][5];
		W = new int[5][5];
		directions = new int[100002];

		for (int i = 0; i < 5; i++) {
			W[0][i] = 2;
		}

		W[1][2] = 3;
		W[1][4] = 3;
		W[2][1] = 3;
		W[2][3] = 3;
		W[3][2] = 3;
		W[3][4] = 3;
		W[4][3] = 3;
		W[4][1] = 3;

		W[1][3] = 4;
		W[3][1] = 4;
		W[2][4] = 4;
		W[4][2] = 4;

		for (int i = 1; i <= 4; i++) {
			W[i][i] = 1;
		}

		int turn = 1;
		st = new StringTokenizer(br.readLine());
		while (true) {
			int dir = Integer.parseInt(st.nextToken());
			if (dir == 0) break;
			directions[turn++] = dir;
		}

		for (int t = 1; t < turn; t++) {
			for (int left = 0; left < 5; left++) {
				for (int right = 0; right < 5; right++) {
					DP[t][left][right] = INF;
				}
			}
		}

		DP[1][directions[1]][0] = 2;
		DP[1][0][directions[1]] = 2;

		for (int t = 1; t < turn; t++) {
			for (int left = 0; left < 5; left++) {
				for (int right = 0; right < 5; right++) {
					if (DP[t][left][right] != INF) {
						int next = directions[t + 1];
						if (next != right) {
							DP[t + 1][next][right] = Math.min(DP[t + 1][next][right], DP[t][left][right] + W[left][next]);
						}
						if (next != left) {
							DP[t + 1][left][next] = Math.min(DP[t + 1][left][next], DP[t][left][right] + W[right][next]);
						}
					}
				}
			}
		}

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				min = Math.min(DP[turn - 1][i][j], min);
			}
		}

		System.out.println(min);
	}
}
