package 힣_코테.줌_2022_04;

public class sol3 {

	public static void main(String[] args) {
		Solution sol = new Solution();
		int solution = sol.solution(new int[]{2, 2, 2, 3});
		System.out.println(solution);
	}

	private static class Solution {
		static int S = 1;
		static int[] tree;
		static int[] H;
		static int N;

		public int solution(int[] histogram) {

			H = new int[histogram.length + 1];
			for (int i = 0; i < histogram.length; i++) {
				H[i + 1] = histogram[i];
			}
			N = H.length;

			while (S < N) {
				S *= 2;
			}
			tree = new int[S * 2];

			// 트리에 구간별 가장 작은 높이의 인덱스 저장
			init(1, 1, S);

			// 구간 별 최대 크기 구함
			int answer = calcMaxArea(1, 1, N);

			System.out.println(answer);

			return answer;
		}

		private int calcMaxArea(int node, int qL, int qR) {
			if (node > S) return -1;
			if (qL >= qR) return -1;

			int index = query(1, 1, N, qL, qR);
			int maxSize = (qR - qL - 1) * H[index];

			if (index - 1 > qL)
				maxSize = Math.max(maxSize, calcMaxArea(node * 2, qL, index - 1));
			if (index + 1 < qR)
				maxSize = Math.max(maxSize, calcMaxArea(node * 2 + 1, index + 1, qR));

			return maxSize;
		}

		private static int query(int node, int start, int end, int queryLeft, int queryRight) {

			if (queryLeft > end || queryRight < start)
				return -1;

			if (queryLeft <= start && end <= queryRight) {
				return tree[node];
			}

			int mid = (start + end) / 2;
			int leftMinHeightIdx = query(node * 2, start, mid, queryLeft, queryRight);
			int rightMinHeightIdx = query(node * 2 + 1, mid + 1, end, queryLeft, queryRight);

			if (leftMinHeightIdx == -1) {
				return rightMinHeightIdx;
			} else if (rightMinHeightIdx == -1) {
				return leftMinHeightIdx;
			} else {
				return H[leftMinHeightIdx] < H[rightMinHeightIdx] ? leftMinHeightIdx : rightMinHeightIdx;
			}
		}

		private int init(int node, int start, int end) {
			if (start >= N) {
				return -1;
			}

			if (start == end) {
				tree[node] = start;
			} else {
				int mid = (start + end) / 2;
				int leftMinIdx = init(node * 2, start, mid);
				int rightMinIdx = init(node * 2 + 1, mid + 1, end);

				if (leftMinIdx == -1) {
					tree[node] = rightMinIdx;
				} else if (rightMinIdx == -1) {
					tree[node] = leftMinIdx;
				} else {
					tree[node] = H[leftMinIdx] < H[rightMinIdx] ? leftMinIdx : rightMinIdx;
				}
			}
			return tree[node];
		}
	}
}
