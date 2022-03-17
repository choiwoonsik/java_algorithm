package sk_2022_3;

import java.util.Arrays;

public class Main2 {
	public static void main(String[] args) {
		Solution sol = new Solution();
		int[][] solution = sol.solution(9, false);
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(solution[i][j]+" ");
			}
			System.out.println();
		}

		solution = sol.solution(1, false);
		for (int i = 0; i < 1; i++) {
			for (int j = 0; j < 1; j++) {
				System.out.print(solution[i][j]+" ");
			}
			System.out.println();
		}
	}

	static class Solution {

		static int tmpN;
		static int N;
		static int[][] map;
		static int[][] clockwiseTrueDir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
		static int[][] clockwiseFalseDir = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};

		public int[][] solution(int n, boolean clockwise) {

			map = new int[n][n];
			N = n;
			tmpN = n;
			if (n % 2 == 0) {
				makeTornado(clockwise);
			} else{
				makeTornado(clockwise);
			}

			System.out.println(Arrays.deepToString(map).replaceAll(" ", ""));
			return map;
		}

		private void makeTornado(boolean clock) {
			int [][] dir;
			if (clock) dir = clockwiseTrueDir;
			else dir = clockwiseFalseDir;

			Dot leftUp = new Dot(0, 0);
			markAndMove(dir, leftUp, 0, clock);

			Dot rightUp = new Dot(0, N - 1);
			markAndMove(dir, rightUp, 1, clock);

			Dot rightDown = new Dot(N - 1, N - 1);
			markAndMove(dir, rightDown, 2, clock);

			Dot leftDown = new Dot(N - 1, 0);
			markAndMove(dir, leftDown, 3, clock);
		}

		private void markAndMove(int[][] dir, Dot dot, int dirIdx, boolean clock) {
			tmpN = N - 1;
			int num = 1;

			if (tmpN == 0) {
				map[0][0] = 1;
			}

			while (tmpN >= 1) {
				for (int i = 0; i < tmpN; i++) {
					map[dot.y][dot.x] = num++;
					if (i == tmpN - 1) {
						if (clock) {
							dirIdx += 1;
							if (dirIdx == 4) dirIdx = 0;
						} else {
							dirIdx -= 1;
							if (dirIdx == -1) dirIdx = 3;
						}
					}
					dot.y += dir[dirIdx][0];
					dot.x += dir[dirIdx][1];
				}

				if (tmpN > 2) {
					tmpN -= 2;
				} else if (tmpN == 2) {
					tmpN = 1;
				} else if (tmpN == 1) {
					break;
				}

			}
		}
	}

	private static class Dot {
		int y;
		int x;

		public Dot(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
