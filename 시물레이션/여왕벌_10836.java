package 시물레이션;

import java.util.*;
import java.io.*;

public class 여왕벌_10836 {
	static int N;
	static int M;
	static int[] box;
	static int[][] plusBox;
	static int y, x;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		box = new int[N + 1];
		plusBox = new int[N + 1][N + 1];

		for (int i = 0; i < M; i++) {
			y = N;
			x = 1;
			st = new StringTokenizer(br.readLine());
			int zeroCount = Integer.parseInt(st.nextToken());
			int oneCount = Integer.parseInt(st.nextToken());
			int twoCount = Integer.parseInt(st.nextToken());

			update(0, zeroCount);
			update(1, oneCount);
			update(2, twoCount);
		}

		fillDiffBox();
	}

	private static void update(int plus, int count) {
		while (count-- > 0) {
			if (y == 1) {
				plusBox[y][x] += plus;
				x++;
			} else {
				plusBox[y][x] += plus;
				y--;
			}
		}
	}

	private static void fillDiffBox() {

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				if (i == 1) {
					box[j] = 1 + plusBox[1][j];
					sb.append(box[j]).append(" ");
				}
				else {
					if (j == 1) sb.append(1 + plusBox[i][1]).append(" ");
					else sb.append(box[j]).append(" ");
				}
			}
			sb.append("\n");
		}

		System.out.print(sb);
	}
}
