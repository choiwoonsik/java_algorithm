package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 캐슬디펜스2_17135 {
	static int[][] map = new int[15][15];
	static int N, M, D, MAX;
	static ArrayList<Archer> archer_list = new ArrayList<>();
	static ArrayList<Zombie> zombie_list = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		// zombie queue
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) zombie_list.add(new Zombie(i, j, false));
			}
		}
		zombie_list.sort(Comparator.comparingInt(s->s.x));

		// archer queue
		boolean[] visited = new boolean[M];
		make_archer_list(visited, 0, 0);

		calc_kill();

		System.out.println(MAX);
	}

	private static void calc_kill() {
		for (Archer archer : archer_list)
		{
			int all_kill = 0;
			// each archer position
			ArrayList<Zombie> sub_zombie = new ArrayList<>();
			ArrayList<Zombie> live_zombie = new ArrayList<>();
			for (Zombie zombie : zombie_list)
				sub_zombie.add(new Zombie(zombie.y, zombie.x, zombie.isDead));

			while (!sub_zombie.isEmpty())
			{
				// kill
				all_kill += kill(sub_zombie, archer);

				// move
				move(sub_zombie);

				// clear zombie
				clear(sub_zombie, live_zombie);
			}
			MAX = Math.max(MAX, all_kill);
		}
	}

	private static void clear(ArrayList<Zombie> sub_zombie, ArrayList<Zombie> live_z) {
		live_z.clear();
		for (Zombie z : sub_zombie) {
			if (!z.isDead)
				live_z.add(z);
		}
		sub_zombie.clear();
		sub_zombie.addAll(live_z);
	}

	private static void move(ArrayList<Zombie> sub_zombie) {
		for (Zombie zombie : sub_zombie) {
			if (zombie.isDead) continue;
			zombie.y++;
			if (zombie.y == N)
				zombie.isDead = true;
		}
	}

	private static int kill(ArrayList<Zombie> zombies, Archer archer) {
		int kill_count = 0;

		Zombie candidate = null;
		for(int ax : archer.x) {
			int Min = 10000;
			for (Zombie z : zombies){
				int dist = Math.abs(N - z.y) + Math.abs(ax - z.x);
				if (dist > D) continue;
				if (dist < Min) {
					Min = dist;
					candidate = z;
				}
			}
			if (candidate != null && !candidate.isDead) {
				candidate.isDead = true;
				kill_count++;
			}
		}

		return kill_count;
	}

	private static void make_archer_list(boolean[] vst, int start, int slt) {
		if (slt == 3) {
			int[] xs = new int[3];
			int idx = 0;
			for (int i = 0; i < M; i++) if (vst[i]) xs[idx++] = i;
			archer_list.add(new Archer(xs));
			return;
		}

		for (int i = start; i < M; i++) {
			if (!vst[i]) {
				vst[i] = true;
				make_archer_list(vst, i+1, slt + 1);
				vst[i] = false;
			}
		}
	}

	private static class Archer {
		int[] x;

		public Archer(int[] x) {
			this.x = x;
		}
	}
	private static class Zombie {
		int y, x;
		boolean isDead;

		public Zombie(int y, int x, boolean isDead) {
			this.y = y;
			this.x = x;
			this.isDead = isDead;
		}
	}
}
