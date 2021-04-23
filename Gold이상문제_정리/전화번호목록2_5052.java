package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 전화번호목록2_5052 {
	static int T, N;
	static StringBuilder str = new StringBuilder();
	static ArrayList<String> list = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());

		for (int i = 0; i < T; i++) {
			N = Integer.parseInt(br.readLine());
			list.clear();
			for (int j = 0; j < N; j++)
				list.add(br.readLine());
			list.sort(Comparator.comparingInt(String::length));
			solve();
		}
		System.out.print(str);
	}

	private static void solve() {
		Trie trie = new Trie();

		for (String s : list) {
			if (!trie.check(s)) {
				str.append("NO\n");
				return;
			}
		}
		str.append("YES\n");
	}

	private static class Tree
	{
		Tree[] number = new Tree[10];
		boolean isEnd;
	}
	private static class Trie
	{
		Tree tree = new Tree();

		private boolean check(String str) {
			Tree now = tree;

			for (int i = 0; i < str.length(); i++) {
				if (now.number[str.charAt(i) - '0'] == null) {
					now.number[str.charAt(i) - '0'] = new Tree();
				}
				if (now.isEnd) return false;
				now = now.number[str.charAt(i) - '0'];
			}
			now.isEnd = true;
			return true;
		}
	}
}
