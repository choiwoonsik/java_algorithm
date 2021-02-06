package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 탑_2493 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Stack<Top> stack = new Stack();
		StringBuilder str = new StringBuilder();

		int N = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			int topH = Integer.parseInt(st.nextToken());
			// 자신보다 더 큰 탑이 이미 있는 경우
			if (!stack.isEmpty() && stack.peek().h > topH)
			{
				str.append(stack.peek().p).append(" ");
				stack.push(new Top(topH, i));
			}
			// 자신보다 더 작은 탑이 위에 있는 경우
			else {
				while (!stack.isEmpty() && stack.peek().h <= topH)
					stack.pop();
				if (!stack.isEmpty())
					str.append(stack.peek().p).append(" ");
				else
					str.append(0).append(" ");
				stack.push(new Top(topH, i));
			}
		}
		System.out.println(str);
	}
	private static class Top
	{
		int h, p;
		Top (int h, int p) {
			this.h = h;
			this.p = p;
		}
	}
}
