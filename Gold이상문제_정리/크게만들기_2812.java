package Gold이상문제_정리;

import java.util.*;
import java.io.*;

public class 크게만들기_2812 {
	static int N, K;
	static String NUM;
	static Deque<Integer> deque = new ArrayDeque<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		NUM = br.readLine();

		for (int idx = 0; idx < NUM.length(); idx++)
		{
			int n = NUM.charAt(idx) - '0';

			// 더 큰 수가 들어오는 경우
			while (!deque.isEmpty() && K > 0 && deque.peekLast() < n)
			{
				deque.pollLast();
				K--;
			}
			deque.addLast(n);
		}

		StringBuilder answer = new StringBuilder();
		while (!deque.isEmpty())
			answer.append(deque.poll());
		System.out.println(answer.substring(0, answer.length() - K));
	}
}
