package 트라이;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
5
ICPC
ACM
CONTEST
GCPC
PROGRAMM

3
ACMA
APCA
TOGI
NEST

PCMM
RXAI
ORCN
GPCG

ICPC
GCPC
ICPC
GCPC

1글자, 2글자로 이루어진 단어는 0점, 3글자, 4글자는 1점, 5글자는 2점, 6글자는 3점, 7글자는 5점, 8글자는 11점
 */
public class boggle_9202 {
	static int N;
	static int M;
	static char[][] board;
	static TrieTree trieTree;
	static String longestWord;
	static HashSet<String> wordSet = new HashSet<>();
	static int MaxLen;
	static int MaxPoint;
	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		trieTree = new TrieTree();
		N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			String word = br.readLine();
			trieTree.insert(word);
		}
		br.readLine();

		M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
//			System.out.println(i + " : START");
			board = new char[4][4];
			for (int row = 0; row < 4; row++) {
				String line = br.readLine();
				for (int col = 0; col < 4; col++) {
					board[row][col] = line.charAt(col);
				}
			}
			br.readLine();

			wordSet.clear();
			MaxLen = 0;
			MaxPoint = 0;
			longestWord = "";
			int totalPoint = boardPoint(board);
			answer.append(totalPoint).append(" ").append(longestWord).append(" ").append(wordSet.size()).append("\n");
		}

		System.out.print(answer);
	}

	private static int boardPoint(char[][] board) {
		int point = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				int word = board[i][j] - 'A';

				if (trieTree.root.tries[word] != null) {
//					System.out.println("GO : "+board[i][j]);
					Trie node = trieTree.root;
					point += bfs(new Dot(i, j), node);
				}
			}
		}

		return point;
	}

	private static int bfs(Dot dot, Trie root) {
		int[] dy = {0, 0, 1, -1, 1, 1, -1, -1};
		int[] dx = {1, -1, 0, 0, -1, 1, -1, 1};

		boolean[][] visited = new boolean[4][4];
		visited[dot.y][dot.x] = true;
		root = root.tries[board[dot.y][dot.x] - 'A'];

		Queue<Dot> queue = new LinkedList<>();
		queue.add(dot);

		int len;
		int point = 0;

		while (!queue.isEmpty()) {
			Dot cur = queue.poll();

			if (root == null) break;

			if (root.isEnd) {
				if (wordSet.contains(root.word)) continue;
//				System.out.println(root.word);

				int plus = 0;
				if (root.word.length() <= 2) plus = 0;
				else if (root.word.length() <= 4) plus = 1;
				else if (root.word.length() == 5) plus = 2;
				else if (root.word.length() == 6) plus = 3;
				else if (root.word.length() == 7) plus = 5;
				else if (root.word.length() == 8) plus = 11;

				if (!wordSet.contains(root.word)) {
					wordSet.add(root.word);
					point += plus;
				}

				len = root.depth;
				if (len > MaxLen) {
					MaxLen = len;
					MaxPoint = plus;
					longestWord = root.word;
				}
				break;
			}

			for (int d = 0; d < 8; d++) {
				int ny = cur.y + dy[d];
				int nx = cur.x + dx[d];

				if (ny < 0 || ny > 3 || nx < 0 || nx > 3) continue;
				if (visited[ny][nx]) continue;
				int nextWord = board[ny][nx] - 'A';
				if (root.tries[nextWord] == null) continue;

				visited[ny][nx] = true;
				queue.add(new Dot(ny, nx));
				root = root.tries[nextWord];
			}

		}

		return point;
	}

	private static class Dot {
		int y;
		int x;

		public Dot(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	private static class Trie {
		Trie[] tries = new Trie[26];
		boolean isEnd = false;
		int depth = 0;
		String word = "";
	}

	private static class TrieTree {
		Trie root = new Trie();

		public void insert(String s) {
			Trie curNode = root;
			curNode.depth = 0;
			curNode.word = "";

			for (int i = 0; i < s.length(); i++) {
				System.out.println(s.charAt(i));
				int word = s.charAt(i) - 'A';
				int tmp = curNode.depth;

				if (curNode.tries[word] == null) {
					curNode.tries[word] = new Trie();
				}
				curNode = curNode.tries[word];
				curNode.depth = tmp + 1;

				if (i == s.length() - 1) {
					curNode.isEnd = true;
					curNode.word = s;
				}
			}
		}
	}
}
