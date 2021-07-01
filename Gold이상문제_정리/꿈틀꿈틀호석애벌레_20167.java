package Gold이상문제_정리;

import java.io.*;
import java.util.*;

/*
9 6
1 5 4 4 2 3 10 3 5
 */

public class 꿈틀꿈틀호석애벌레_20167 {
	static int N, K, E;
	static int[] bugs;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		bugs = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			bugs[i] = Integer.parseInt(st.nextToken());
		}

		go(0, 0, 0);
		System.out.println(E);
	}

	private static void go(int now, int beforeEat, int stackEat) {
		if (now == N) {
			E = Math.max(E, stackEat);
			return;
		}

		if (beforeEat + bugs[now] >= K) {
			stackEat += beforeEat + bugs[now] - K;
			go(now + 1, 0, stackEat);
		}
		else
			go(now+1, beforeEat + bugs[now], stackEat);

		go(now + 1, 0, stackEat);
	}
}
