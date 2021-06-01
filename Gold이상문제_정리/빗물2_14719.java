package Gold이상문제_정리;
/*
4 4
3 0 1 4
 */

import java.util.*;
import java.io.*;

public class 빗물2_14719 {
	static int H, W;
	static Stack<String> stack = new Stack<>();
	static int[] wall = new int[501];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < W; i++) {
			wall[i] = Integer.parseInt(st.nextToken());
		}

		int answer = check();
		System.out.println(answer);
	}

	private static int check() {
		int count = 0;
		for (int y = 0; y < H; y++) {
			for (int x = 1; x < W - 1; x++) {
				if (y < wall[x]) continue;
				boolean right_wall = right_wall_check(y, x);
				boolean left_wall = left_wall_check(y, x);
				if (!right_wall && !left_wall) return count;
				else if (right_wall && left_wall) count++;
			}
		}
		return count;
	}

	private static boolean left_wall_check(int y, int x) {
		for (int nowX = x; nowX >= 0; nowX--) {
			if (wall[nowX] > y) {
				return true;
			}
		}
		return false;
	}

	private static boolean right_wall_check(int y, int x) {
		for (int nowX = x; nowX < W; nowX++) {
			if (wall[nowX] > y)
				return true;
		}
		return false;
	}
}
