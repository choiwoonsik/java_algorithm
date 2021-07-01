package Gold이상문제_정리;

import java.util.*;
import java.io.*;

public class 문자열지옥에빠진호석_20166 {
	static int N, M, K, MaxLen;
	static StringBuilder answer = new StringBuilder();
	static int[] dy = {0, 0, -1, 1, 1, 1, -1, -1};
	static int[] dx = {1, -1, 0, 0, 1, -1, 1, -1};
	static char[][] board;
	static HashMap<String, Integer> godStrCntMap = new HashMap<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		board = new char[N][M];

		for (int i = 0; i < N; i++) {
			board[i] = br.readLine().toCharArray();
		}

		ArrayList<String> godStrs = new ArrayList<>();

		for (int i = 0; i < K; i++) {
			String godStr = br.readLine();
			MaxLen = Math.max(MaxLen, godStr.length());
			godStrCntMap.put(godStr, 0);
			godStrs.add(godStr);
		}

		findGodStr();

		for (String s : godStrs)
			answer.append(godStrCntMap.get(s)).append("\n");
		System.out.print(answer);
	}

	private static void findGodStr() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				DFS(new Dot(i, j), board[i][j]+"", 1);
			}
		}
	}

	private static void DFS(Dot dot, String myS, int move) {

		if (godStrCntMap.containsKey(myS))
			godStrCntMap.put(myS, godStrCntMap.get(myS) + 1);

		if (move == MaxLen)
			return;

		for (int d = 0; d < 8; d++) {
			int ny = dot.y + dy[d];
			int nx = dot.x + dx[d];

			ny %= N;
			if (ny < 0) ny = N - 1;

			nx %= M;
			if (nx < 0) nx = M - 1;

			DFS(new Dot(ny, nx), myS + board[ny][nx], move + 1);
		}
	}

	private static class Dot
	{
		int y, x;
		public Dot(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
