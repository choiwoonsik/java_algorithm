package Gold이상문제_정리;

import java.util.*;
import java.io.*;

public class 공정컨설턴트호석_22254 {
	static int N, X;
	static Integer[] times;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		X = Integer.parseInt(input[1]);

		times = new Integer[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			times[i] = Integer.parseInt(st.nextToken());

		System.out.println(binarySearch());
	}

	private static int binarySearch() {
		int low = 1;
		int high = N;
		int mid;
		while (low < high) {
			mid = (low + high) / 2;

			if (makeItem(mid) <= X)
				high = mid;
			else
				low = mid + 1;
		}
		return high;
	}

	private static int makeItem(int mid) {
		PriorityQueue<Integer> lines = new PriorityQueue<>();
		int idx = 0;
		int last = 0;
		while (idx < mid) lines.add(times[idx++]);

		for (int i = idx; i < N; i++) {
			last = lines.poll();
			last += times[i];
			if (last > X) return X + 1;
			else lines.add(last);
		}
		return last;
	}
}
