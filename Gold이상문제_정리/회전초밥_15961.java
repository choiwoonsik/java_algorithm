package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 회전초밥_15961 {
	static int[] dish;
	static int[] eatSushiCnt;
	static int K, N, D, Coupon, Eat, Kinds;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		Coupon = Integer.parseInt(st.nextToken());

		dish = new int[N+K-1];
		eatSushiCnt = new int[D+1];

		for (int i = 0; i < N+K-1; i++) {
			if (i < N) {
				st = new StringTokenizer(br.readLine());
				int sushi = Integer.parseInt(st.nextToken());
				dish[i] = sushi;
			} else
				dish[i] = dish[i % N];
		}
		//쿠폰 스시 먹음
		eatSushiCnt[Coupon] = 1;
		Eat = 1;
		Kinds = 1;
		find();
		System.out.println(Kinds);
	}

	private static void find() {
		for (int i = 0; i < K; i++) {
			if (eatSushiCnt[dish[i]] == 0)
				Kinds++;
			eatSushiCnt[dish[i]]++;
		}

		int maxKinds = 0;
		for (int e = 0; e < N - 1; e++) {
			maxKinds = Math.max(maxKinds, Kinds);

			//빼고
			if (eatSushiCnt[dish[e]] == 1)
				Kinds--;
			eatSushiCnt[dish[e]]--;
			//추가
			if(eatSushiCnt[dish[e+K]] == 0)
				Kinds++;
			eatSushiCnt[dish[e+K]]++;
		}
		Kinds = maxKinds;
	}
}
