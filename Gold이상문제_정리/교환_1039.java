package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 교환_1039 {
	static String number;
	static int K, L;
	static int[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		number = st.nextToken();
		K = Integer.parseInt(st.nextToken());
		L = number.length();
		visited = new int[K+1][1000001];

		System.out.println(DFS(number, 0));
	}

	private static int DFS(String number, int k) {
		int num = Integer.parseInt(number);
		if (k == K) return num;
		if (visited[k][num] != 0) return visited[k][num];

		int max = -1;
		for (int i = 0; i < L - 1; i++) {
			for (int j = i + 1; j < L; j++) {
				if (i == 0 && number.charAt(j) == '0') continue;
				String swap_str = swap(number, i, j);
				int val = DFS(swap_str, k + 1);
				max = Math.max(max, val);
			}
		}

		visited[k][num] = max;
		return max;
	}

	private static String swap(String number, int i, int j) {
		char[] nums = number.toCharArray();

		char c = nums[i];
		nums[i] = nums[j];
		nums[j] = c;

		return String.valueOf(nums);
	}
}
