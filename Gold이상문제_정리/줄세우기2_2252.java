package Gold이상문제_정리;

import java.util.*;
import java.io.*;

/*
4 2
4 2
3 1
 */
public class 줄세우기2_2252 {
	static int N, M;
	static ArrayList<Integer>[] in_degree;
	static int[] degree_cnt;
	static StringBuilder str = new StringBuilder();
	static Queue<Integer> queue = new LinkedList<>();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		degree_cnt = new int[N+1];
		in_degree = new ArrayList[N+1];
		for (int i = 0; i < N + 1; i++) {
			in_degree[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int small = Integer.parseInt(st.nextToken());
			int big = Integer.parseInt(st.nextToken());
			in_degree[small].add(big);
			degree_cnt[big]++;
		}

		for (int i = 1; i < N + 1; i++) {
			if (degree_cnt[i] == 0)
				queue.add(i);
		}

		while (!queue.isEmpty())
		{
			int now = queue.poll();
			str.append(now).append(" ");

			for (int next : in_degree[now])
			{
				degree_cnt[next]--;
				if (degree_cnt[next] == 0) {
					queue.add(next);
				}
			}
		}
		System.out.print(str);
	}
}
