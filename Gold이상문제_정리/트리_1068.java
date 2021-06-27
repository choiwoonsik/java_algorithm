package Gold이상문제_정리;

import java.io.*;
import java.util.*;

/*
5
-1 0 0 1 1
2
 */

public class 트리_1068 {
	static int N, count, ROOT;
	static LinkedList<Integer>[] adj;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		adj = new LinkedList[N+1];
		for (int i = 0; i < N+1; i++) {
			adj[i] = new LinkedList<>();
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int parent = Integer.parseInt(st.nextToken());
			if (parent == -1) {
				adj[N].add(i);
				ROOT = i;
			}
			else {
				adj[parent].add(i);
			}
		}

		st = new StringTokenizer(br.readLine());
		int D = Integer.parseInt(st.nextToken());
		count = countChild(N, D);
		System.out.println(count);
	}

	private static int countChild(int start, int deleted) {
		int sum = 0;

		if (deleted == ROOT) return 0;

		if (adj[start].contains(deleted))
			adj[start].remove((Integer) deleted);

		if (adj[start].size() == 0) return 1;

		for (Integer child : adj[start])
		{
			sum += countChild(child, deleted);
		}

		return sum;
	}
}
