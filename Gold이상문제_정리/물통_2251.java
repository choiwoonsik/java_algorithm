package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 물통_2251 {
	static int[] water = new int[3];
	static int[] from = {0, 0, 1, 1, 2, 2};
	static int[] to = {1, 2, 0, 2, 0, 1};
	static boolean[] cWater = new boolean[201];
	static boolean[][] visited = new boolean[201][201];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		water[0] = Integer.parseInt(st.nextToken());
		water[1] = Integer.parseInt(st.nextToken());
		water[2] = Integer.parseInt(st.nextToken());

		/*
		a -> b
		a -> c

		b -> a
		b -> c

		c -> a
		c -> b
		 */

		Queue<Dot> queue = new LinkedList<>();
		queue.add(new Dot(0, 0));
		visited[0][0] = true;
		cWater[water[2]] = true;

		while (!queue.isEmpty())
		{
			Dot dot = queue.poll();
			int a = dot.a;
			int b = dot.b;
			int c = water[2] - a - b;

			for (int i = 0; i < 6; i++) {
				int[] next = {a, b, c};
				next[to[i]] += next[from[i]];
				next[from[i]] = 0;

				if (next[to[i]] > water[to[i]])
				{
					int over = next[to[i]] - water[to[i]];
					next[from[i]] = over;
					next[to[i]] -= over;
				}

				if (!visited[next[0]][next[1]]) {
					visited[next[0]][next[1]] = true;
					queue.add(new Dot(next[0], next[1]));
					if (next[0] == 0) {
						cWater[next[2]] = true;
					}
				}
			}
		}
		for (int w = 0; w <= water[2]; w++) {
			if (cWater[w])
				System.out.print(w+" ");
		}
	}

	private static class Dot
	{
		int a, b;

		public Dot(int a, int b) {
			this.a = a;
			this.b = b;
		}
	}
}
