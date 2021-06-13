package Gold이상문제_정리;

import java.util.*;
import java.io.*;

/*
14
-1 0 0 1 1 1 2 2 3 4 5 6 7 12

21
-1 0 0 1 1 1 2 2 3 4 5 6 7 12 12 13 13 16 16 16 16

23
-1 0 1 1 1 2 2 2 3 3 3 4 4 4 0 14 15 16 17 18 19 20 21
 */

public class 뉴스전하기_1135 {
	static int N;
	static int Max = 0;
	static HashMap<Integer, Integer> map = new HashMap<>();
	static Queue<Integer> queue = new LinkedList<>();
	static ArrayList<Integer>[] adj = new ArrayList[51];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		adj = new ArrayList[N];

		for (int i = 0; i < N; i++) {
			adj[i] = new ArrayList<>();
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int p = Integer.parseInt(st.nextToken());
			if (p == -1) continue;
			adj[p].add(i);
		}

		queue.add(0);
		DFS(0);
		pq_DFS(0, 0);

		System.out.println(Max);
	}

	private static void pq_DFS(int p, int time) {
		adj[p].sort(Comparator.comparingInt(h -> -map.get(h)));
		for (int next : adj[p])
		{
			time++;
//			System.out.println(next+" : " + time);
			pq_DFS(next, time);
		}
		Max = Math.max(Max, time);
	}

	private static int DFS(int p) {
		int height = 1;
		int tmp = 0;
		for (int next : adj[p])
		{
			queue.add(next);
			tmp = Math.max(tmp, DFS(next));
		}
		height += tmp;
		map.put(p, height);
		return height;
	}
}
