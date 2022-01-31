package SDS_알고리즘.Day09;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 외판원순회_2098 {
	static int INF = 987654321;
	static int N;
	static int answer;
	static int VISIT_ALL;
	static int[][] W;
	static int[][] DP;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		VISIT_ALL = (1 << N) - 1; // 1111
		W = new int[N + 1][N + 1];
		DP = new int[N + 1][1 << N];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				W[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 1; i <= N; i++) {
			Arrays.fill(DP[i], INF);
		}

		answer = INF;
		DP[1][1] = 0; // 1 -> 1 비용 : 0
		calcDP(1, 1);

		System.out.println(answer);
	}

	private static void calcDP(int cur, int visited) {
		// 탈출조건
		if (visited == VISIT_ALL) {
			if (W[cur][1] == 0) {
				// 현재 -> 1로 가는 비용 : 1 -> 못간다
				return;
			}
			answer = Math.min(answer, DP[cur][visited] + W[cur][1]);
			return;
		}

		// 탐색
		for (int next = 1; next <= N; next++) {
			int nextVisitBit = (1 << (next - 1)) | visited;

			if (nextVisitBit == visited) {
				// 방문했었으면
				continue;
			}

			if (W[cur][next] == 0) {
				// 갈수가 없으면
				continue;
			}

			if (DP[next][nextVisitBit] > DP[cur][visited] + W[cur][next]) {
				DP[next][nextVisitBit] = DP[cur][visited] + W[cur][next];
				calcDP(next, nextVisitBit);
			}
		}
	}
}
