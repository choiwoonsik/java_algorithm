package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		TrieTree tree = new TrieTree();
		while (true) {
			String input = br.readLine();
			tree.insert(input);
		}
	}
	private static class Tree
	{
		Tree[] next = new Tree[26];
		boolean isEnd = false;
	}
	private static class TrieTree
	{
		Tree root = new Tree();

		private void insert(String str) {
			Tree current = root;

			System.out.println("input: "+str);
			for (int i = 0; i < str.length(); i++) {
				if (current.next[str.charAt(i) - 'a'] == null)
				{
					current.next[str.charAt(i) - 'a'] = new Tree();
				}
				current = current.next[str.charAt(i) - 'a'];
			}
			current.isEnd = true;
		}
	}
}
