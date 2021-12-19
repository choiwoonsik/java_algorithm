package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 사다리타기_2469 {
	static boolean[][] board;
	static int K, N, L;
	static boolean isOver;
	static String myResult;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		K = Integer.parseInt(br.readLine());
		N = Integer.parseInt(br.readLine());
		myResult = br.readLine();
		board = new boolean[N][K-1];

		L = 0;
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			if (s.charAt(0) == '?') {
				L = i;
				continue;
			}
			for (int j = 0; j < s.length(); j++) {
				char c = s.charAt(j);
				if (c == '*') board[i][j] = false;
				if (c == '-') board[i][j] = true;
			}
		}

		for (int dari = 0; dari <= (K-1) / 2; dari++) {
			boolean[] visited = new boolean[K-1];
			boolean[] makeDari = new boolean[K-1];
			makeLine(0, 0, dari, visited, makeDari);
		}

		if (!isOver) {
			StringBuilder result = new StringBuilder();
			System.out.println(result.append("*".repeat(K - 1)));
		}
	}

	private static boolean sadari() {
		char[] result = new char[K];
		char A = 'A';
		int play;
		for (int c = 0; c < K; c++) {
			play = c;
			char start = (char)(A + c);
			for (int r = 0; r < N; r++) {
				if (play == 0) {
					if (board[r][play])
						play += 1;
				}
				else if (play == K - 1) {
					if (board[r][play - 1])
						play -= 1;
				}
				else {
					if (board[r][play-1]) play -= 1;
					else if (board[r][play]) play += 1;
				}
			}
			result[play] = start;
		}
		StringBuilder str = new StringBuilder();
		for (char c : result)
			str.append(c);
		return str.toString().equals(myResult);
	}

	private static void makeLine(int start, int select, int max, boolean[] visited, boolean[] make) {
		if (isOver) return;

		if (select == max) {
			board[L] = make.clone();

//			for (boolean value : make) {
//				if (value) System.out.print("-");
//				else System.out.print("*");
//			}
//			System.out.println();

			if (sadari()) {
				isOver = true;
				for (boolean b : make)
					if (b) System.out.print("-");
					else System.out.print("*");
			}
			return;
		}

		for (int i = start; i < K-1; i++) {
			if (!visited[i])
			{
				visited[i] = true;
				if (i > 1 && !make[i - 1]) {
					make[i] = true;
					makeLine(i + 1, select + 1, max, visited, make);
				}
				else if (i == 0) {
					make[i] = true;
					makeLine(i + 1, select + 1, max, visited, make);
				}
				visited[i] = false;
				make[i] = false;
			}
		}
	}
}
