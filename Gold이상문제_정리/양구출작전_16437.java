package Gold이상문제_정리;

import java.util.*;
import java.io.*;

public class 양구출작전_16437 {
	static int N;
	static long answer;
	static Animal_cnt[] island = new Animal_cnt[123457];
	static ArrayList<Integer>[] adj = new ArrayList[123457];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		for (int i = 1; i <= N; i++) adj[i] = new ArrayList<>();

		for (int i = 2; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			char animal = st.nextToken().charAt(0);
			int cnt = Integer.parseInt(st.nextToken());
			int bridge = Integer.parseInt(st.nextToken());
			island[i] = new Animal_cnt(animal, cnt);
			adj[bridge].add(i);
		}

		island[1] = new Animal_cnt('S', 0);
		answer = sheep_escape(1);
		System.out.println(answer);
	}

	private static long sheep_escape(int start) {
		long survivor = 0;

		for (int next : adj[start]) {
			survivor += sheep_escape(next);
		}

		if (island[start].animal == 'S') {
			return survivor + island[start].cnt;
		} else {
			return Math.max(survivor - island[start].cnt, 0);
		}
	}

	private static class Animal_cnt{
		char animal;
		int cnt;

		public Animal_cnt(char animal, int cnt) {
			this.animal = animal;
			this.cnt = cnt;
		}
	}
}
