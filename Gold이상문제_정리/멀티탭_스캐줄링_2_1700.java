package Gold이상문제_정리;

import java.util.*;
import java.io.*;

public class 멀티탭_스캐줄링_2_1700 {
	static int N, Hole;
	static ArrayList<Integer>[] adj;
	static HashSet<Integer> set = new HashSet<>();
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		Hole = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		adj = new ArrayList[N+1];
		for (int i = 0; i <= N; i++)
			adj[i] = new ArrayList<>();

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++){
			int job = Integer.parseInt(st.nextToken());
			adj[job].add(i); // job 의 수행 번호를 넣는다
			set.add(job);
		}

		PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(o -> adj[o].get(0)));
		pq.addAll(set);

		int[] holeArr = new int[Hole];
		Arrays.fill(holeArr, 0);
		int count = 0;
		while (!pq.isEmpty())
		{
			int job = pq.poll();
			if (adj[job].get(0) == Integer.MAX_VALUE)
				break;
			adj[job].remove(0); // 작업 처리
			if (adj[job].size() == 0)
				adj[job].add(Integer.MAX_VALUE);
			pq.add(job); // 다시 제자리 찾기

			boolean isPlug = false;
			for (int i = 0; i < Hole; i++) {
				if (holeArr[i] == 0 || holeArr[i] == job) {
					holeArr[i] = job;
					isPlug = true;
					break;
				}
			}
			if (!isPlug)
			{
				int last_job_order = 0;
				int last_job = 0;
				for (int i = 0; i < Hole; i++) {
					if (adj[holeArr[i]].get(0) > last_job_order)
					{
						last_job_order = adj[holeArr[i]].get(0);
						last_job = i;
					}
				}
				holeArr[last_job] = job;
				count++;
			}
		}
		System.out.println(count);
	}
}
