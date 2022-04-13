package 다이나믹프로그래밍;

import java.util.*;
import java.io.*;

/*
6
10 20 10 30 20 50

9
1 3 5 2 4 6 3 6 7

5
1 4 2 3 5
 */
public class 가장긴증가하는부분수열_11053 {
	static int N;
	static int[] nums;
	static ArrayList<Integer> all = new ArrayList<>();

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		nums = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		for (int num : nums) {
			if (all.isEmpty()) {
				all.add(num);
			} else if (num > all.get(all.size() - 1)) {
				all.add(num);
			} else {
				int idx = binarySearch(num);
				if (idx >= 0) {
					all.remove(idx);
					all.add(idx, num);
				}
			}
		}

		System.out.print(all.size());
	}

	private static int binarySearch(int num) {
		int left = 0;
		int right = all.size();

		while (left < right) {
			int mid = (left + right) / 2;

			if (all.get(mid) >= num) {
				right = mid;
			} else if (all.get(mid) < num) {
				left = mid + 1;
			}
		}

		return right;
	}
}
