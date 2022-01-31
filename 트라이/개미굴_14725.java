package 트라이;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class 개미굴_14725 {
	static int N;
	static Trie root;
	static int endCount;
	static int depth = 0;
	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		root = new Trie();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			int len = Integer.parseInt(st.nextToken());
			String[] words = new String[len];
			for (int j = 0; j < len; j++) {
				words[j] = st.nextToken();
			}
			makeTrie(words);
		}

		makeVisualization(-1, root);
		System.out.print(answer);
	}

	private static void makeVisualization(int depth, Trie cur) {
		cur.getChild().sort(Comparator.comparing(N -> N.name));

		if (depth != -1) {
			for (int i = 0; i < depth; i++) {
				answer.append("--");
			}
			answer.append(cur.name).append("\n");
		}

		for (Trie child : cur.getChild()) {
			makeVisualization(depth + 1, child);
		}
	}

	private static void makeTrie(String[] words) {
		Trie cur = root;
		for (String name : words) {
			int idx = -1;

			for (int i = 0; i < cur.getChild().size(); i++) {
				if (cur.getChild().get(i).name.equals(name)) {
					idx = i;
					break;
				}
			}

			if (idx == -1) {
				Trie child = new Trie();
				child.name = name;
				cur.getChild().add(child);
				cur = cur.getChild().get(cur.getChild().size() - 1);
			} else {
				cur = cur.getChild().get(idx);
			}
		}
	}

	private static class Trie {
		String name = "";
		ArrayList<Trie> child = new ArrayList<>();

		public boolean hasChild() {
			return child != null;
		}

		public ArrayList<Trie> getChild() {
			return child;
		}
	}
}
