package Gold이상문제_정리;

import java.util.*;
import java.io.*;

public class 옥상정원꾸미기_6198 {
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Integer> stack = new Stack<>();

		long answer = 0;
		N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			int t = Integer.parseInt(br.readLine());

			while (!stack.isEmpty() && stack.peek() <= t)
				stack.pop();

			stack.push(t);
			answer += stack.size() - 1;
		}

		System.out.println(answer);
	}
}
