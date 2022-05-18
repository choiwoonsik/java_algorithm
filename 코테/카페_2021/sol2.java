package 코테.카페_2021;

public class sol2 {

	public static void main(String[] args) {
		boolean isSame;
		int[] answer;
		Solution sol;
		int[] solution;

		sol = new Solution();
		solution = sol.solution(4, 3, new int[][]{
				{1, 1, 2, 4, 3},
				{3, 2, 1, 2, 3},
				{4, 1, 1, 4, 3},
				{2, 2, 1, 3, 3}
		});
		isSame = true;
		answer = new int[]{23, 3, 21, 9};
		for (int i = 0; i < solution.length; i++) {
			if (answer[i] != solution[i]) {
				isSame = false;
				break;
			}
		}
		System.out.println(isSame);

		sol = new Solution();
		solution = sol.solution(2, 4, new int[][]{
				{3, 1, 2, 2, 4},
				{3, 1, 2, 2, 4},
				{4, 2, 1, 2, 3},
				{1, 1, 1, 2, 3}
		});
		isSame = true;
		answer = new int[]{12, 10, 5, 20};
		for (int i = 0; i < solution.length; i++) {
			if (answer[i] != solution[i]) {
				isSame = false;
				break;
			}
		}
		System.out.println(isSame);

		sol = new Solution();
		solution = sol.solution(2, 2, new int[][]{
				{3, 1, 1, 1, 2},
				{1, 1, 2, 2, 2},
				{4, 2, 1, 2, 2},
				{2, 1, 1, 2, 1}
		});
		isSame = true;
		answer = new int[]{2, 4, 3, 2};
		for (int i = 0; i < solution.length; i++) {
			if (answer[i] != solution[i]) {
				isSame = false;
				break;
			}
		}
		System.out.println(isSame);
	}

	private static class Solution {
		static int[][] map;
		static int[] answer;

		// d : 1 아래 , 2 위, 3 오른쪽, 4 왼쪽
		private int[] solution(int rows, int columns, int[][] swipes) {
			map = new int[rows][columns];
			answer = new int[swipes.length];

			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < columns; j++) {
					map[i][j] = (i * columns) + j + 1;
				}
			}

			int i = 0;
			for (int[] swipe : swipes) {
				int d = swipe[0];
				int r1 = swipe[1] - 1;
				int c1 = swipe[2] - 1;
				int r2 = swipe[3] - 1;
				int c2 = swipe[4] - 1;

				answer[i++] = swipeFunc(r1, c1, r2, c2, d);
			}

			return answer;
		}

		private int swipeFunc(int r1, int c1, int r2, int c2, int d) {
			int sum = 0;

			if (d == 1) {
				// 아래로 이동
				for (int col = c1; col <= c2; col++) {
					int bottom = map[r2][col];
					sum += bottom;
					for (int row = r2; row > r1; row--) {
						map[row][col] = map[row - 1][col];
					}
					map[r1][col] = bottom;
				}
			}
			if (d == 2) {
				// 위로 이동
				for (int col = c1; col <= c2; col++) {
					int top = map[r1][col];
					sum += top;
					for (int row = r1; row < r2; row++) {
						map[row][col] = map[row + 1][col];
					}
					map[r2][col] = top;
				}
			}
			if (d == 3) {
				// 오른쪽으로 이동
				for (int row = r1; row <= r2; row++) {
					int right = map[row][c2];
					sum += right;
					for (int col = c2; col > c1; col--) {
						map[row][col] = map[row][col - 1];
					}
					map[row][c1] = right;
				}
			}
			if (d == 4) {
				// 왼쪽으로 이동
				for (int row = r1; row <= r2; row++) {
					int left = map[row][c1];
					sum += left;
					for (int col = c1; col < c2; col++) {
						map[row][col] = map[row][col + 1];
					}
					map[row][c2] = left;
				}
			}

			return sum;
		}
	}
}
