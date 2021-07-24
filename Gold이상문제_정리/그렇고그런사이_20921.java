package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 그렇고그런사이_20921 {
	static int N, K;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		K = Integer.parseInt(input[1]);

		// 1 2 3 4
		// 1 2 4 3 : 1
		// 1 4 2 3 : 2
		// 4 1 2 3 : 3
		// 4 1 3 2 : 4
		// 4 3 1 2 : 5
		// 4 3 2 1 : 6

		List<Integer> nums = new LinkedList<>();
		for (int i = 0; i < N; i++)
			nums.add(i + 1);

		int max = N - 1;
		while (K > 0)
		{
			if (K >= max) {
				K -= max;
				int last = nums.get(nums.size() - 1);
				nums.add(nums.size() - max - 1, last);
				nums.remove(nums.size() - 1);
				max -= 1;
			} else {
				max = K;
				int last = nums.get(nums.size() - 1);
				nums.add(nums.size() - max - 1, last);
				nums.remove(nums.size() - 1);
				break;
			}
		}
		StringBuilder answer = new StringBuilder();
		for (int n : nums) answer.append(n).append(" ");
		System.out.print(answer);
	}
}
