package Gold이상문제_정리;

import java.util.*;
import java.io.*;

public class 소수의연속합_1644 {
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		ArrayList<Integer> list = new ArrayList<>();

		int div = 2, tmpN = N;
		boolean isSosu;
		if (N <= div) list.add(2);
		else {
			while (tmpN >= 2) {
				isSosu = true;
				for (div = 2; div <= Math.sqrt(tmpN); div++) {
					if (tmpN % div == 0) {
						isSosu = false;
						break;
					}
				}
				if (isSosu)
					list.add(tmpN);
				tmpN--;
			}
		}
		int i = 0;
		int[] sosu_arr = new int[list.size()];
		Collections.sort(list);
		for (int sosu : list) {
			sosu_arr[i++] = sosu;
		}

		int L = 0, R = 0;
		int sum = 0, cnt = 0;
		while (R < sosu_arr.length) {
			sum += sosu_arr[R];
			while (L <= R && sum > N) {
				sum -= sosu_arr[L];
				L++;
			}
			if (sum == N)
				cnt++;
			R++;
		}
		System.out.println(cnt);
	}
}
