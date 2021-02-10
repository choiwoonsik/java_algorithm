package Gold이상문제_정리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 스도쿠_2580 {
	static int[][] board;
	static boolean isDone;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		board = new int[9][9];
		for (int i = 0; i < 9; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		backtracking(0, -1);
	}
	private static void backtracking(int i, int j)
	{
		if (i == 8 && j == 8) {
			if (!isDone) {
				isDone = true;
				for (int k = 0; k < 9; k++) {
					for (int l = 0; l < 9; l++) {
						System.out.print(board[k][l]+" ");
					}
					System.out.println();
				}
			}
			return;
		}

		if (isDone) return;

		int nextI = i;
		int nextJ = j+1;
		if (nextJ == 9) {
			nextJ = 0;
			nextI++;
		}

		if (board[nextI][nextJ] == 0) {
			for (int n = 1; n <= 9; n++) {
				if (isPossible(nextI, nextJ, n)) {
					board[nextI][nextJ] = n;
					backtracking(nextI, nextJ);
					board[nextI][nextJ] = 0;
				}
			}
		}
		else
			backtracking(nextI, nextJ);
	}

	private static boolean isPossible(int y, int x, int n) {
		// 라인체크
		for (int k = 0; k < 9; k++) {
			if (board[y][k] == n) return false;
			if (board[k][x] == n) return false;
		}
		// 박스 체크
		int sy = y - y%3;
		int sx = x - x%3;
		for (int i = sy; i < sy+3; i++) {
			for (int j = sx; j < sx+3; j++) {
				if (board[i][j] == n) return false;
			}
		}
		return true;
	}
}
