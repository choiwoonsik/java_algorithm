package Gold이상문제_정리;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	static int N;
	static char map[][];
	static Point[] BP, EP;
	static int[] dx = { -1, 1, 0, 0 }, dy = { 0, 0, -1, 1 };

	static class State {
		int x, y, dir, dist;

		public State() {
			super();
		}

		State(int x, int y, int dir, int dist) {
			this.x = x;
			this.y = y;
			this.dir = dir; // 0: 가로, 1 : 세로
			this.dist = dist;
		}

	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		// 처음위치와 최종위치의 좌표
		BP = new Point[3];
		EP = new Point[3];

		int bi = 0, ei = 0;
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
			// 처음위치와 최종위치를 저장
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 'B')
					BP[bi++] = new Point(i, j);
				if (map[i][j] == 'E')
					EP[ei++] = new Point(i, j);
			}
		}

		System.out.println(bfs());
	}

	private static int bfs() {

		// 통나무가 가로로 있을 경우와 세로로 있을 경우 이동 상태
		boolean[][][] visited = new boolean[2][N][N];
		Queue<State> q = new LinkedList<>();

		// 통나무 상태 확인
		int dir = 0;
		// 통나무가 가로로 있다면
		if (BP[0].y + 1 == BP[1].y) dir = 0;
		else dir = 1;

		// B의 중심 기준으로 이동
		q.add(new State(BP[1].x, BP[1].y, dir, 0));
		visited[dir][BP[1].x][BP[1].y] = true;

		while (!q.isEmpty()) {

			State now = q.poll();
			// B의 중심이 E의 중심에 도달하면
			if (now.x == EP[1].x & now.y == EP[1].y) {
				// 통나무가 가로 방향으로 있고 도작 지점도 가로
				if (now.dir == 0 && map[now.x][now.y - 1] == 'E' && map[now.x][now.y + 1] == 'E') {
					return now.dist;
				}
				// 통나무가 세로방향으로 있고 도착 지점도 세로
				else if (now.dir == 1 && map[now.x - 1][now.y] == 'E' && map[now.x + 1][now.y] == 'E'){
					return now.dist;
				}
			}

			System.out.println("DIR : "+now.dist);
			// 4방으로 이동
			for (int d = 0; d < 4; d++) {
				int xx = now.x + dx[d];
				int yy = now.y + dy[d];
				System.out.println("y, x> "+xx +", "+ yy);

				// 통나무가 가로일 경우
				if(now.dir == 0) {
					// 이동할 수 있는 확인
					if(!checkHoriz(xx, yy, d)) continue;
				}
				// 통나무가 세로일 경우
				else {
					// 이동할 수 있는 확인
					if(!checkVerti(xx, yy, d)) continue;
				}

				// 이미 방문한 곳이라면 pass
				if (visited[now.dir][xx][yy]) continue;

				visited[now.dir][xx][yy] = true;
				q.add(new State(xx, yy, now.dir, now.dist + 1));
			}

			// 회전이 가능한지 체크
			if (canRotation(now.x, now.y)) {
				// 통나무가 가로인 경우
				if (now.dir == 0 && !visited[1][now.x][now.y]) {
					visited[1][now.x][now.y] = true;
					q.add(new State(now.x, now.y, 1, now.dist + 1));
				}
				// 통나무가 세로인 경우
				else if (now.dir == 1 && !visited[0][now.x][now.y]) {
					visited[0][now.x][now.y] = true;
					q.add(new State(now.x, now.y, 0, now.dist + 1));
				}
			}

		}

		return 0;
	}

	private static boolean checkVerti(int xx, int yy, int d) {

		// 상/하로 이동
		if (d < 2) {
			// 통나무 끝이
			// 범위를 넘어가면 pass
			if (xx - 1 < 0 || xx + 1 >= N) return false;
			// 나무가 있을 경우
			if (map[xx][yy] == '1' || map[xx - 1][yy] == '1' || map[xx + 1][yy] == '1') return false;
		}
		// 좌/우로 이동
		else {
			// 통나무 끝이
			// 범위를 넘어가면 pass
			if (yy < 0 || yy >= N) return false;
			// 나무가 있을 경우
			if (map[xx][yy] == '1' || map[xx - 1][yy] == '1' || map[xx + 1][yy] == '1') return false;
		}

		return true;
	}

	private static boolean checkHoriz(int xx, int yy, int d) {

		// 상/하로 이동
		if (d < 2) {
			// 통나무 끝이
			// 범위를 넘어가면 pass
			if (xx < 0 || xx >= N) return false;
			// 나무가 있을 경우
			if (map[xx][yy] == '1' || map[xx][yy-1] == '1' || map[xx][yy+1] == '1') return false;
		}
		// 좌/우로 이동
		else {
			// 통나무 끝이
			// 범위를 넘어가면 pass
			if (yy - 1 < 0 || yy + 1 >= N) return false;
			// 나무가 있을 경우
			if (map[xx][yy] == '1' || map[xx][yy - 1] == '1' || map[xx][yy + 1] == '1') return false;
		}

		return true;
	}

	private static boolean canRotation(int x, int y) {

		for (int i = x - 1; i <= x + 1; i++) {
			for (int j = y - 1; j <= y + 1; j++) {
				// 범위를 나갈경우
				if (i < 0 || j < 0 || i >= N || j >= N) return false;
				// 나무가 있을 경우
				if (map[i][j] == '1') return false;
			}
		}

		return true;
	}

}