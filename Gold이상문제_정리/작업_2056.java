package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 작업_2056 {
	static int N;
	static int max_time;
	static ArrayList<Integer>[] adj;
	static Queue<Integer> jobQueue = new LinkedList<>();
	static int[] degree_count;
	static int[] jobTime;
	static int[] before_time;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		jobTime = new int[N+1];
		before_time = new int[N+1];
		adj = new ArrayList[N+1];
		for (int i = 0; i < N; i++)
			adj[i+1] = new ArrayList<>();
		degree_count = new int[N+1];

		for (int job = 1; job < N + 1; job++) {
			st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			int pre_job_cnt = Integer.parseInt(st.nextToken());
			jobTime[job] = time;
			degree_count[job] = pre_job_cnt;
			for (int j = 0; j < pre_job_cnt; j++) {
				int pre_job = Integer.parseInt(st.nextToken());
				adj[pre_job].add(job);
			}
		}

		for (int i = 1; i < N+1; i++) {
			if (degree_count[i] == 0) {
				jobQueue.add(i);
				before_time[i] = jobTime[i];
			}
		}

		while (!jobQueue.isEmpty())
		{
			int now_job = jobQueue.poll();

			for (int next_job : adj[now_job])
			{
				degree_count[next_job]--;
				if (before_time[next_job] < jobTime[next_job] + before_time[now_job])
					before_time[next_job] = jobTime[next_job] + before_time[now_job];
				if (degree_count[next_job] == 0)
					jobQueue.add(next_job);
			}
		}

		for (int i = 0; i < N+1; i++) {
			max_time = Math.max(before_time[i], max_time);
		}
		System.out.println(max_time);
	}
}
