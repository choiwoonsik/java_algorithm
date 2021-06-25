package Gold이상문제_정리;

import java.util.*;
import java.io.*;

public class 컨베이어벨트위의로봇_20055 {
	static int N, K;
	static int[] belt;
	static boolean[] robot;
	static int L;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		L = 2 * N;
		belt = new int[2*N];
		robot = new boolean[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 2*N; i++) {
			belt[i] = Integer.parseInt(st.nextToken());
		}

		int level = 0;
		while (K > 0)
		{
			rotate_belt();
			rotate_robot();
			level++;
		}

		System.out.println(level);
	}

	private static void rotate_robot() {
		robot[N - 1] = false;

		for (int i = N - 2; i >= 0; i--) {
			if (!robot[i + 1] && belt[i + 1] > 0 && robot[i]) {
				robot[i] = false;
				robot[i + 1] = true;
				belt[i + 1]--;
				if (belt[i + 1] == 0) K--;
			}
		}

		if (!robot[0] && belt[0] > 0) {
			belt[0]--;
			robot[0] = true;
			if (belt[0] == 0) K--;
		}
	}

	// 1 -> 2*N , 2 -> 1
	private static void rotate_belt() {
		int tmpHP = belt[L - 1];

		for (int i = L - 1; i >= N; i--) {
			belt[i] = belt[i - 1];
		}

		for (int i = N - 1; i >= 1; i--) {
			belt[i] = belt[i - 1];
			robot[i] = robot[i - 1];
		}

		belt[0] = tmpHP;
		robot[0] = false;
	}
}
