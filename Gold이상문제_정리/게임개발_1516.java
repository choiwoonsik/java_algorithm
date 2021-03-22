package Gold이상문제_정리;

/*
5
10 -1
10 1 -1
4 1 -1
4 3 1 -1
3 3 -1
 */

import java.io.*;
import java.util.*;

public class 게임개발_1516 {
	static int N;
	static int[] build_time;
	static int[] before_time;
	static int[] degree_cnt;
	static Queue<Integer> job_queue = new LinkedList<>();
	static ArrayList<Integer>[] in_degree;
	static StringBuilder str = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		build_time = new int[N+1];
		before_time = new int[N+1];
		degree_cnt = new int[N+1];
		in_degree = new ArrayList[N+1];
		for (int i = 1; i <= N; i++) {
			in_degree[i] = new ArrayList<>();
		}

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			build_time[i] = t;
			int need_build;
			while ((need_build = Integer.parseInt(st.nextToken())) != -1){
				in_degree[need_build].add(i);
				degree_cnt[i]++;
			}
		}

		for (int i = 1; i < N+1; i++) {
			if (degree_cnt[i] == 0) {
				job_queue.add(i);
				before_time[i] = build_time[i];
			}
		}

		while (!job_queue.isEmpty())
		{
			int now = job_queue.poll();

			for (int next_build : in_degree[now])
			{
				degree_cnt[next_build]--;
				if (before_time[next_build] < before_time[now] + build_time[next_build])
					before_time[next_build] = before_time[now] + build_time[next_build];
				if (degree_cnt[next_build] == 0) {
					job_queue.add(next_build);
				}
			}
		}

		for (int i = 1; i < N + 1; i++) {
			str.append(before_time[i]).append("\n");
		}
		System.out.print(str);
	}
}
