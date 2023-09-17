package 힣_코테.카페_2022_05;

import java.util.*;

public class Sol2 {
	public static void main(String[] args) {
		Solution sol = new Solution();

	}

	private static class Solution {
		static int lastRow;
		static int lastCol;
		static boolean[][] box;
		static int H;
		static int W;
		static ArrayList<int[]> answer;

		public int[][] solution(int m, int n, int[][] rectangle) {

			H = n;
			W = m;
			box = new boolean[n + 1][m + 1];
			answer = new ArrayList<>();
			for (int i = 0; i < H; i++) {
				Arrays.fill(box[i], true);
			}
			Arrays.sort(rectangle, Comparator.comparingInt(r -> r[0]));

			for (int[] rec : rectangle) {
				int size = rec[0];
				int count = rec[1];

				for (int i = 0; i < count; i++) {
					fillBlock(size);
				}
			}

			int[][] result = new int[answer.size()][3];
			for (int i = 0; i < answer.size(); i++) {
				result[i] = answer.get(i);
			}

			return result;
		}

		private void fillBlock(int size) {
			loop:
			for (int h = lastRow; h < H; h++) {
				for (int w = lastCol; w < W; w++) {

					if (IsPossible(h, w, size)) {
						fillBox(h, w, size);
						if (W - (w + size) >= size) {
							lastRow = h;
							lastCol = w + size;
						} else {
							lastRow = h + 1;
							lastCol = 0;
						}

						break loop;
					}
				}
			}
		}

		private boolean IsPossible(int h, int w, int size) {
			if (w + size - 1 >= W) {
				return false;
			}

			if (h + size - 1 >= H) {
				return false;
			}

			for (int c = w; c < w + size; c++) {
				if (!box[h][c]) {
					return false;
				}
			}

			return true;
		}

		private void fillBox(int h, int w, int size) {
			for (int r = h; r < h + size; r++) {
				for (int c = w; c < w + size; c++) {
					box[r][c] = false;
				}
			}

			answer.add(new int[]{w, h, size});
		}
	}
}

