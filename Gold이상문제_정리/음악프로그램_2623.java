package Gold이상문제_정리;
/*
6 3
3 1 4 3
4 6 2 5 4
2 2 3
 */

import java.io.*;
import java.util.*;

public class 음악프로그램_2623 {
	static int N, M;
	static int[] degree;
	static int count;
	static ArrayList<Integer>[] adj;
	static Queue<Integer> degree_queue = new LinkedList<>();
	static StringBuilder str = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		degree = new int[N+1];
		adj = new ArrayList[N+1];
		for (int i = 0; i < N + 1; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int len = Integer.parseInt(st.nextToken());
			int first_man = Integer.parseInt(st.nextToken());
			for (int i = 0; i < len-1; i++) {
				int next_man = Integer.parseInt(st.nextToken());
				adj[first_man].add(next_man);
				degree[next_man]++;
				first_man = next_man;
			}
		}
		for (int i = 1; i < N+1; i++) {
			if (degree[i] == 0) {
				count++;
				str.append(i).append("\n");
				degree_queue.add(i);
			}
		}

		while (!degree_queue.isEmpty())
		{
			int front = degree_queue.poll();

			for (int next : adj[front]) {
				degree[next]--;
				if (degree[next] == 0) {
					count++;
					str.append(next).append("\n");
					degree_queue.add(next);
				}
			}
		}

		if (count < N) System.out.println(0);
		else System.out.print(str);
	}
}
