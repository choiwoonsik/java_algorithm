package 브루투포스;

public class 양궁대회_카카오2022 {
	public static void main(String[] args) {
		Sol sol = new Sol();
		int[] solution = sol.solution(5, new int[]{2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0});
		int[] solution1 = sol.solution(9, new int[]{0, 0, 1, 2, 0, 1, 1, 1, 1, 1, 1});
		int[] solution2 = sol.solution(10, new int[]{0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 3});

		for (int i : solution) {
			System.out.print(i+" ");
		}
		System.out.println();
		for (int i : solution1) {
			System.out.print(i+" ");
		}
		System.out.println();
		for (int i : solution2) {
			System.out.print(i+" ");
		}
	}

	private static class Sol {
		static int[] apeachSelect;
		static int max = 0;
		static int[] maxTarget;

		public int[] solution(int n, int[] info) {
			apeachSelect = info.clone();
			max = -1;
			maxTarget = new int[11];

			int[] ryanSelect = new int[11];
			boolean[] visited = new boolean[11];
			dfs(0, n, ryanSelect, visited);

			if (max == -1) {
				return new int[]{-1};
			} else {
				return maxTarget;
			}
		}

		private void dfs(int cur, int remain, int[] ryanSelect, boolean[] visited) {
			if (remain == 0 || cur == 11) {
				ryanSelect[10] = remain;

				int diff = calc(ryanSelect);
				if (diff < 0) return;
				if (diff > max) {
					max = diff;
					for (int i = 0; i < 11; i++) {
						maxTarget[i] = ryanSelect[i];
					}
				} else if (diff == max) {
					for (int i = 10; i >= 0; i--) {
						if (maxTarget[i] > ryanSelect[i]) break;
						else if (ryanSelect[i] > maxTarget[i]) {
							for (int j = 0; j < 11; j++) {
								maxTarget[j] = ryanSelect[j];
							}
							break;
						}
					}
				}
				return;
			}

			for (int now = cur; now < 11; now++) {
				if (!visited[now]) {
					visited[now] = true;
					if (remain > apeachSelect[cur]) {
//						System.out.println(cur +" > 더 많음 : " + remain + " vs " + apeachSelect[cur]);
						ryanSelect[cur] = apeachSelect[cur] + 1;
						dfs(now + 1, remain - (apeachSelect[cur] + 1), ryanSelect, visited);
					}
					ryanSelect[cur] = 0;
					dfs(now + 1, remain, ryanSelect, visited);
					visited[now] = false;
				}
			}
		}

		private int calc(int[] ryanSelect) {
			int ryanPoint = 0;
			int apeachPoint = 0;
			for (int i = 0; i < 11; i++) {
				if (ryanSelect[i] == 0 && apeachSelect[i] == 0) continue;
				if (ryanSelect[i] > apeachSelect[i]) {
					ryanPoint += 10 - i;
				} else {
					apeachPoint += 10 - i;
				}
			}

//			System.out.println("apeachPoint : " + apeachPoint);
//			System.out.println("ryanPoint = " + ryanPoint);
			return ryanPoint - apeachPoint;
		}
	}
}
