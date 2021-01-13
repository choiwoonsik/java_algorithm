package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 캐슬디펜스_17135 {
	static int[][] map;
	static int N, M, D;
	static int Kill = Integer.MIN_VALUE;
	static ArrayList<Dot> enemyList = new ArrayList<>();
	static ArrayList<int[]> arrowList = new ArrayList<>();
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		enemyList.sort(Comparator.comparingInt(s-> s.x));

		boolean[] visited = new boolean[M];
		arrow_position(0, visited, M, 3);
		for (int[] arrow_pos : arrowList)
		{
			init();
			Kill = Math.max(Kill, attack(arrow_pos));
		}
		System.out.println(Kill);
	}

	// enemyList init
	private static void init() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1)
					enemyList.add(new Dot(i, j, false));
			}
		}
		enemyList.sort(Comparator.comparingInt(o -> o.x));
	}

	// 순서없는, 중복없는 조합 코드
	private static void arrow_position(int depth, boolean[] visited, int n, int r) {
		// nCr , N개의 폭에서 3개의 위치 뽑기
		if (r == 0)
		{
			int[] arrow_pos = new int[3];
			int index = 0;
			for (int i = 0; i < n; i++) {
				if (visited[i])
					arrow_pos[index++] = i;
			}
			arrowList.add(arrow_pos);
			return;
		}
		for (int i = depth; i < n; i++) {
			if (!visited[i]) {
				visited[i] = true;
				arrow_position(i + 1, visited, n, r - 1);
				visited[i] = false;
			}
		}
	}

	private static int attack(int[] arrow_pos) {
		int die = 0;
		while (!enemyList.isEmpty())
		{
			ArrayList<Dot> deadList = new ArrayList<>();
			for (int a = 0; a < 3; a++)
			{
				int MIN = 100;
				int dist;
				Dot temp = null;
				for (Dot enemy : enemyList) {
					dist = Math.abs(enemy.x - arrow_pos[a]) + Math.abs(enemy.y - N);
					if (dist <= D && dist < MIN) {
						temp = enemy;
						MIN = dist;
					}
				}
				if (temp != null && !deadList.contains(temp))
					deadList.add(temp);
			}

			for (Dot dead : deadList) {
				enemyList.remove(dead);
				die++;
			}
			deadList.clear();
			// 좀비 내리기
			for (Dot enemy : enemyList) {
				if (enemy.y < N) {
					enemy.y++;
					if (enemy.y == N) {
						deadList.add(enemy);
					}
				}
			}
			// 죽은 좀비 없애기
			for (Dot dead : deadList)
				enemyList.remove(dead);
		}
		return die;
	}

	private static class Dot {
		int y;
		int x;
		boolean isDie;

		public Dot(int y, int x, boolean isDie) {
			this.y = y;
			this.x = x;
			this.isDie = isDie;
		}
	}
}
