package Gold이상문제_정리;

/*
5
-2 4 -99 -1 98
 */

import java.io.*;
import java.util.*;

public class 두용액2_2470 {
	static int N;
	static int one, two;
	static int[] fluid;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		fluid = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int acid = Integer.parseInt(st.nextToken());
			fluid[i] = acid;
		}
		Arrays.sort(fluid);
		find();
		System.out.println(one +" "+ two);
	}

	private static void find() {
		long minSum = Long.MAX_VALUE;
		for (int start_fluid = 1; start_fluid < N; start_fluid++) {
			int nowAcid = fluid[start_fluid-1];
			int pos = Arrays.binarySearch(fluid, start_fluid, N, -nowAcid);
			pos = Math.abs(pos + 1);

			long sum;
			long first_sum;
			long second_sum;

			if (pos == start_fluid) {
				first_sum = Math.abs(nowAcid + fluid[pos]);
				if (first_sum < minSum) {
					one = nowAcid;
					two = fluid[pos];
					minSum = first_sum;
				}
			}
			else if (pos >= N) {
				second_sum = Math.abs(nowAcid + fluid[pos - 1]);
				if (second_sum < minSum) {
					one = nowAcid;
					two = fluid[pos - 1];
					minSum = second_sum;
				}
			}
			else {
				first_sum = Math.abs(nowAcid + fluid[pos]);
				second_sum = Math.abs(nowAcid + fluid[pos - 1]);
				sum = Math.min(second_sum, first_sum);
				sum = Math.abs(sum);
				if (sum < minSum) {
					minSum = sum;
					one = nowAcid;
					if (second_sum == sum)
						two = fluid[pos - 1];
					else
						two = fluid[pos];
				}
			}
		}
	}
}
