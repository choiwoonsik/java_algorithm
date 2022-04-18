package 다이나믹프로그래밍;

import java.io.*;
import java.util.*;

/*
8
1 8
2 2
3 9
4 1
6 4
7 6
9 7
10 10
 */
public class 전깃줄_2565 {
	static int N;
	static int[] table;
	static int LAST;
	static ArrayList<Integer> list = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		table = new int[501];
		Arrays.fill(table, -1);

		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			int jdx = Integer.parseInt(st.nextToken());
			table[idx] = jdx;
			LAST = Math.max(LAST, idx);
		}

		for (int i = 0; i <= LAST; i++) {
			int cur = table[i];
			if (cur == -1) continue;

			if (list.isEmpty()) {
				list.add(cur);
			} else if (list.get(list.size() - 1) < cur) {
				list.add(cur);
			} else {
				int index = binarySearch(cur);
				list.remove(index);
				list.add(index, cur);
			}
		}

		System.out.println(list.size());
	}

	private static int binarySearch(int cur) {
		int left = 0;
		int right = list.size();

		while (left < right) {
			int mid = (left + right) / 2;

			if (list.get(mid) >= cur) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}

		return right;
	}
}
