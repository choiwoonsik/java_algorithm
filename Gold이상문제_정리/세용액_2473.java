package Gold이상문제_정리;

import java.util.*;
import java.io.*;

public class 세용액_2473 {
	static long[] Arr;
	static long[] save;
	static long Min = Long.MAX_VALUE;
	static long p1, p2, p3;
	static boolean done;
	public static void main(String[] args) throws IOException {
		//-97 -20 -6 -2 6 23 98
		/*
		-97을 뽑고 나머지에 대해서 -97에 가장 가까운 값을 이분탐색해서 찾는다
		 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());

		Arr = new long[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			Arr[i] = Long.parseLong(st.nextToken());

		//-97 -6 -2 6 98

		Arrays.sort(Arr);

		for (int i = 0; i < N - 1; i++) {
			save = Arrays.copyOfRange(Arr, i+1, Arr.length);
			find(0, save.length - 1, Arr[i]);
			if (done)
				break;
		}

		System.out.println(p1 + " " + p2 + " " + p3);
	}
	private static void find(int start, int end, long key)
	{
		while (start < end)
		{
			long calc = save[start] + save[end] + key;
			// 0보다 작은 경우 더 증가
			if (calc < 0)
			{
				if (Math.abs(calc) < Min) {
					Min = Math.abs(calc);
					p1 = key;
					p2 = save[start];
					p3 = save[end];
				}
				start++;
			}
			else if (calc > 0)
			{
				if (Math.abs(calc) < Min) {
					Min = Math.abs(calc);
					p1 = key;
					p2 = save[start];
					p3 = save[end];
				}
				end--;
			}
			else {
				done = true;
				Min = 0;
				p1 = key;
				p2 = save[start];
				p3 = save[end];
				return;
			}
		}
	}
}
