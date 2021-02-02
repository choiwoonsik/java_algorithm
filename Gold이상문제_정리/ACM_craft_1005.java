package Gold이상문제_정리;

import java.util.*;
import java.io.*;

public class ACM_craft_1005 {
	static int T, N, K;
	static int[] D;
	static int [] countDegree;
	static ArrayList<ArrayList<Integer>> adj;
	static Queue<Integer> que = new LinkedList<>();
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		StringBuilder str = new StringBuilder();

		T = Integer.parseInt(st.nextToken());
		while (T-- > 0) {
			int time = play();
			str.append(time).append("\n");
		}
		System.out.println(str);
	}
	private static int play() throws IOException {
		que.clear();
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		//작업 시간
		st = new StringTokenizer(br.readLine());
		D = new int[N+1];
		for (int n = 1; n <= N; n++)
			D[n] = Integer.parseInt(st.nextToken());

		// 연관된 작업
		countDegree = new int[N + 1];
		adj = new ArrayList<>();
		for (int i = 0; i <= N; i++)
			adj.add(new ArrayList<>());

		// 작업 연결
		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			int first = Integer.parseInt(st.nextToken());
			int after = Integer.parseInt(st.nextToken());
			adj.get(first).add(after); // 1 - 2, 3, 2 4, 34
			countDegree[after]++;// 2 ++, 3++, 4++, 4++
			// 먼저 해야될 조건들을 뒤에 달아준다
		}

		// 마지막 작업
		st = new StringTokenizer(br.readLine());
		int W = Integer.parseInt(st.nextToken());

		for (int i = 1; i <= N; i++) {
			if (countDegree[i] == 0) {
				que.add(i);
			}
		}

		int[] timeArr = new int[N+1];
		Arrays.fill(timeArr, 0);
		while (countDegree[W] > 0 && !que.isEmpty())
		{
			int job = que.poll(); // 먼저 할거
			for (int nextJob : adj.get(job)) { // job에 연결된 다음 작업 실시
				timeArr[nextJob] = Math.max(timeArr[nextJob], timeArr[job] + D[job]);
				countDegree[nextJob]--;
				if (countDegree[nextJob] == 0)
					que.add(nextJob);
			}
		}
		timeArr[W] += D[W];
		return timeArr[W];
	}
}
