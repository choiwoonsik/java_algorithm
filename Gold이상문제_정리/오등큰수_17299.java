package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 오등큰수_17299 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Stack<pair> stack = new Stack<>();
		Stack<pair> out = new Stack<>();
		HashMap<Integer, Integer> map = new HashMap<>();
		ArrayList<Integer> list = new ArrayList<>();
		StringBuilder str = new StringBuilder();

		int N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int[] ret = new int[N];

		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(st.nextToken());
			if (!map.containsKey(n)) map.put(n, 1);
			else map.put(n, map.get(n) + 1);
			list.add(n);
		}

		int IDX = 0;
		for (Integer num : list) {
			if (!stack.isEmpty() && stack.peek().count < map.get(num)) {
				while (!stack.isEmpty() && stack.peek().count < map.get(num)) {
					stack.peek().init = num;
					out.push(stack.pop());
				}
			}
			stack.push(new pair(-1, map.get(num), IDX++));
		}
		for (pair p : stack)
			out.push(p);
		for (pair o : out)
			ret[o.pos] = o.init;
		for (int i : ret)
			str.append(i).append(" ");
		System.out.println(str);

	}
	private static class pair
	{
		int init, count, pos;

		public pair(int init, int count, int pos) {
			this.init = init;
			this.count = count;
			this.pos = pos;
		}
	}
}
