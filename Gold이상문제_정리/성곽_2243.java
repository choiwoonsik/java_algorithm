package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 성곽_2243 {
	static int[] dir4 = {1, 1<<1, 1<< 2, 1<< 3};
	static int[] dy = {0, -1, 0, 1};
	static int[] dx = {-1, 0, 1, 0};
	static int H, W, size, Numbering;
	static int[] roomSize;
	static int[][] roomCheck;
	static Dot[][] map;
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder str = new StringBuilder();

		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		map = new Dot[H][W];
		visited = new boolean[H][W];
		roomCheck = new int[H][W];

		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				int dir = Integer.parseInt(st.nextToken());
				boolean[] open = new boolean[4];
				Arrays.fill(open, true);
				if ((dir4[0] & dir) == dir4[0]) open[0] = false;
				if ((dir4[1] & dir) == dir4[1]) open[1] = false;
				if ((dir4[2] & dir) == dir4[2]) open[2] = false;
				if ((dir4[3] & dir) == dir4[3]) open[3] = false;
				map[i][j] = new Dot(open);
			}
		}

		int Max_size = 0;
		ArrayList<Integer> roomCnt = new ArrayList<>();

		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (!visited[i][j]) {
					visited[i][j] = true;
					roomCheck[i][j] = Numbering;
					size = 1;
					dfs(i, j);
					Numbering++;
					Max_size = Math.max(Max_size, size);
					roomCnt.add(size);
				}
			}
		}

		roomSize = new int[Numbering];
		for (int i = 0; i < roomCnt.size(); i++)
			roomSize[i] = roomCnt.get(i);

		int roomSumMax = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				int nowRoom = roomCheck[i][j];
				for (int d = 0; d < 4; d++) {
					int ny = i + dy[d];
					int nx = j + dx[d];
					if (ny < 0 || ny >= H || nx < 0 || nx >= W) continue;
					if (roomCheck[ny][nx] == nowRoom) continue;
					int nextRoom = roomCheck[ny][nx];
					roomSumMax = Math.max(roomSize[nowRoom] + roomSize[nextRoom], roomSumMax);
				}
			}
		}

		str.append(Numbering).append("\n").append(Max_size).append("\n").append(roomSumMax);
		System.out.print(str);
	}

	private static void dfs(int i, int j) {
		for (int d = 0; d < 4; d++) {
			if (map[i][j].isOpen[d])
			{
				int ny = dy[d] + i;
				int nx = dx[d] + j;
				if (!visited[ny][nx]) {
					size++;
					roomCheck[ny][nx] = Numbering;
					visited[ny][nx] = true;
					dfs(ny, nx);
				}
			}
		}
	}

	private static class Dot
	{
		boolean[] isOpen;

		public Dot(boolean[] isOpen) {
			this.isOpen = isOpen;
		}
	}
}
