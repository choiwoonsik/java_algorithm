package Gold이상문제_정리;

import java.io.*;
import java.util.*;

/*
5
#..#.
 */

public class 타일뒤집기_Easy_14711 {
	static int N;
	static char[][] board;
	static int[][] count;
	static StringBuilder answer = new StringBuilder();
	static int[] dy = {0, 0, 1};
	static int[] dx = {-1, 1, 0};
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		board = new char[N][N];
		count = new int[N][N];
		board[0] = br.readLine().toCharArray();

		for (char c : board[0])
			answer.append(c);
		answer.append("\n");

		for (int i = 0; i < N - 1; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j] == '#') {
					count[i][j]++;
					for (int d = 0; d < 3; d++) {
						int ny = dy[d] + i;
						int nx = dx[d] + j;
						if (ny < 0 || nx < 0 || ny >= N || nx >= N) continue;
						count[ny][nx]++;
					}
				}
			}

			for (int j = 0; j < N; j++) {
				if (count[i][j] % 2 == 0)
					board[i + 1][j] = board[i][j] == '#' ? '#' : '.';
				else
					board[i + 1][j] = board[i][j] == '#' ? '.' : '#';
				answer.append(board[i + 1][j]);
			}
			answer.append("\n");
		}
		System.out.print(answer);
	}
}
