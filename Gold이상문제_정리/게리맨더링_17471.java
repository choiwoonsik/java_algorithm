package Gold이상문제_정리;

import java.util.*;
import java.io.*;

public class 게리맨더링_17471 {
	static int N;
	static int[] peopleCnt;
	static ArrayList<Pair> comblist = new ArrayList<>();
	static ArrayList<Integer>[] adj;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		adj = new ArrayList[N+1];
		for (int i = 0; i < N + 1; i++)
			adj[i] = new ArrayList<>();

		peopleCnt = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < N + 1; i++)
			peopleCnt[i] = Integer.parseInt(st.nextToken());

		for (int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			int adjN = Integer.parseInt(st.nextToken());

			for (int j = 0; j < adjN; j++) {
				int next = Integer.parseInt(st.nextToken());
				adj[i].add(next);
				adj[next].add(i);
			}
		}

		int MIN = 1000000;
		for (int i = 1; i <= Math.ceil((double) N / 2); i++) {
			comblist.clear();
			boolean[] selected = new boolean[N];
			Arrays.fill(selected, false);

			// 선거구 조합 만들기
			makeComb(0, 0, i, selected);

			//해당 조합의 선거구가 되는지 확인
			for(Pair comb : comblist) {
				if (checkComb(comb))
				{
					int peopleA = 0;
					int peopleB = 0;
					for (int j = 0; j < comb.A.length; j++)
						peopleA += peopleCnt[comb.A[j]];
					for (int j = 0; j < comb.B.length; j++)
						peopleB += peopleCnt[comb.B[j]];

					MIN = Math.min(MIN, Math.abs(peopleA - peopleB));
				}
			}
		}
		if (MIN == 1000000) MIN = -1;
		System.out.println(MIN);
	}

	private static boolean checkComb(Pair comb) {
		int[] A = comb.A;
		int[] B = comb.B;

		boolean okA = true;
		boolean okB = true;

		if (A.length > 1)
			okA = adjSearch(A);
		if (B.length > 1)
			okB = adjSearch(B);

		return okA && okB;
	}

	private static boolean adjSearch(int[] array) {
		boolean[] visited = new boolean[N+1];
		Queue<Integer> queue = new LinkedList<>();
		int myArea = 0;
		queue.add(array[0]);

		while (!queue.isEmpty())
		{
			int now = queue.poll();
			for (int next : adj[now]) {
				if (visited[next]) continue;

				for (int j : array) {
					if (j == next && !visited[j]) {
						myArea++;
						visited[next] = true;
						queue.add(next);
					}
				}
			}
		}

		return  myArea == array.length;
	}

	private static void makeComb(int start, int select, int max, boolean[] selected) {
		if (select == max)
		{
			int idx = 0;
			int jdx = 0;
			int[] A = new int[max];
			int[] B = new int[N-max];
			for (int i = 0; i < selected.length; i++) {
				if (selected[i]) A[idx++] = i+1;
				else B[jdx++] = i+1;
			}

			comblist.add(new Pair(A, B));
			return;
		}

		for (int i = start; i < N; i++) {
			if (!selected[i])
			{
				selected[i] = true;
				makeComb(i + 1, select + 1, max, selected);
				selected[i] = false;
			}
		}
	}

	private static class Pair
	{
		int[] A;
		int[] B;

		public Pair(int[] a, int[] b) {
			A = a;
			B = b;
		}
	}
}
