package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 휴게소세우기_1477 {
	static int N, M, L;
	static int[] place_ps;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		place_ps = new int[N + 2];
		st = new StringTokenizer(br.readLine());
		place_ps[0] = 0;
		for (int i = 1; i <= N; i++)
			place_ps[i] = Integer.parseInt(st.nextToken());
		place_ps[N + 1] = L;

		Arrays.sort(place_ps);
		System.out.println(binarySearch());
	}

	private static int binarySearch() {
		int left = 0;
		int right = L;

		while (left <= right)
		{
			int mid = (left + right) / 2;

			if (check(mid))
				right = mid - 1;
			else left = mid + 1;
		}
		return left;
	}

	private static boolean check(int mid) {
		int all = 0;
		for (int i = 0; i <= N; i++) {
			int dist = place_ps[i+1] - place_ps[i];

			int plus;
			if (dist / mid > 0)
			{
				if (dist % mid == 0) plus = (dist / mid) - 1; // 마지막 부분 휴게소 x
				else plus = dist / mid;
				all += plus;
			}
		}
		return all <= M;
	}
}
