package Gold이상문제_정리;

import java.util.*;
import java.io.*;

public class 최소비용구하기_1916 {
	static int[] dp;
	static boolean[] visited;
	static PriorityQueue<Bus> pq = new PriorityQueue<>(Comparator.comparingInt(s -> s.pay));
	static ArrayList<Bus>[] adjList;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int cityN = Integer.parseInt(br.readLine());
		int busN = Integer.parseInt(br.readLine());

		adjList = new ArrayList[cityN];
		dp = new int[cityN];
		Arrays.fill(dp, Integer.MAX_VALUE);
		visited = new boolean[cityN];
		for (int i = 0; i < cityN; i++) {
			adjList[i] = new ArrayList<>();
		}

		for (int bus = 0; bus < busN; bus++)
		{
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()) - 1;
			int end = Integer.parseInt(st.nextToken()) - 1;
			int pay = Integer.parseInt(st.nextToken());
			adjList[start].add(new Bus(end, pay));
		}

		st = new StringTokenizer(br.readLine());
		int START = Integer.parseInt(st.nextToken()) - 1;
		int END = Integer.parseInt(st.nextToken()) - 1;

		pq.add(new Bus(START, 0));
		dp[START] = 0;
		while (!pq.isEmpty())
		{
			Bus bus = pq.poll();
			int now = bus.end;
			int pay = bus.pay;

			visited[now] = true;

			if (dp[now] < pay) continue;

			for (Bus nextBus : adjList[now])
			{
				int next = nextBus.end;
				int nextPay = nextBus.pay;
				if (dp[now] + nextPay < dp[next])
				{
					dp[next] = dp[now] + nextPay;
					pq.add(nextBus);
				}
			}
		}
		System.out.println(dp[END]);
	}

	private static class Bus{
		int end;
		int pay;

		public Bus(int end, int pay) {
			this.end = end;
			this.pay = pay;
		}
	}
}
