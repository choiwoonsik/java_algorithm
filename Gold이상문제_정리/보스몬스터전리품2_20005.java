package Gold이상문제_정리;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;

import java.util.*;
import java.io.*;

public class 보스몬스터전리품2_20005 {
	static int M, N, P, prizeCnt, HP;
	static char[][] board;
	static boolean[][] vistied;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static Queue<Pos> queue = new LinkedList<>();
	static boolean[] checked = new boolean[26];
	static ArrayList<Character> fightPlayers = new ArrayList<>();
	static ArrayList<Character> players = new ArrayList<>();
	static HashMap<Character, Integer> playerDps = new HashMap<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input;
		input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		P = Integer.parseInt(input[2]);
		board = new char[N][M];
		vistied = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				board[i][j] = s.charAt(j);
				if (board[i][j] == 'B') {
					vistied[i][j] = true;
					queue.add(new Pos(i, j));
				}
			}
		}

		for (int i = 0; i < P; i++) {
			input = br.readLine().split(" ");
			char id = input[0].charAt(0);
			int dps = Integer.parseInt(input[1]);
			playerDps.put(id, dps);
		}
		HP = Integer.parseInt(br.readLine());

		playGame();
		for (int i = 0; i < 26; i++) {
			if (checked[i]) prizeCnt++;
		}
		System.out.println(prizeCnt);
	}

	private static void playGame() {
		while (HP > 0)
		{
			int size = queue.size();

			while (size-- > 0)
			{
				Pos now = queue.poll();

				for (int d = 0; d < 4; d++) {
					int ny = now.y + dy[d];
					int nx = now.x + dx[d];

					if (ny < 0 || nx < 0 || ny >= N || nx >= M) continue;
					if (board[ny][nx] == 'X') continue;
					if (vistied[ny][nx]) continue;
					vistied[ny][nx] = true;
					queue.add(new Pos(ny, nx));
					if (board[ny][nx] != '.' && !checked[board[ny][nx] - 'a']) {
						checked[board[ny][nx] - 'a'] = true;
						fightPlayers.add(board[ny][nx]);
					}
				}
			}

			for (char player : fightPlayers)
				HP -= playerDps.get(player);
		}
	}

	private static class Pos {
		int y, x;

		public Pos(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
