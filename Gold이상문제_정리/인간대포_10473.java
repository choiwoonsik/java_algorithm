package Gold이상문제_정리;

/*
25.0 100.0
190.0 57.5
4
125.0 67.5
75.0 125.0
45.0 72.5
185.0 102.5
 */

import java.io.*;
import java.util.*;

public class 인간대포_10473 {
	static int N;
	static double[] dp;
	static Dot[] dots;
	static int INF = 10000;
	static PriorityQueue<edge> pq = new PriorityQueue<>(Comparator.comparingDouble(s -> s.time));
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		Dot[] se = new Dot[2];
		for (int i = 0; i < 2; i++) {
			st = new StringTokenizer(br.readLine());
			double x = Double.parseDouble(st.nextToken());
			double y = Double.parseDouble(st.nextToken());
			se[i] = new Dot(x, y);
		}

		N = Integer.parseInt(br.readLine());
		N += 2;
		dp = new double[N+1];
		dots = new Dot[N+1];
		Arrays.fill(dp, INF);
		dp[1] = 0;

		dots[1] = se[0];
		dots[N] = se[1];
		for (int i = 2; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			double x1 = Double.parseDouble(st.nextToken());
			double y1 = Double.parseDouble(st.nextToken());
			dots[i] = new Dot(x1, y1);
		}

		pq.add(new edge(0, 1));
		dijkstra();
		System.out.println(dp[N]);
	}

	private static void dijkstra() {
		while (!pq.isEmpty())
		{
			edge e = pq.poll();

			if (e.time > dp[e.now]) continue;

			for (int next = 1; next < N + 1; next++) {
				// 거리
				double walk_d =
						Math.sqrt(Math.pow(dots[e.now].x - dots[next].x, 2)
								+ Math.pow(dots[e.now].y - dots[next].y, 2));

				// 걸어가는 경우
				if (dp[next] > e.time + walk_d / 5) {
					dp[next] = e.time + walk_d / 5;
					pq.add(new edge(dp[next], next));
				}

				// 대포타는 경우
				if (e.now != 1 && e.now != N) {
					double fly_t = 2 + Math.abs(50 - walk_d) / 5;

					if (dp[next] > e.time + fly_t) {
						dp[next] = e.time + fly_t;
						pq.add(new edge(dp[next], next));
					}
				}
			}
		}
	}

	private static class Dot {
		double x, y;

		public Dot(double x, double y) {
			this.x = x;
			this.y = y;
		}
	}
	private static class edge {
		double time;
		int now;

		public edge(double time, int now) {
			this.time = time;
			this.now = now;
		}
	}
}
