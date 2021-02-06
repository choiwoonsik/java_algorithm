package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 오큰수_17298 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Stack<pair> stack = new Stack<>();
		StringBuilder str = new StringBuilder();

		int N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		Stack<pair> out = new Stack<>();
		int[] ret = new int[N];

		for (int i = 0; i < N; i++){
			int num = Integer.parseInt(st.nextToken());
			if (stack.isEmpty())
				stack.push(new pair(num, -1, i));
			else {
				if (stack.peek().number >= num)
					stack.push(new pair(num, -1, i)); // 5 -> 4 -> 3 ..
				else if (stack.peek().number < num) // 5 -> 4 -> 3 -> 9
				{
					while (!stack.isEmpty() && stack.peek().number < num) {
						stack.peek().init = num;
						out.push(stack.pop());
					}
					stack.push(new pair(num, -1, i));
				}
			}
		}
		for (pair p : stack)
			out.push(p);
		for (pair pair : out)
			ret[pair.pos] = pair.init;
		for (Integer i : ret)
			str.append(i).append(" ");
		System.out.println(str);
	}
	private static class pair
	{
		int number;
		int init;
		int pos;

		public pair(int number, int init, int pos) {
			this.number = number;
			this.init = init;
			this.pos = pos;
		}
	}
}
