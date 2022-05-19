package 다이나믹프로그래밍;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

/*
4
2 3 3 3
3
1 4 10
 */
public class 양팔저울_2629 {
	static int N, M;
	static int[] weights;
	static int[] targets;
	static boolean[][] visited;
	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		visited = new boolean[N + 1][80001];
		weights = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			weights[i] = Integer.parseInt(st.nextToken());
		}

		M = Integer.parseInt(br.readLine());
		targets = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			targets[i] = Integer.parseInt(st.nextToken());
		}

		dfs(0, 40000);

		for (int i = 0; i < M; i++) {
			boolean isYes = false;
			for (int j = 1; j <= N; j++) {
				int sum = 40000 + targets[i];

				if (visited[j][sum]) {
					isYes = true;
					answer.append("Y ");
					break;
				}
			}
			if (!isYes) answer.append("N ");
		}

		System.out.print(answer);
	}

	private static void dfs(int count, int sum) {
		if (count > N) return;
		if (visited[count][sum]) return;
		visited[count][sum] = true;

		dfs(count + 1, sum + weights[count]);
		dfs(count + 1, sum - weights[count]);
		dfs(count + 1, sum);
	}
}
