package Gold이상문제_정리;
/*
(0/(0))
 */

import java.io.*;
import java.util.*;

public class 괄호제거_2800 {
	static String line;
	static int[] pairs;
	static boolean[] deleted;
	static List<String> list = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		line = br.readLine();
		pairs = new int[line.length()];
		deleted = new boolean[line.length()];
		Arrays.fill(pairs, -1);

		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < line.length(); i++) {
			if (line.charAt(i) == '(') stack.add(i);
			else if (line.charAt(i) == ')')
			{
				int j = stack.pop();
				pairs[j] = i;
				pairs[i] = j;
			}
		}

		DFS(0);
		list.remove(list.size()-1);
		Collections.sort(list);
		list.forEach(System.out::println);
	}

	private static void DFS(int idx) {
		if (idx == line.length()) {
			StringBuilder s = new StringBuilder();
			for (int i = 0; i < line.length(); i++) {
				if (!deleted[i]) {
					s.append(line.charAt(i));
				}
			}
			String str = s.toString();
			if (!list.contains(str)) list.add(str);
			return;
		}

		if (line.charAt(idx) == '(') {
			deleted[idx] = true;
			DFS(idx + 1);
			deleted[idx] = false;
			DFS(idx + 1);
		} else if (line.charAt(idx) == ')') {
			deleted[idx] = deleted[pairs[idx]];
			DFS(idx + 1);
		}else {
			deleted[idx] = false;
			DFS(idx + 1);
		}
	}
}
