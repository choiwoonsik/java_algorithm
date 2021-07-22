package Gold이상문제_정리;

import java.util.*;
import java.io.*;

public class 보스몬스터전리품_20005 {
	static int M, N, P, prizeCnt, HP;
	static char[][] board;
	static boolean[][][] vistied;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static Queue<Pos>[] playerQ = new Queue[26];
	static boolean[] checked;
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
		vistied = new boolean[26][N][M];
		checked = new boolean[26];

		for (int i = 0; i < 26; i++) {
			playerQ[i] = new LinkedList<>();
		}

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				board[i][j] = s.charAt(j);
				if (board[i][j] <= 'z' && board[i][j] >= 'a') {
					int p = board[i][j] - 'a';
					playerQ[p].add(new Pos(i, j));
					players.add(board[i][j]);
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
	}

	private static void playGame() {
		while (true) {
			playerMove();
			if (!fightBoss())
				break;
		}
		System.out.println(prizeCnt);
	}

	private static boolean fightBoss() {
		if (HP > 0) {
			for (char player : fightPlayers) {
				HP -= playerDps.get(player);
				if (!checked[player - 'a']) {
					checked[player - 'a'] = true;
					prizeCnt++;
				}
			}
		} else return false;
		return true;
	}

	private static void playerMove() {
		for (char player : players) {
			int p = player - 'a';
			Queue<Pos> tmpQ = new LinkedList<>(playerQ[p]);

			playerQ[p].clear();
			int t = tmpQ.size();
			loop:
			while (t-- > 0) {
				Pos now = tmpQ.poll();

				for (int d = 0; d < 4; d++) {
					int ny = now.y + dy[d];
					int nx = now.x + dx[d];

					if (ny < 0 || ny >= N || nx < 0 || nx >= M) continue;
					if (board[ny][nx] == 'X') continue;
					if (vistied[p][ny][nx]) continue;
					if (board[ny][nx] == 'B') {
						fightPlayers.add(player);
						playerQ[p].clear();
						break loop;
					}
					vistied[p][ny][nx] = true;
					playerQ[p].add(new Pos(ny, nx));
				}
			}
		}
	}

	private static class Pos
	{
		int y, x;

		public Pos(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}