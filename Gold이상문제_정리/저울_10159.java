package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 저울_10159 {
	static int N;
	static int K;
	static int[][] D;
	static final int INF = 99999;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());

		D = new int[N+1][N+1];
		for (int i = 1; i <= N; i++) {
			Arrays.fill(D[i], INF);
			D[i][i] = 0;
		}

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int big = Integer.parseInt(st.nextToken());
			int small = Integer.parseInt(st.nextToken());

			D[big][small] = 1;
		}

		for (int mid = 1; mid <= N; mid++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					D[i][j] = Math.min(D[i][j], D[i][mid] + D[mid][j]);
				}
			}
		}

		StringBuilder str = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			int cnt = 0;
			for (int j = 1; j <= N; j++) {
				if (Math.min(D[i][j], D[j][i]) == INF) cnt++;
			}
			str.append(cnt).append("\n");
		}
		System.out.print(str);
	}
}
