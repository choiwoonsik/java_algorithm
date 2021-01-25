package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 꼬인전깃줄_1365 {
	static int N;
	static int[] A;
	static int[] B;
	static ArrayList<Integer> list = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		A = new int[N+1];
		B = new int[N+1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			int val = Integer.parseInt(st.nextToken());
			A[i] = val;
			B[val] = i;
		}

		// 왼쪽
		int left_size = 0;
		list.clear();
		list.add(0, -1);
		for (int i = 1; i <= N; i++) {
			int pos = Collections.binarySearch(list, A[i]);
			// 음수인 경우
			if (pos < 0)
			{
				pos = Math.abs(pos + 1);
				if (pos < list.size())
					list.remove(pos);
				list.add(pos, A[i]);
			}
			// 양수인 경우 - 없음 (중복 없으므로)
		}
		left_size = list.size() - 1;

		// 오른쪽
		int right_size = 0;
		list.clear();
		list.add(0, -1);
		for (int i = 1; i <= N; i++) {
			int pos = Collections.binarySearch(list, B[i]);
			if (pos < 0)  {
				pos = Math.abs(pos + 1);
				if (pos < list.size())
					list.remove(pos);
				list.add(pos, B[i]);
			}
			// 양수인 경우 - 없음 (중복 없으므로)
		}
		right_size = list.size() - 1;

		// N * log(N) // log(100000) = 17 // 십만 * 17 = 117만 *2 = 234만
		int ans = N - Math.max(left_size, right_size);
		System.out.println(ans);
	}
}
