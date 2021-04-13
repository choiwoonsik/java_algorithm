package Gold이상문제_정리;

import java.util.*;
import java.io.*;

public class easy2048_12100 {
	static int[][] map;
	static int[][] sub;
	static int N, MAX;
	static Stack<Integer> stack = new Stack<>();
	static ArrayList<Integer> after = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		DFS(map, 0);
		System.out.println(MAX);
	}

	private static void DFS(int[][] board, int move) {
		if (move == 5) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					MAX = Math.max(board[i][j], MAX);
				}
			}
			return;
		}

		// 왼쪽
		DFS(move_left(board), move+1);

		// 오른쪽
		DFS(move_right(board), move+1);

		// 위
		DFS(move_up(board), move+1);

		// 아래
		DFS(move_down(board), move+1);
	}

	private static int[][] move_up(int[][] board) {
		sub = new int[N][N];
		stack.clear();
		after.clear();

		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				if (board[y][x] > 0) {
					if (!stack.isEmpty()){
						if (stack.peek() == board[y][x]) {
							stack.pop();
							after.addAll(stack);
							after.add(board[y][x] * 2);
							stack.clear();
						}
						else stack.add(board[y][x]);
					}
					else stack.add(board[y][x]);
				}
			}
			after.addAll(stack);
			// 한줄 밀기 끝
			int idx = 0;
			for (Integer i : after)
				sub[x][idx++] = i;
			stack.clear();
			after.clear();
		}
		return sub;
	}

	private static int[][] move_down(int[][] board) {
		sub = new int[N][N];
		stack.clear();
		after.clear();

		for (int x = 0; x < N; x++) {
			for (int y = N - 1; y >= 0; y--) {
				if (board[y][x] > 0) {
					if (!stack.isEmpty()){
						if (stack.peek() == board[y][x]) {
							stack.pop();
							after.addAll(stack);
							after.add(board[y][x] * 2);
							stack.clear();
						}
						else stack.add(board[y][x]);
					}
					else stack.add(board[y][x]);
				}
			}
			after.addAll(stack);
			// 한줄 밀기 끝
			int idx = N - 1;
			for (Integer i : after)
				sub[x][idx--] = i;
			stack.clear();
			after.clear();
		}
		return sub;
	}

	private static int[][] move_right(int[][] board) {
		sub = new int[N][N];
		stack.clear();
		after.clear();

		for (int y = 0; y < N; y++) {
			for (int x = N-1; x >= 0; x--) {
				if (board[y][x] > 0) {
					if (!stack.isEmpty()){
						if (stack.peek() == board[y][x]) {
							stack.pop();
							after.addAll(stack);
							after.add(board[y][x] * 2);
							stack.clear();
						}
						else stack.add(board[y][x]);
					}
					else stack.add(board[y][x]);
				}
			}
			after.addAll(stack);
			// 한줄 밀기 끝
			int idx = N - 1;
			for (Integer i : after)
				sub[y][idx--] = i;
			stack.clear();
			after.clear();
		}
		return sub;
	}

	private static int[][] move_left(int[][] board) {
		sub = new int[N][N];
		stack.clear();
		after.clear();

		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				if (board[y][x] > 0) {
					if (!stack.isEmpty()) {
						if (stack.peek() == board[y][x]) {
							stack.pop();
							after.addAll(stack);
							after.add(board[y][x] * 2);
							stack.clear();
						}
						else stack.add(board[y][x]);
					}
					else stack.add(board[y][x]);
				}
			}
			after.addAll(stack);
			// 한줄 밀기 끝
			int idx = 0;
			for (Integer i : after)
				sub[y][idx++] = i;
			stack.clear();
			after.clear();
		}
		return sub;
	}
}
