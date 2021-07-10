package Gold이상문제_정리;

import java.util.*;
import java.io.*;

public class 용액_2467 {

	static int N;
	static long[] A;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		A = new long[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			A[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(A);

		long ZERO = Long.MAX_VALUE;
		long zl = 0, zr = 0;
		for (int s = 1; s <= A.length - 1; s++) {

			long left = A[s - 1];
			int l = s;
			int r = A.length - 1;

			while (l <= r)
			{
				int mid = (l + r) / 2;

				if (left + A[mid] < 0) {
					l = mid + 1;
					if (Math.abs(left + A[mid]) < ZERO) {
						ZERO = Math.abs(left + A[mid]);
						zl = left;
						zr = A[mid];
					}
				} else if (left + A[mid] > 0)
				{
					r = mid - 1;
					if (Math.abs(left + A[mid]) < ZERO) {
						ZERO = Math.abs(left + A[mid]);
						zl = left;
						zr = A[mid];
					}
				} else {
					zl = left;
					zr = A[mid];
					ZERO = 0;
					break;
				}
			}
			if (ZERO == 0) break;
		}
		System.out.println(zl+" "+zr);

	}
}
