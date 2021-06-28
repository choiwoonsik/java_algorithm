package Gold이상문제_정리;

import java.util.*;
import java.io.*;

public class 물병_1052 {
	static int N, K, buyCount;
	static Deque<Integer> dq = new LinkedList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());


		int n;
		while (N > 0)
		{
			n = 0;
			int bottle;
			while (N >= (int) Math.pow(2, n))
				n++;
			n--;
			bottle = (int) Math.pow(2, n);
			N -= bottle;
			dq.addLast(bottle);
		}

		while (dq.size() > K)
		{
			int one = dq.pollLast();
			int two = dq.pollLast();

			int diff = two - one;
			buyCount += diff;
			dq.addLast(two*2);
		}
		System.out.println(buyCount);
	}
}
