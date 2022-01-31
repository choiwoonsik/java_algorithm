package SDS_알고리즘.Day10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 전깃줄2_2568 {
	static int[] indexOrderArr;
	static int[][] pole;

	static StringBuilder answer = new StringBuilder();
	static ArrayList<Integer> list = new ArrayList<>();
	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine().trim());
		indexOrderArr = new int[N];
		pole = new int[N][2];
		list.add(0);

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine().trim());
			int key = Integer.parseInt(st.nextToken());
			int val = Integer.parseInt(st.nextToken());
			pole[i][0] = key;
			pole[i][1] = val;
		}

		Arrays.sort(pole, Comparator.comparing(p -> p[0]));
		answer.append(binarySearch()).append("\n");
		tracePoll();

		System.out.print(answer);
	}

	private static int binarySearch() {
		for (int l = 0; l < N; l++) {
			if (list.get(list.size() - 1) < pole[l][1]) {
				list.add(pole[l][1]);
				indexOrderArr[l] = list.size() - 1;
			} else {
				int left = 1;
				int right = list.size() - 1;
				int mid;

				while (left < right) {
					mid = (left + right) / 2;

					if (pole[l][1] > list.get(mid)) {
						left = mid + 1;
					} else {
						right = mid;
					}
				}

				list.set(right, pole[l][1]);
				indexOrderArr[l] = right;
			}
		}

		return N - (list.size() - 1);
	}

	private static void tracePoll() {
		Stack<Integer> stack = new Stack<>();
		int last = list.size() - 1;

		for (int i = N - 1; i >= 0; i--) {
			int index = indexOrderArr[i];

			if (index == last) {
				last--;
			} else {
				stack.push(pole[i][0]);
			}
		}

		while (!stack.isEmpty()) {
			answer.append(stack.pop()).append("\n");
		}
	}
}