package Gold이상문제_정리;

import java.io.*;
import java.util.*;

/*
2
4 3 2 1 0 4 3 2 1
1 2 3 4 1 2 3 4 0
 */
public class 야구게임_17281 {
	static int N, MAX;
	static int[] hit_order;
	static boolean[] visited;
	static int[][] inning_point;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		hit_order = new int[10];
		visited = new boolean[10];
		inning_point = new int[N+1][10];

		for (int i = 1; i < 10; i++)
			hit_order[i] = i;

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < 10; j++) {
				inning_point[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		DFS(1);
		System.out.println(MAX);
	}

	private static void DFS(int s) {
		if (s == 10) {
			if (hit_order[4] != 1) return;
//			System.out.println("** player **");
//			for (int p : hit_order)
//				System.out.print(p+" ");
//			System.out.println();
			MAX = Math.max(MAX, play_game());
			return;
		}

		for (int p = 1; p <= 9; p++) {
			if (!visited[p])
			{
				visited[p] = true;
				hit_order[s] = p;
				DFS(s + 1);
				visited[p] = false;
			}
		}
	}

	private static int play_game() {
		int out_count;
		int player = 1;
		int point = 0;

		for (int game = 1; game <= N; game++) {
			int[] base = {0, 0, 0, 0};
			out_count = 0;
			while (true){
				int now_player = hit_order[player++];
				if (player == 10) player = 1;

				int my_point = inning_point[game][now_player];
				if (my_point == 0) out_count++;
				if (out_count >= 3) break;
				else {
					switch (my_point) {
						case 1: {
							if (base[3] == 1) point++;
							base[3] = base[2];
							base[2] = base[1];
							base[1] = 1;
							break;
						}
						case 2: {
							if (base[3] == 1) point++;
							if (base[2] == 1) point++;
							base[3] = base[1];
							base[2] = 1;
							break;
						}
						case 3: {
							if (base[3] == 1) point++;
							if (base[2] == 1) point++;
							if (base[1] == 1) point++;
							base[3] = 1;
							break;
						}
						case 4: {
							if (base[3] == 1) point++;
							if (base[2] == 1) point++;
							if (base[1] == 1) point++;
							point++;
							break;
						}
					}
				}
			}
		}
		return point;
	}
}
