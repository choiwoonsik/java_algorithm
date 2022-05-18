package 백트래킹;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

/*
1 2 3 4 1 2 3 4 1 2
 */
public class 주사위_윷놀이_17825 {
	static int len = 43;
	static HashMap<Integer, Integer> map = new HashMap<>();
	static HashMap<Integer, Integer> turn = new HashMap<>();
	static boolean[] check;
	static int[] horse;
	static int[] dice;
	static int max;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		dice = new int[10];
		for (int i = 0; i < 10; i++) {
			dice[i] = Integer.parseInt(st.nextToken());
		}

		check = new boolean[len];
		horse = new int[4];
		init();

		dfs(0, 0);

		System.out.println(max);
	}

	private static void dfs(int idx, int sum) {
//		System.out.println("idx : " + idx + ", sum : " + sum);

		if (idx == 10) {
//			System.out.println("[MAX]");
			max = Math.max(max, sum);
			return;
		}

		for (int h = 0; h < 4; h++) {
			int cur = horse[h];
			int next = cur;
			int dist = dice[idx];
			int point = 0;

			if (cur == 42) continue;

			if (turn.containsKey(cur)) {
				while (dist-- > 0) {
					next = turn.get(next);
					if (next == 40) {
						next = map.get(next);
						break;
					}
				}
//				System.out.println("TURN : " + cur + " -> " + next);
			} else {
				while (dist-- > 0) {
					next = map.get(next);
					if (next == 40) {
						next = map.get(next);
						break;
					}
				}
//				System.out.println("GO : " + cur + " -> " + next);
			}

			if (next != len) point = next;
			if (next != len && check[next]) continue;

			check[cur] = false;
			check[next] = true;
			horse[h] = next;
			dfs(idx + 1, sum + point);
			horse[h] = cur;
			check[next] = false;
			check[cur] = true;
		}
	}

	private static void init() {
		int start = 0;
		for (int i = start; i < 41; i += 2) {
			map.put(i, i + 2);
//			System.out.println(i + " : " + map.get(i));
		}

		start = 10;
		for (int i = start; i < 19; i += 3) {
			turn.put(i, i + 3);
		}
		turn.put(19, 25);

		start = 20;
		for (int i = start; i < 24; i += 2) {
			turn.put(i, i + 2);
		}
		turn.put(24, 25);

		turn.put(30, 28);
		start = 28;
		for (int i = start; i > 26; i--) {
			turn.put(i, i - 1);
		}
		turn.put(26, 25);

		start = 25;
		for (int i = start; i < 40; i += 5) {
			turn.put(i, i + 5);
		}
	}
}
