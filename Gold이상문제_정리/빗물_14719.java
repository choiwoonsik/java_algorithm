package Gold이상문제_정리;

import java.io.*;
import java.util.*;

/*
4 4
3 0 1 4
 */
public class 빗물_14719 {
	static int H, W;
	static int[] wall;
	static boolean[][] map;
	static int water;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());

		map = new boolean[H][W];
		wall = new int[W];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < W; i++) {
			wall[i] = Integer.parseInt(st.nextToken());
		}

		for (int h = 0; h < H; h++) {
			for (int w = 0; w < W; w++) {
				if (h >= wall[w]) {
					if (h > wall[w] && !map[h-1][w]) continue;
					if(check_side(h, w)) {
						water++;
						map[h][w] = true;
					}
				}
			}
		}

		System.out.println(water);
	}

	private static boolean check_side(int h, int w) {
		boolean right = false;
		for (int i = w+1; i < W; i++) {
			if (wall[i] > h) {
				right = true;
				break;
			}
		}
		boolean left = false;
		for (int i = w-1; i >= 0; i--) {
			if (wall[i] > h) {
				left = true;
				break;
			}
		}
		return right && left;
	}
}
