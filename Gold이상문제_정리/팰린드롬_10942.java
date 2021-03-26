package Gold이상문제_정리;

import java.io.*;
import java.util.*;

/*
7
1 2 1 3 1 2 1
4
1 3
2 5
3 3
5 7
 */
public class 팰린드롬_10942 {
	static boolean[][] dp;
	static boolean[][] visited;
	static int N, M;
	static int[] num;
	static StringBuilder str = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		num = new int[N+1];
		dp = new boolean[N+1][N+1];
		visited = new boolean[N+1][N+1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < N+1; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}

		M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			if (is_pallindrom(s, e)) str.append("1").append("\n");
			else str.append("0").append("\n");
		}
		System.out.print(str);
	}

	private static boolean is_pallindrom(int s, int e) {
		if (s > e) return false;
		if (s == e) return true;
		if (visited[s][e]) return dp[s][e];
		visited[s][e] = true;

		if (num[s] == num[e]) {
			if (e - s != 1) {
				if (is_pallindrom(s + 1, e - 1))
					dp[s][e] = true;
			} else dp[s][e] = true;
		} else
			dp[s][e] = false;
		return dp[s][e];
	}
}
