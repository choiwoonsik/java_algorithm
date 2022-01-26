package SDS_알고리즘.Day08;

import java.util.*;
import java.io.*;

public class 구간합구하기5_11660 {
	static int N, M;
	static int[][] numTable;
	static int[][] cummulativeSum;
	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		numTable = new int[N + 1][N + 1];
		cummulativeSum = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				numTable[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				int adjSum = cummulativeSum[i - 1][j] + cummulativeSum[i][j - 1] - cummulativeSum[i - 1][j - 1];
				adjSum += numTable[i][j];
				cummulativeSum[i][j] = adjSum;
			}
		}

		for (int q = 0; q < M; q++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());

			int total = cummulativeSum[y2][x2];
			int leftMinus = cummulativeSum[y2][x - 1];
			int upMinus = cummulativeSum[y - 1][x2];
			int leftUpPlus = cummulativeSum[y - 1][x - 1];
			answer.append(total - leftMinus - upMinus + leftUpPlus).append("\n");
		}
		System.out.print(answer);
	}
}
