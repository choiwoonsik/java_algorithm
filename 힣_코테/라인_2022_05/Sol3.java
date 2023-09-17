package 힣_코테.라인_2022_05;

import java.util.PriorityQueue;

public class Sol3 {
	public static void main(String[] args) {
		Solution sol = new Solution();

	}

	private static class Solution {
		private PriorityQueue<Flower> pq = new PriorityQueue<>((f1, f2) -> {
			if (f1.end != f2.end) return Integer.compare(f1.end, f2.end);
			else return Integer.compare(f1.start, f2.start);
		});
		private boolean[] flowerDays = new boolean[366];
		private int lastDay = 0;
		private int totalDay = 0;

		public int solution(int[][] flowers) {
			for (int[] flower : flowers) {
				pq.add(new Flower(flower[0], flower[1]));
			}

			while (!pq.isEmpty()) {
				Flower nowFlower = pq.poll();

				for (int i = nowFlower.start; i < nowFlower.end; i++) {
					flowerDays[i] = true;
				}

				lastDay = Math.max(lastDay, nowFlower.end);
			}

			for (int s = 0; s < lastDay; s++) {
				if (flowerDays[s]) totalDay++;
			}

			return totalDay;
		}
	}

	private static class Flower {
		int start;
		int end;

		public Flower(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
}