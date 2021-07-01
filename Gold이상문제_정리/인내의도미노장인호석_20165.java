package Gold이상문제_정리;

import java.util.*;
import java.io.*;

public class 인내의도미노장인호석_20165 {
	static boolean[][] isUp;
	static int[][] domino;
	static int N, M, R;
	static int[] dy = {0, 0, 1, -1};
	static int[] dx = {1, -1, 0, 0};
	static StringBuilder answer = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		domino = new int[N][M];
		isUp = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				domino[i][j] = Integer.parseInt(st.nextToken());
				isUp[i][j] = true;
			}
		}

		int allPoint = 0;
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken()) - 1;
			int x = Integer.parseInt(st.nextToken()) - 1;
			char d = st.nextToken().charAt(0);

			if (isUp[y][x]) allPoint += attack(y, x, d);

			st = new StringTokenizer(br.readLine());
			y = Integer.parseInt(st.nextToken()) - 1;
			x = Integer.parseInt(st.nextToken()) - 1;

			if (!isUp[y][x]) isUp[y][x] = true;
		}

		printResult(allPoint);
	}

	private static void printResult(int point) {
		answer.append(point).append("\n");

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (isUp[i][j])
					answer.append("S ");
				else
					answer.append("F ");
			}
			answer.append("\n");
		}
		System.out.print(answer);
	}

	private static int attack(int y, int x, char d) {
		int point = 0;
		int dir = -1;
		if (d == 'N') dir = 3;
		if (d == 'S') dir = 2;
		if (d == 'W') dir = 1;
		if (d == 'E') dir = 0;

		Queue<Pin> queue = new LinkedList<>();
		queue.add(new Pin(y, x));
		isUp[y][x] = false;
		point++;
		point = BFS(queue, point, dir);

		return point;
	}

	private static int BFS(Queue<Pin> queue, int point, int dir) {
		while (!queue.isEmpty()) {
			Pin pin = queue.poll();
			int height = domino[pin.y][pin.x];

			int nx = pin.x;
			int ny = pin.y;
			for (int move = 1; move < height; move++) {
				ny += dy[dir];
				nx += dx[dir];
				if (nx >= M || ny >= N || nx < 0 || ny < 0) break;
				if (isUp[ny][nx]) {
					isUp[ny][nx] = false;
					queue.add(new Pin(ny, nx));
					point++;
				}
			}
		}
		return point;
	}

	private static class Pin
	{
		int y, x;

		public Pin(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
