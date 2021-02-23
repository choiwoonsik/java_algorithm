package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class Boggle_9202 {
	static int[] point = {0, 0, 0, 1, 1, 2, 3, 5, 11};
	static int DicN;
	static int BoardN;
	static char[][] Boggle;
	static TrieTree TREE;
	static boolean[] alpha = new boolean[26];
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static int[] DicLen;
	static StringBuilder str = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		TREE = new TrieTree();

		DicN = Integer.parseInt(br.readLine());
		DicLen = new int[DicN];
		// 사전 문자열 받아서 트라이에 넣기
		for (int i = 0; i < DicN; i++) {
			char[] dict = br.readLine().toCharArray();
			DicLen[i] = dict.length;
			Arrays.sort(dict);
			System.out.println(dict);
			TREE.insert(dict);
		}

		br.readLine();
		BoardN = Integer.parseInt(br.readLine());
		for (int i = 0; i < BoardN; i++) {
			// 보드판 채우기
			Boggle = new char[4][4];
			for (int j = 0; j < 4; j++) {
				String s = br.readLine();
				Boggle[j] = s.toCharArray();
				System.out.println(Boggle[j]);
			}
			// 채운 보드판으로 bfs하면서 문자열 담기
			int max_point = 0;
			for (int y = 0; y < 4; y++) {
				for (int x = 0; x < 4; x++) {
					// 등장했던 단어이면
					Queue<Dot> que = new LinkedList<>();
					if (alpha[Boggle[y][x] - 'A']) {
						que.add(new Dot(y, x));
						while (!que.isEmpty()) {
							Dot now = que.poll();

							for (int l = 0; l < DicN; l++) {
								if (que.size() == DicLen[l]) {
									char[] tmp = new char[que.size()];
									int idx = 0;
									for (Dot dot : que) {
										tmp[idx] = Boggle[dot.y][dot.x];
										idx++;
									}
									Arrays.sort(tmp);
									System.out.println(tmp);
									if (TREE.isOk(tmp)) {
										max_point = Math.max(max_point, point[tmp.length]);
									}
								}
							}

							for (int d = 0; d < 4; d++) {
								int ny = dy[d] + now.y;
								int nx = dx[d] + now.x;
								if (ny < 0 || ny >= 4 || nx < 0 || nx >= 4) continue;
								if (!alpha[Boggle[ny][nx] - 'A']) continue;

								que.add(new Dot(ny, nx));
							}
						}
					}
				}
			}
			// 보드 한판에 대한 maxpoint 계산 끝
			br.readLine();
			str.append(max_point).append(" ");
		}
		System.out.println(str);
	}
	private static class tree
	{
		tree[] next = new tree[26];
		boolean isEnd = false;
	}
	private static class TrieTree
	{
		tree root = new tree();

		private void insert(char[] s) {
			tree current = root;
			for (char c : s) {
				if (!alpha[c-'A']) alpha[c-'A'] = true;
				if (current.next[c - 'A'] == null) {
					current.next[c - 'A'] = new tree();
				}
				current = current.next[c - 'A'];
			}
			current.isEnd = true;
		}

		private boolean isOk(char[] s) {
			tree current = root;
			for (char c : s) {
				if (current.next[c - 'A'] == null) {
					return false;
				}
				current = current.next[c - 'A'];
			}
			return true;
		}
	}
	private static class Dot {
		int y, x;

		public Dot(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}

