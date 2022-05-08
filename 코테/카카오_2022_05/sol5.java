package 코테.카카오_2022_05;

import java.util.*;

public class sol5 {
	public static void main(String[] args) {
		Solution sol = new Solution();
		int[][] solution = sol.solution(new int[][]{
				{1, 2, 3},
				{4, 5, 6},
				{7, 8, 9}
		}, new String[]{"Rotate", "ShiftRow"});

		for (int[] ints : solution) {
			for (int anInt : ints) {
				System.out.print(anInt + " ");
			}
			System.out.println();
		}
	}

	private static class Solution {
		static HashMap<Integer, Deque<Integer>> dequeMap = new HashMap<>();
		static int W;
		static int N;
		static int[][] answer;

		public int[][] solution(int[][] rc, String[] operations) {
			N = rc.length;
			W = rc[0].length;
			answer = new int[N][W];

			for (int i = 0; i < N; i++) {
				Deque<Integer> list = new LinkedList<>();
				for (int j = 0; j < rc[i].length; j++) {
					list.add(rc[i][j]);
				}
				dequeMap.put(i, list);
			}

			for (String operation : operations) {
				if (operation.equals("Rotate")) {
					rotate();
				} else if (operation.equals("ShiftRow")) {
					shiftRow();
				}
			}

			for (int i = 0; i < N; i++) {
				int j = 0;
				for (Integer number : dequeMap.get(i)) {
					answer[i][j++] = number;
				}
			}

			return answer;
		}

		private void shiftRow() {
			Deque<Integer> last = dequeMap.get(N - 1);
			for (int i = N-1; i >= 1; i--) {
				dequeMap.put(i, dequeMap.get(i - 1));
			}
			dequeMap.put(0, last);
		}

		private void rotate() {
			for (int i = 0; i < N; i++) {
				Integer first = dequeMap.get(i + 1).removeFirst();
				dequeMap.get(i + 1).removeLast();
				dequeMap.get(i - 1).addFirst(first);
			}
			for (int i = N - 2; i >= 0; i--) {
				Integer last = dequeMap.get(i).removeLast();
				dequeMap.get(i + 1).addLast(last);

				Integer first = dequeMap.get(i + 1).removeFirst();
				int tmpFirst = dequeMap.get(i).getFirst();

				dequeMap.get(i).addFirst(first);
			}
		}
	}
}
