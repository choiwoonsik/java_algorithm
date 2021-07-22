package Gold이상문제_정리;

import java.util.*;
import java.io.*;

public class 가운데서만나기_21940 {
	static int N, K, S, INF = 1000000;
	static int[][] cost;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		cost = new int[N][N];

		for (int i = 0; i < N; i++) {
			Arrays.fill(cost[i], INF);
			cost[i][i] = 0;
		}

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken()) - 1;
			int e = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken());
			cost[s][e] = c;
		}

		S = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int[] friends = new int[S];
		for (int i = 0; i < S; i++)
			friends[i] = Integer.parseInt(st.nextToken()) - 1;

		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (cost[i][k] == INF || cost[k][j] == INF) continue;
					if (cost[i][j] > cost[i][k] + cost[k][j]) {
						cost[i][j] = cost[i][k] + cost[k][j];
					}
				}
			}
		}

		ArrayList<Integer> candidate = new ArrayList<>();
		int minTotal = INF;
		int maxGoBack;
		for (int x = 0; x < N; x++) {
			maxGoBack = 0;
			for (int friend : friends) {
				int go = cost[friend][x];
				int back = cost[x][friend];

				if (go == INF || back == INF) {
					maxGoBack = INF;
					break;
				}

				if (maxGoBack < go + back)
					maxGoBack = go + back;
			}
			if (minTotal > maxGoBack) {
				minTotal = maxGoBack;
				candidate.clear();
				candidate.add(x + 1);
			} else if (minTotal == maxGoBack)
				candidate.add(x + 1);
		}

		StringBuilder ans = new StringBuilder();
		for (int x : candidate)
			ans.append(x).append(" ");
		System.out.println(ans);
	}
}
