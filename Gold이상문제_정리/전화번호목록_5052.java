package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 전화번호목록_5052 {
	static int T, N;
	static boolean NO;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		StringBuilder str = new StringBuilder();

		while (T-- > 0) {
			TrieTree trieTree = new TrieTree();
			ArrayList<String> numbers = new ArrayList<>();
			N = Integer.parseInt(br.readLine());
			NO = false;

			for (int i = 0; i < N; i++) {
				String number = br.readLine();
				numbers.add(number);
			}
			numbers.sort(Comparator.comparingInt(String::length));
			for (String number : numbers) {
				if (NO) continue;
				trieTree.insert(number);
			}
			if (NO) str.append("NO").append("\n");
			else str.append("YES").append("\n");
		}
		System.out.print(str);
	}
	private static class Tree
	{
		Tree[] next = new Tree[10];
		boolean isEnd = false;
	}
	private static class TrieTree
	{
		Tree root = new Tree();

		private void insert(String number)
		{
			Tree current = root;
			for (int i = 0; i < number.length(); i++) {
				if (current.next[number.charAt(i) - '0'] == null) {
					current.next[number.charAt(i) - '0'] = new Tree();
				} else if (current.next[number.charAt(i) - '0'].isEnd) {
					NO = true;
					break;
				}
				current = current.next[number.charAt(i) - '0'];
			}
			current.isEnd = true;
		}
	}
}
