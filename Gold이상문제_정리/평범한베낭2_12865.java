package Gold이상문제_정리;

import java.util.*;
import java.io.*;

public class 평범한베낭2_12865 {
	//4 7
	//6 13
	//4 8
	//3 6
	//5 12
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());

		int[] dp = new int[W + 1];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());

			for (int weight = W; weight >= w ; weight--) {
				dp[weight] = Math.max(dp[weight], dp[weight - w] + p);
			}
		}
		System.out.println(dp[W]);
	}
}
