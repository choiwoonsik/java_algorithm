package 다이나믹프로그래밍;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class 외벽점검_카카오2020 {

	public static void main(String[] args) {
		Solution sol = new Solution();
		int solution4 = sol.solution(200, new int[]{0, 100}, new int[]{1, 1});
		System.out.println(solution4 == 2);
	}

	private static class Solution {
		static int[] extend;
		static HashSet<Integer> weakSet = new HashSet<>();
		static int[] D;
		static int answer = -1;
		static int totalWeak;


		public int solution(int n, int[] weak, int[] dist) {
			totalWeak = weak.length;
			extend = new int[weak.length * 2];
			D = dist.clone();
			Arrays.sort(D);

			for (int i = 0; i < totalWeak; i++) {
				extend[i] = weak[i];
				weakSet.add(weak[i]);
			}
			for (int i = 0; i < totalWeak - 1; i++) {
				extend[i + totalWeak] = weak[i] + n;
				weakSet.add(weak[i] + n);
			}

			for (int size = 1; size <= D.length; size++) {
				int[] selected = new int[size];
				boolean[] visited = new boolean[D.length];
				permutation(0, size, selected, visited);
				if (answer != -1) break;
			}

			System.out.println("\n");
			System.out.println(totalWeak);
			System.out.println(answer);
			return answer;
		}

		private void permutation(int start, int max, int[] select, boolean[] visited) {
			if (answer != -1) return;

			if (start == max) {
				ArrayList<Integer> distList = new ArrayList<>();
				for (int dist : select) {
					distList.add(dist);
				}

				int coverCount = calc(distList);
				if (coverCount == totalWeak) {
					answer = distList.size();
				}
				return;
			}

			for (int i = 0; i < D.length; i++) {
				if (!visited[i]) {
					visited[i] = true;
					select[start] = D[i];
					permutation(start + 1, max, select, visited);
					visited[i] = false;
				}
			}
		}

		private int calc(ArrayList<Integer> distSet) {
			int maxCover = 0;

			for (int sIdx = 0; sIdx < totalWeak; sIdx++) {

				int tmpIdx = sIdx;
				int useCount = 0;

				for (Integer dist : distSet) {
					int end = extend[tmpIdx] + dist;

					while (tmpIdx < sIdx + totalWeak) {
						if (extend[tmpIdx] <= end) {
							useCount++;
							tmpIdx++;
						} else {
							break;
						}
					}
				}

				maxCover = Math.max(maxCover, useCount);
			}

			return maxCover;
		}
	}
}
