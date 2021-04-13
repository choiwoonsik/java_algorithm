package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 스티커붙이기_18808 {
	static int row, col, K, tmp_r, tmp_c;
	static ArrayList<int[][]> sticker = new ArrayList<>();
	static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[row][col];

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int size_r = Integer.parseInt(st.nextToken());
			int size_c = Integer.parseInt(st.nextToken());
			int[][] now = new int[size_r][size_c];

			for (int y = 0; y < size_r; y++) {
				st = new StringTokenizer(br.readLine());
				for (int x = 0; x < size_c; x++) {
					now[y][x] = Integer.parseInt(st.nextToken());
				}
			}
			sticker.add(now);
		}

		for (int[][] now_sticker : sticker) {
			tmp_r = now_sticker.length;
			tmp_c = now_sticker[0].length;
			int[][] sub_sticker = new int[tmp_r][tmp_c];
			// 백지에 스티커 복사
			for (int y = 0; y < tmp_r; y++)
				sub_sticker[y] = now_sticker[y].clone();
			// 스티커 맞추기
			int temp;
			for (int t = 0; t < 4; t++) {
				if (!matching(sub_sticker)) {
					temp = tmp_r;
					tmp_r = tmp_c;
					tmp_c = temp;
					sub_sticker = turn(sub_sticker);
				} else break;
			}
		}

		int cnt = 0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (map[i][j] == 1) cnt++;
			}
		}
		System.out.println(cnt);
	}

	private static int[][] turn(int[][] sticker) {

		int[][] sub = new int[tmp_r][tmp_c];

		for (int r = 0; r < sticker.length; r++) {
			for (int c = 0; c < sticker[0].length; c++) {
				sub[c][sticker.length - r - 1] = sticker[r][c];
			}
		}

		return sub;
	}

	private static boolean matching(int[][] sub_sticker) {
		if (tmp_r > row || tmp_c > col) return false;
		for (int y = 0; y < row - tmp_r + 1; y++) {
			for (int x = 0; x < col - tmp_c + 1; x++) {
				if (patch(x, y, sub_sticker))
					return true;
			}
		}
		return false;
	}

	private static boolean patch(int x, int y, int[][] sticker) {

		for (int r = 0; r < tmp_r; r++) {
			for (int c = 0; c < tmp_c; c++) {
				if (map[r + y][c + x] == 1 && sticker[r][c] == 1) {
					return false;
				}
			}
		}

		for (int i = 0; i < tmp_r; i++) {
			for (int j = 0; j < tmp_c; j++) {
				if (map[i + y][j + x] == 0) {
					map[i + y][j + x] = sticker[i][j];
				}
			}
		}
		return true;
	}
}
