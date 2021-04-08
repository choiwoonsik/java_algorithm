package Gold이상문제_정리;

import java.util.*;
import java.io.*;

public class 택배_8980 {
	static PriorityQueue<sofo> pickup_pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.receive));
	static int N, C, M, all_box;
	static int[] capacity_arr;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(br.readLine());

		capacity_arr = new int[N+1];
		Arrays.fill(capacity_arr, C);

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int send = Integer.parseInt(st.nextToken());
			int receive = Integer.parseInt(st.nextToken());
			int box = Integer.parseInt(st.nextToken());
			pickup_pq.add(new sofo(send, receive, box));
		}

		solve();
		System.out.println(all_box);
	}

	private static void solve() {
		while (!pickup_pq.isEmpty())
		{
			sofo now_sofo = pickup_pq.poll();
			int send = now_sofo.send;
			int end = now_sofo.receive;
			int box = now_sofo.box;

			int min_C = 987654321;
			for (int i = send; i < end; i++) {
				if (capacity_arr[i] < min_C)
					min_C = capacity_arr[i];
			}

			if (box > min_C) box = min_C;

			all_box += box;

			for (int i = send; i < end; i++)
				capacity_arr[i] -= box;
		}
	}

	private static class sofo
	{
		int send;
		int receive;
		int box;

		public sofo(int send, int receive, int box) {
			this.send = send;
			this.receive = receive;
			this.box = box;
		}
	}
}
