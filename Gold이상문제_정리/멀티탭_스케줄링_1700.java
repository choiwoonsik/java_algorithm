package Gold이상문제_정리;

import java.util.*;
import java.io.*;

public class 멀티탭_스케줄링_1700 {
	static int N, Hole;
	static ArrayList<Integer>[] adj;
	static HashSet<Integer> set = new HashSet<>();
	public static void main(String[] args) throws IOException {
		/*
		2 7
		2 3 2 3 1 2 7
		 */
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

		ArrayList<Integer> pq = new ArrayList<>(set);
		pq.sort(Comparator.comparingInt(o->adj[o].get(0)));
		for (int job : pq)
		{
			System.out.println(job +"의 순서 : "+adj[job].get(0));
		}

		int[] holeArr = new int[Hole];
		Arrays.fill(holeArr, 0);
		int count = 0;
		while (!pq.isEmpty())
		{
			pq.sort(Comparator.comparingInt(o->adj[o].get(0)));
//			for (int i : pq)
//				System.out.print(i+" "+adj[i].get(0)+", ");
//			System.out.println();

			int job = pq.get(0);
			if (adj[job].get(0) == Integer.MAX_VALUE)
				break;
			adj[job].remove(0);
			if (adj[job].size() == 0)
				adj[job].add(Integer.MAX_VALUE);

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
//				System.out.println(holeArr[last_job]+" >> "+job);
				holeArr[last_job] = job;
				count++;
			}
		}
		System.out.println(count);
	}
}
