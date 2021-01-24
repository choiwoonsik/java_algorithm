package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 두용액_2470 {
	static int N;
	static int[] Arr;
	static int Min = 2000000001;
	static int pair1, pair2;
	static boolean done;
	public static void main(String[] args) throws IOException {
		// -99 -2 4 -1 2 98 100
		// 1 100 2 200 3 -3
		//999 1 -1
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		Arr = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			Arr[i] = Integer.parseInt(st.nextToken());

		Arrays.sort(Arr);
		for (int i = 0; i < Arr.length - 1; i++) {
			find(i+1, Arr.length - 1, Arr[i]);
			if (done)
				break;
		}
		System.out.println(pair1 +" "+ pair2);
	}

	private static void find(int start, int end, int key)
	{
		while (start <= end)
		{
			int mid = (start + end) / 2;
			if (key + Arr[mid] > 0) {
				end = mid - 1;
				if (Math.abs(key + Arr[mid]) < Min) {
					Min = Math.abs(key + Arr[mid]);
					pair1 = key;
					pair2 = Arr[mid];
				}
			}
			else if (key + Arr[mid] < 0) {
				start = mid + 1;
				if (Math.abs(key + Arr[mid]) < Min) {
					Min = Math.abs(key + Arr[mid]);
					pair1 = key;
					pair2 = Arr[mid];
				}
			}
			else if (key + Arr[mid] == 0)
			{
				Min = Math.abs(key + Arr[mid]);
				pair1 = key;
				pair2 = Arr[mid];
				done = true;
				return;
			}
		}
	}
}
