package 다이나믹프로그래밍;

public class 파괴되지않는건물_효율성_2022카카오 {

	public static void main(String[] args) {
		Solution sol = new Solution();
		int answer = sol.solution(
				new int[][]{{5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}},
				new int[][]{{1, 0, 0, 3, 4, 4}, {1, 2, 0, 2, 3, 2}, {2, 1, 0, 3, 1, 2}, {1, 0, 1, 3, 3, 1}}
		);
		System.out.println(answer == 10);
	}

	private static class Solution {
		static int ATK = 1;
		static int HEAL = 2;
		static int width;
		static int height;
		static int[][] BOARD;
		static int[][] origin;

		private int solution(int[][] board, int[][] skill) {

			int Q = skill.length;
			width = board[0].length;
			height = board.length;
			BOARD = new int[height + 2][width + 2];
			origin = new int[height][width];
			for (int i = 0; i < board.length; i++) {
				origin[i] = board[i].clone();
			}

			for (int query = 0; query < Q; query++) {
				int[] nowQuery = skill[query];
				int type = nowQuery[0];
				int r1 = nowQuery[1];
				int c1 = nowQuery[2];
				int r2 = nowQuery[3];
				int c2 = nowQuery[4];
				int degree = nowQuery[5];

				if (type == ATK) {
					query(r1, c1, r2, c2, degree * -1);
				} else if (type == HEAL) {
					query(r1, c1, r2, c2, degree);
				}
			}

			int destroyCount = 0;

			for (int row = 1; row <= height; row++) {
				for (int col = 1; col <= width; col++) {
					BOARD[row][col] += BOARD[row - 1][col] + BOARD[row][col - 1] - BOARD[row - 1][col - 1];

					if (BOARD[row][col] + origin[row - 1][col - 1] <= 0) {
						destroyCount++;
					}
				}
			}

			return height * width - destroyCount;
		}

		private void query(int r1, int c1, int r2, int c2, int degree) {
			// (r1, c1) 좌표 오른쪽아래 면적에 전부 더합
			BOARD[r1 + 1][c1 + 1] += degree;

			// (r1, c2), (r2, c1) 좌표 오른쪽아래 면적을 빼줌
			BOARD[r1 + 1][c2 + 2] -= degree;
			BOARD[r2 + 2][c1 + 1] -= degree;

			// (r2, c2) 좌표 오른쪽 아래 면적을 더해줌
			BOARD[r2 + 2][c2 + 2] += degree;
		}
	}
}
