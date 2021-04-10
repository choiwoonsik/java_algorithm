package Gold이상문제_정리;
/*
2
7
3 1 3 7 3 4 6
8
1 2 3 4 5 6 7 8
 */

import java.io.*;
import java.util.*;

public class 텀프로젝트_9466 {
	static int T;
	static int N, sum;
	static int[] selected;
	static boolean[] visited;
	static boolean[] team;
	static StringBuilder str = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			N = Integer.parseInt(br.readLine());
			selected = new int[N+1];
			st = new StringTokenizer(br.readLine());
			for (int sel = 1; sel < N + 1; sel++)
				selected[sel] = Integer.parseInt(st.nextToken());
			solve();
		}
		System.out.print(str);
	}

	private static void solve() {
		visited = new boolean[N+1];
		team = new boolean[N+1];
		sum = 0;
		for (int n = 1; n <= N; n++) {
			DFS(n);
		}
		str.append(N - sum).append("\n");
	}

	private static void DFS(int n) {
		visited[n] = true;

		int next = selected[n];

		if (!visited[next]) {
			DFS(next);
		} else if (!team[next]) {
			int cnt = 1;
			// 팀 인원수 세기
			for (int i = selected[n]; i != n ; i = selected[i]) {
				cnt++;
				if (team[i]) {
					team[n] = true;
					return;
				}
			}
			sum += cnt;
		}
		team[next] = true;
	}
}
