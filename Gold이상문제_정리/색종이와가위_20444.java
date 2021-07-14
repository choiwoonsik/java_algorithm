package Gold이상문제_정리;

import java.util.*;
import java.io.*;

public class 색종이와가위_20444 {
	static int N;
	static long K;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Long.parseLong(st.nextToken());

		binarySearch();
	}

	private static void binarySearch() {
		long lo = 0;
		long high = N;

		while (lo <= high)
		{
			long garo = (lo + high) / 2;
			long sero = (N - garo);
			long slice = (garo + 1) * (sero + 1);

			if (slice < K)
				lo = garo + 1;
			else if (slice > K)
				high = garo - 1;
			else {
				System.out.println("YES");
				return;
			}
		}
		System.out.println("NO");
	}
}
