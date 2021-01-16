package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 나무자르기_2805 {
	static int N, M;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int[] H = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			H[i] = Integer.parseInt(st.nextToken());

		Arrays.sort(H);
		int maxH = find(H,0, H[H.length - 1]);
		System.out.println(maxH);

	}
	private static int find(int[] H, int start, int end)
	{
		long sum;
		int middle = 0;
		while(start < end)
		{
			sum = 0;
			middle = (start + end) / 2;
			int i = H.length - 1;
			while (i >= 0 && H[i] > middle) {
				sum += H[i--] - middle;
				if (sum > M)
					break;
			}

			if (sum > M) {
				if (start == middle)
					break;
				start = middle;
			}
			else if (sum < M) {
				if (end == middle)
					break;
				end = middle;
			}
			else
				return middle;
		}
		return middle;
	}
}
