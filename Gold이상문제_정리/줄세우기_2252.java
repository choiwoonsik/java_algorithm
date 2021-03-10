package Gold이상문제_정리;

import java.io.*;
import java.util.*;

/*
3 2
1 3
2 3
 */


public class 줄세우기_2252 {
	static int N, M;
	static ArrayList<Integer>[] adj;
	static int[] degree;
	static Queue<Integer> degree_queue = new LinkedList<>();
	static StringBuilder str = new StringBuilder();

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		degree = new int[N+1];
		adj = new ArrayList[N+1];
		for (int i = 0; i < N+1; i++) {
			adj[i] = new ArrayList<>();
		}
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int front = Integer.parseInt(st.nextToken());
			int back = Integer.parseInt(st.nextToken());
			adj[front].add(back);
			degree[back]++;
		}

		for (int i = 1; i < N+1; i++) {
			if(degree[i] == 0) {
				str.append(i).append(" ");
				degree_queue.add(i);
			}
		}

		while (!degree_queue.isEmpty())
		{
			int now = degree_queue.poll();

			for (int next : adj[now]) {
				degree[next]--;
				if (degree[next] == 0) {
					degree_queue.add(next);
					str.append(next).append(" ");
				}
			}
		}

		System.out.println(str);
	}
}
