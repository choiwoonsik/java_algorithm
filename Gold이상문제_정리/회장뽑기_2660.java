package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 회장뽑기_2660 {
	static int N;
	static int D[][];
	static int min = 9;
	static PriorityQueue<Integer> pq = new PriorityQueue<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		D = new int[N+1][N+1];
		for (int i = 1; i <= N; i++) {
			Arrays.fill(D[i], 9);
			D[i][i] = 0;
		}

		while (true) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			if (from == -1) break;
			D[from][to] = 1;
			D[to][from] = 1;
		}

		for (int dari = 1; dari <= N; dari++) {
			for (int i = 1; i <= N; i++) {
				int friend_max = 0;
				for (int j = 1; j <= N; j++) {
					D[i][j] = Math.min(D[i][j], D[i][dari] + D[dari][j]);
					friend_max = Math.max(D[i][j], friend_max);
				}
				if (min == friend_max && !pq.contains(i))
					pq.add(i);
				else if (min > friend_max) {
					pq.clear();
					pq.add(i);
					min = friend_max;
				}
			}
		}

		StringBuilder str = new StringBuilder();
		str.append(min).append(" ").append(pq.size()).append("\n");
		while (!pq.isEmpty()) str.append(pq.poll()).append(" ");
		System.out.print(str);
	}
}
