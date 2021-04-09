package Gold이상문제_정리;

import java.io.*;
import java.util.*;

/*
5 5 6
1 1
3 2
2 3
5 1
5 4
 */
public class 사다리조작3_15684 {
	static boolean[][] board;
	static int row, col, bridge_cnt;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		col = Integer.parseInt(st.nextToken());
		bridge_cnt = Integer.parseInt(st.nextToken());
		row = Integer.parseInt(st.nextToken());

		board = new boolean[row+1][col+1];

		// 자기 자신이 true -> 오른쪽 / 자기 왼쪽이 true -> 왼쪽
		for (int b = 0; b < bridge_cnt; b++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			board[r][c] = true;
		}

		for (int b = 0; b <= 3; b++) {
			if (DFS(1, 0, 0, b)) {
				System.out.println(b);
				return;
			}
		}
		System.out.println(-1);
	}

	private static boolean DFS(int y, int x, int selected, int bridge) {

		if (selected == bridge) return is_ok();

		if (x < col) x++;
		else if (y < row) {
			y++;
			x = 1;
		}
		else
			return false;

		// 추가하는 경우 , 맨 오른쪽 x
		boolean plus_bridge = false;
		if (x < col && !board[y][x] && !board[y][x - 1] && !board[y][x + 1]) {
			board[y][x] = true;
			plus_bridge = DFS(y, x, selected + 1, bridge);
			board[y][x] = false;
		}
		// 못하는 경우
//		boolean no_plus = DFS(y, x, selected, bridge);
		return plus_bridge || DFS(y, x, selected, bridge);
	}

	private static boolean is_ok() {

		for (int c = 1; c <= col; c++) {
			int start = c;
			for (int r = 1; r <= row; r++) {
				if (start < col && board[r][start]) start++;
				else if (start > 1 && board[r][start-1]) start--;
			}
			if (start != c) return false;
		}
		return true;
	}
}
