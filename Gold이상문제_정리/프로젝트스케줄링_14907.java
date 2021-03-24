package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 프로젝트스케줄링_14907 {
	static int[] degree_cnt = new int[26];
	static ArrayList<Integer>[] degree = new ArrayList[26];
	static Queue<Integer> job_queue = new LinkedList<>();
	static int[] job_time = new int[26];
	static int[] before_time = new int[26];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Arrays.fill(degree_cnt, -1);
		for (int i = 0; i < 26; i++) {
			degree[i] = new ArrayList<>();
		}

		String line;
		while ((line = br.readLine()) != null)
		{
			String[] line_arr = line.split(" ");
			if (line_arr.length < 3) {
				char job = line_arr[0].charAt(0);
				int time = Integer.parseInt(line_arr[1]);
				if (degree_cnt[job-'A'] == -1) degree_cnt[job-'A'] = 0;
				job_time[job-'A'] = time;
			} else {
				char job = line_arr[0].charAt(0);
				int time = Integer.parseInt(line_arr[1]);
				if (degree_cnt[job-'A'] == -1) degree_cnt[job-'A'] = 0;
				job_time[job-'A'] = time;

				for (int before = 0; before < line_arr[2].length(); before++) {
					char before_job = line_arr[2].charAt(before);
					degree[before_job - 'A'].add(job - 'A');
					degree_cnt[job-'A']++;
				}
			}
		}

		for (int i = 0; i < degree_cnt.length; i++) {
			if (degree_cnt[i] == 0) {
				job_queue.add(i);
				before_time[i] = job_time[i];
			}
		}

		while (!job_queue.isEmpty())
		{
			int now_job = job_queue.poll();

			for (int next : degree[now_job])
			{
				degree_cnt[next]--;
				if (before_time[next] < before_time[now_job] + job_time[next]) {
					before_time[next] = before_time[now_job] + job_time[next];
				}
				if (degree_cnt[next] == 0) {
					job_queue.add(next);
				}
			}
		}

		int max = 0;
		for (int j : before_time) {
			max = Math.max(max, j);
		}
		System.out.println(max);
	}
}
