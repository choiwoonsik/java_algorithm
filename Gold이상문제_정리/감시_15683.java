package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 감시_15683 {
	static int N, M, Min;
	static int[][] map;
	static ArrayList<camera> cameraList = new ArrayList<>();
	static int[] direction = {0, 4, 2, 4, 4, 1};
	public static void main(String[] args) throws IOException {
		// 1번 카메라 오, 아래, 왼, 위 (4)
		// 2번 카메라 오-왼 , 위-아래 (2)
		// 3번 카메라 위-오, 오-아래, 아래-왼, 왼-위 (4)
		// 4번 카메라 ㅗ, ㅏ, ㅜ, ㅓ (4)
		// 5번 카메라 + (1)

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		cameraList = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] >= 1 && map[i][j] <= 5)
					cameraList.add(new camera(map[i][j], i, j));
			}
		}

		Min = 9999999;
		DFS(0);
		System.out.println(Min);
	}
	private static void DFS(int camera)
	{
		if (camera >= cameraList.size()) {
			Min = Math.min(Min, check_cannot_see());
			return;
		}
		int[][] subMap = new int[N][M];
		int type = cameraList.get(camera).num;
		for (int dir = 0; dir < direction[type]; dir++)
		{
			map_copy(subMap, map);
			if (type == 1)
			{
				check_map(dir, cameraList.get(camera));
			}
			else if (type == 2) {
				check_map(dir, cameraList.get(camera));
				check_map((dir + 2)%4, cameraList.get(camera));
			}
			else if (type == 3) {
				check_map(dir, cameraList.get(camera));
				check_map((dir + 1)%4, cameraList.get(camera));
			}
			else if (type == 4) {
				check_map(dir, cameraList.get(camera));
				check_map((dir + 1)%4, cameraList.get(camera));
				check_map((dir + 3)%4, cameraList.get(camera));
			}
			else if (type == 5) {
				check_map(dir, cameraList.get(camera));
				check_map(dir+1, cameraList.get(camera));
				check_map(dir+2, cameraList.get(camera));
				check_map(dir+3, cameraList.get(camera));
			}
			DFS(camera+1);
			map_copy(map, subMap);
		}
	}

	private static void check_map(int dir, camera camera) {
		int y = camera.y;
		int x = camera.x;

		if (dir == 0) {
			while (y > 0 && map[y-1][x] != 6) {
				map[--y][x] = 9;
			}
		}
		else if (dir == 1) {
			while (x < M-1 && map[y][x+1] != 6)
				map[y][++x] = 9;
		}
		else if (dir == 2) {
			while (y < N-1 && map[y+1][x] != 6)
				map[++y][x] = 9;
		}
		else if (dir == 3) {
			while (x > 0 && map[y][x-1] != 6)
				map[y][--x] = 9;
		}
	}

	private static void map_copy(int[][] dest, int[][] src) {
		for (int i = 0; i < N; i++)
			dest[i] = src[i].clone();
	}

	private static int check_cannot_see() {
		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) count++;
			}
		}
		return count;
	}

	private static class camera
	{
		int num;
		int y;
		int x;

		public camera(int num, int y, int x) {
			this.num = num;
			this.y = y;
			this.x = x;
		}
	}
}
