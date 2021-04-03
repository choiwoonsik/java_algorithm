package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 저울_2437 {
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int[] nums = new int[N];
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(nums);

		int num = 1;
		for (int i = 0; i < nums.length; i++) {
			if (num < nums[i])
				break;
			num += nums[i];
		}
		System.out.println(num);
	}
}
