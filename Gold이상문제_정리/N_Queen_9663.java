package Gold이상문제_정리;

import java.util.Scanner;

public class N_Queen_9663 {
	static int N;
	static int[] board;
	static int count;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		board = new int[N];
		DFS(0);
		System.out.println(count);
	}

	private static void DFS(int pos) {
		// 다 놓은 경우
		if (pos == N)
		{
			count++;
			return;
		}
		for (int x = 0; x < N; x++)
		{
			board[pos] = x;
			if (isPossible(pos))
				DFS(pos + 1);
		}
	}

	private static boolean isPossible(int pos) {
		for (int i = 0; i < pos; i++) {
			if (board[i] == board[pos] || Math.abs(board[i] - board[pos]) == pos - i)
				return false;
		}
		return true;
	}
}
