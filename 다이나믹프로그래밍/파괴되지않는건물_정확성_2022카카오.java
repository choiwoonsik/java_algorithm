package 다이나믹프로그래밍;

public class 파괴되지않는건물_정확성_2022카카오 {

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
		static int total;
		static int width;
		static int height;
		static int[][] BOARD;

		private int solution(int[][] board, int[][] skill) {

			int Q = skill.length;
			int minus;
			int plus;
			width = board[0].length;
			height = board.length;
			BOARD = new int[board.length][board[0].length];
			for (int i = 0; i < board.length; i++) {
				BOARD[i] = board[i].clone();
			}

			for (int query = 0; query < Q; query++) {
				int[] nowQuery = skill[query];
				int type = nowQuery[0];
				int r1 = nowQuery[1];
				int c1 = nowQuery[2];
				int r2 = nowQuery[3];
				int c2 = nowQuery[4];
				int degree = nowQuery[5];

				minus = 0;
				plus = 0;
				if (type == ATK) {
					minus = attack(r1, c1, r2, c2, degree);
				} else if (type == HEAL) {
					plus = heal(r1, c1, r2, c2, degree);
				}

				total -= minus;
				total += plus;
			}

			if (total < 0) return width * height + total;
			else return width * height;
		}

		private int attack(int r1, int c1, int r2, int c2, int degree) {
			int count = 0;

			for (int row = r1; row <= r2; row++) {
				for (int col = c1; col <= c2; col++) {
					int before = BOARD[row][col];
					BOARD[row][col] -= degree;
					int after = BOARD[row][col];

					if (before > 0 && after <= 0) {
						count++;
					}
				}
			}

			return count;
		}

		private int heal(int r1, int c1, int r2, int c2, int degree) {
			int count = 0;

			for (int row = r1; row <= r2; row++) {
				for (int col = c1; col <= c2; col++) {
					int before = BOARD[row][col];
					BOARD[row][col] += degree;
					int after = BOARD[row][col];

					if (before <= 0 && after > 0) {
						count++;
					}
				}
			}

			return count;
		}
	}
}
