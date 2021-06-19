package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class tamato_7576 {
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static int X;
	static int Y;
	static int N;
	static int day;
	static int[][] map;
	static int[] p_x = {0, 0, -1, 1};
	static int[] p_y = {-1, 1, 0, 0};
	static Queue<Dot> que = new LinkedList<>();
	public static void main(String[] args) throws IOException {
		day_check();
	}
	private static void day_check() throws IOException {
		int[] size = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		X = size[1];
		Y = size[0];
		map = new int[X][Y];
		for (int i = 0; i < X; i++)
			map[i] = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		for (int i = 0; i < X; i++) {
			for (int j = 0; j < Y; j++) {
				if (map[i][j] == 1) {
					//초기 익은 토마토다 좌표를 모두 큐에 넣는다
					que.add(new Dot(i, j));
					N++;
				}
			}
		}
		BFS();
		if (check_all())
			System.out.println(day);
	}

	private static void BFS() {
		while (!que.isEmpty()) {
			boolean day_cut = false;
			int		cnt = 0;
			//하루에 늘어난 토마토 개수만큼만 토마토를 세어준다
			for (int n = 0; n < N; n++) {
				Dot now = que.poll();
				for (int i = 0; i < 4; i++) {
					int x = now.x + p_x[i];
					int y = now.y + p_y[i];

					if (x >= 0 && y >= 0 && x < X && y < Y) {
						if (map[x][y] == 0) {
							//늘어나는 토마토 개수 증가
							cnt++;
							day_cut = true;
							map[x][y] = 1;
							que.add(new Dot(x, y));
						}
					}
				}
			}
			N = cnt;
			if (day_cut)
				day++;
		}
	}

	private static boolean check_all()
	{
		for (int i = 0; i < X; i++) {
			for (int j = 0; j < Y; j++) {
				if (map[i][j] == 0) {
					System.out.println(-1);
					return (false);
				} else if (day == 0){
					System.out.println(0);
					return (false);
				}
			}
		}
		return (true);
	}
}
