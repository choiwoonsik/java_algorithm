package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 생태학_4358 {
	static HashMap<String, Double> map = new HashMap<>();
	static ArrayList<String> set = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		TrieTree trieTree = new TrieTree();
		StringBuilder str = new StringBuilder();

		String name;
		int all = 0;
		while((name = br.readLine()) != null) {
			all++;
			trieTree.insert(name);
		}
		Collections.sort(set);

		for (String tree_name : set) {
			double cnt = map.get(tree_name);
			double val = cnt * 100 / all;
			str.append(tree_name).append(" ").append(String.format("%.4f", val)).append("\n");
		}

		System.out.print(str);
	}
	private static class Tree
	{
		Tree[] next = new Tree[126 - 32 + 1];
		boolean isEnd = false;
	}
	private static class TrieTree
	{
		Tree root = new Tree();

		private void insert(String s) {
			Tree current = root;
			for (int i = 0; i < s.length(); i++) {
				if (current.next[s.charAt(i) - ' '] == null) {
					current.next[s.charAt(i) - ' '] = new Tree();
				}
				current = current.next[s.charAt(i) - ' '];
			}
			if (!current.isEnd) {
				set.add(s);
				map.put(s, 1d);
				current.isEnd = true;
			} else
				map.put(s, map.get(s) + 1d);
		}
	}
}
