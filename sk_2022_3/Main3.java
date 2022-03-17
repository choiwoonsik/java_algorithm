package sk_2022_3;

public class Main3 {
	public static void main(String[] args) {
		Solution sol = new Solution();
		int answer = sol.solution(2, 2, new int[][]{{1, 1}, {2, 2}});
		System.out.println(answer);

		answer = sol.solution(51, 37, new int[][]{{17, 19}});
		System.out.println(answer);
	}

	private static int[][] dp;
	private static int D = 10000019;

	static class Solution {
		public int solution(int width, int height, int[][] diagonals) {
			dp = new int[height + 1][width + 1];

			for (int y = 0; y <= height; y++) {
				dp[y][0] = 1;
			}

			for (int x = 0; x <= width; x++) {
				dp[0][x] = 1;
			}

			for (int y = 1; y <= height; y++) {
				for (int x = 1; x <= width; x++) {
					dp[y][x] = (dp[y - 1][x] + dp[y][x - 1]) % D;
				}
			}

			int total = 0;
			for (int[] diagonal : diagonals) {
				int y = diagonal[1];
				int x = diagonal[0];

				Dot leftDot = new Dot(y, x - 1);
				Dot downDot = new Dot(y - 1, x);

				int beforeLeft = dp[leftDot.y][leftDot.x];
				int afterLeft = dp[height - downDot.y][width - downDot.x];
				int leftCount = (int) (((long) beforeLeft * afterLeft) % D);

				int beforeDown = dp[downDot.y][downDot.x];
				int afterDown = dp[height - leftDot.y][width - leftDot.x];
				int downCount = (int) (((long) beforeDown * afterDown) % D);

				total += (leftCount + downCount) % D;
				total %= D;
			}
			return total;
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
